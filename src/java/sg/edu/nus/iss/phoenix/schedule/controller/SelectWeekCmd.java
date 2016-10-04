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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.delegate.ManageScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Zaid
 */
@Action("selectweek")
public class SelectWeekCmd implements Perform{

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        try {
            ReviewSelectScheduleDelegate reviewSelectSchedule = new ReviewSelectScheduleDelegate();
            int year = Integer.parseInt(hsr.getParameter("year"));
            List<WeeklySchedule> weeksObj = reviewSelectSchedule.getAllWeek(year);
            List<Timestamp> weeks = new ArrayList<Timestamp>();
            for(int i = 0;i < weeksObj.size();i++){
                weeks.add(weeksObj.get(i).getStartDate());
            }
            hsr.setAttribute("weeks", weeks);
            return "/pages/selectweek.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
