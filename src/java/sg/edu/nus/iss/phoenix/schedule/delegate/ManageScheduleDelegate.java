/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ManageScheduleService;

/**
 *
 * @author zz
 */
public class ManageScheduleDelegate {
    
    ManageScheduleService manageScheduleService;
    
    public ManageScheduleDelegate(){
        this.manageScheduleService = new ManageScheduleService();
    }
    /**
     * return all program slots
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    public List<ProgramSlot> getAllProgramSlots() throws SQLException, NotFoundException{
        return this.manageScheduleService.getAllProgramSlots();
    }
    
    public void copyWeeklySchedule(String originWeek, String dateInTargetWeek) throws SQLException, NotFoundException {
        manageScheduleService.processCopyWeeklySchedule(originWeek, dateInTargetWeek);
    }
    /**
     * return all program slots in a specific week
     * @param week
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    public List<ProgramSlot> getAllProgramSlots(String week) throws SQLException, NotFoundException{
        return this.manageScheduleService.getAllProgramSlots(week);
    }
    
    /**
     * create a new program slot
     * @param programSlot
     * @throws SQLException 
     */
    public void processCreate(ProgramSlot programSlot) throws SQLException{
        this.manageScheduleService.processCreate(programSlot);
    }
    
    /**
     * modify the program slot 
     * @param programSlot
     * @param duration
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException 
     */
    public void processModify(ProgramSlot programSlot, Time duration, String dateOfProgram) throws NotFoundException, SQLException{
        this.manageScheduleService.processModify(programSlot, duration, dateOfProgram);
    }
    
    /**
     * delete the program slot 
     * @param startTime
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException 
     */
    public void processDelete(Time startTime, String dateOfProgram) throws NotFoundException, SQLException{
        this.manageScheduleService.processDelete(startTime, dateOfProgram);
    }
}
