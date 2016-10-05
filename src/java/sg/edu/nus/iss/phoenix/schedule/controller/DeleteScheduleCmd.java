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
import java.sql.Time;
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

/**
 *
 * @author zz
 */
@Action("deleteschedule")
public class DeleteScheduleCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        try {
            if (hsr.getSession().getAttribute("user") == null) {
                return "/pages/login.jsp";
            }

            ManageScheduleDelegate manageScheduleDelegate = new ManageScheduleDelegate();
            
            System.err.println("errrrrrrrrr ---"+hsr.getParameter("startTime"));
            
            
            ProgramSlot slot = new ProgramSlot();
            slot.setDateOfProgram(hsr.getParameter("dateOfProgram"));
            slot.setStartTime(Time.valueOf(hsr.getParameter("startTime")));
            manageScheduleDelegate.processDelete(slot.getStartTime(), slot.getDateOfProgram());
            
            String duration = hsr.getParameter("duration");
            String dateOfProgram = hsr.getParameter("dateOfProgram");
            List<ProgramSlot> programSlot = manageScheduleDelegate.getAllProgramSlots();
            /*
            manageScheduleDelegate.processDelete(Time.valueOf(duration.trim()), Timestamp.valueOf(dateOfProgram));
            hsr.setAttribute("programslots", programSlot);
            return "/pages/crudschedule.jsp";
             */
            ReviewSelectScheduleDelegate reviewSelectSchedule = new ReviewSelectScheduleDelegate();
            List<String> annuals = reviewSelectSchedule.getAllAnnual();
            hsr.setAttribute("annuals", annuals);
            return "/pages/selectannual.jsp";
        } catch (NotFoundException ex) {
            Logger.getLogger(DeleteScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
