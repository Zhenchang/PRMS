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
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;

/**
 *
 * @author zaid
 */
@Action("enterschedule")
public class EnterScheduleDetailsCmd implements Perform{

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse resp) throws IOException, ServletException {
        try {
            ManageUserDelegate manageUserDelegate = new ManageUserDelegate();
            ManageScheduleDelegate manageScheduleDelegate = new ManageScheduleDelegate();
            ProgramSlot programSlot = new ProgramSlot();
            programSlot.setDateOfProgram(hsr.getParameter("dateOfProgram"));
            programSlot.setDuration(Time.valueOf(hsr.getParameter("duration")));
            programSlot.setPresenter(manageUserDelegate.getUserById(hsr.getParameter("presenter")));
            programSlot.setProducer(manageUserDelegate.getUserById(hsr.getParameter("producer")));
            programSlot.setProgramName(hsr.getParameter("programName"));
            programSlot.setStartTime(Time.valueOf(hsr.getParameter("startTime")));
            programSlot.setStartDate(hsr.getParameter("startDate"));
            String ins = hsr.getParameter("ins");
            if(ins.equals("true")){
                manageScheduleDelegate.processCreate(programSlot);
            } else{
                manageScheduleDelegate.processModify(programSlot, programSlot.getDuration(), programSlot.getDateOfProgram());
            }
            /*
            List<ProgramSlot> programSolts = manageScheduleDelegate.getAllProgramSlots();
            hsr.setAttribute("programsolts", programSolts);

            return "/pages/crudschedule.jsp";*/
            ReviewSelectScheduleDelegate reviewSelectSchedule = new ReviewSelectScheduleDelegate();
            List<String> annuals = reviewSelectSchedule.getAllAnnual();
            hsr.setAttribute("annuals", annuals);
            return "/pages/selectannual.jsp";
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(EnterScheduleDetailsCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
