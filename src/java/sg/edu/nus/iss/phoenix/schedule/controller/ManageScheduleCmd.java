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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.delegate.ManageScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author zz
 */
@Action("manageschedule")
public class ManageScheduleCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        try {
            ManageScheduleDelegate manageScheduleDelegate = new ManageScheduleDelegate();
            List<ProgramSlot> programSolts = manageScheduleDelegate.getAllProgramSlots();
            hsr.setAttribute("programsolts", programSolts);
            return "/pages/crudschedule.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(ManageScheduleCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
