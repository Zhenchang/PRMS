/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.ReviewSelectUserDelegate;

/**
 *
 * @author Vishnu
 */
@Action("manageuser")
public class ManageUserCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ReviewSelectUserDelegate delg = new ReviewSelectUserDelegate();
        List<User> users = delg.reviewSelectUser();
        req.setAttribute("users", users);
        return "/pages/cruduser.jsp";
    }

}
