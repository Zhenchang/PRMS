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
 * @author zaid
 */
@Action("selectannual")
public class SelectAnnualCmd implements Perform{

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        try {
            ReviewSelectScheduleDelegate reviewSelectSchedule = new ReviewSelectScheduleDelegate();
            List<String> annuals = reviewSelectSchedule.getAllAnnual();
            hsr.setAttribute("annuals", annuals);
            return "/pages/selectannual.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
