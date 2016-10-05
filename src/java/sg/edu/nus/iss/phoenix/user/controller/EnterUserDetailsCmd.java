/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.RoleDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;

/**
 *
 * @author Vishnu
 */
@Action("enteruser")
public class EnterUserDetailsCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserDelegate userDlg = new UserDelegate();
        RoleDelegate roleDlg = new RoleDelegate();
        ReviewSelectUserDelegate rsUserDlg = new ReviewSelectUserDelegate();

        User user = new User();
        user.setId(req.getParameter("id"));
        user.setName(req.getParameter("name"));
        String[] str_roles = req.getParameterValues("selected_roles");
        List<Role> roles = new ArrayList<>();
        for (String temp : str_roles) {
            Role role = roleDlg.find(temp);
            if (role == null) {
                return "/pages/error.jsp";
            }
            roles.add(role);
        }
        user.setRoles(roles);
        if (req.getParameter("ins").equals("true")) {
            userDlg.processCreate(user);
        } else {
            userDlg.processModify(user);
        }
        req.setAttribute("users", rsUserDlg.reviewSelectUser());
        return "/pages/cruduser.jsp";
    }
}
