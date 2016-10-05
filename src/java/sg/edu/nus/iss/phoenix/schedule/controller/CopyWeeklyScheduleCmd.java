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
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ManageScheduleDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;

/**
 *
 * @author zz
 */
@Action("copyWeeklySchedule")
public class CopyWeeklyScheduleCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ManageScheduleDelegate delg = new ManageScheduleDelegate();
        String originWeek = req.getParameter("originWeek");
        String dateInTargetWeek = req.getParameter("targetWeek");
        try {
            delg.copyWeeklySchedule(originWeek, dateInTargetWeek);
        } catch (SQLException | NotFoundException ex) {
            Logger.getLogger(CopyWeeklyScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
