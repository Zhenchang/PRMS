/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;

/**
 *
 * @author Zhai
 */
@Action("copyscheudle")
public class CopyScheduleCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        try {
            if(hsr.getSession().getAttribute("user") == null){
                return "/pages/login.jsp";
            }
            ProgramDelegate programDelegate = new ProgramDelegate();
            ManageUserDelegate manageUserDelegate = new ManageUserDelegate();
            hsr.setAttribute("program", programDelegate.findAllRP());
            hsr.setAttribute("producer", manageUserDelegate.getAllProducer());
            hsr.setAttribute("presenter", manageUserDelegate.getAllPresenter());
            return "/pages/setupschedule.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(CopyScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
