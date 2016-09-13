/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;

/**
 *
 * @author Liu Zhenchang
 */
@Action("deleteuser")
public class DeleteUserCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserDelegate userDlg = new UserDelegate();
        ReviewSelectUserDelegate rsUserDlg = new ReviewSelectUserDelegate();
        User user = new User();
        user.setId(req.getParameter("id"));
        userDlg.processDelete(user);
        req.setAttribute("users", rsUserDlg.reviewSelectUser());
        return "/pages/cruduser.jsp";
    }
}
