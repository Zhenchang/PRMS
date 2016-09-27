/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author zz 
 */
public class ManageScheduleService {
    DAOFactory factory;
    ScheduleDAO scheduleDAO;
    
    public ManageScheduleService(){
        this.factory = new DAOFactoryImpl();
        this.scheduleDAO = new ScheduleDAOImpl();
    }

    /**
     * return all program slots
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    public List<ProgramSlot> getAllProgramSlots() throws SQLException, NotFoundException{
        return this.scheduleDAO.loadAll();
    }
    
    /**
     * return all program slots in a specific week
     * @param week
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    public List<ProgramSlot> getAllProgramSlots(Timestamp week) throws SQLException, NotFoundException{
        return this.scheduleDAO.load(week);
    }
    
    /**
     * create a new program slot 
     * @param programSlot
     * @throws SQLException 
     */
    public void processCreate(ProgramSlot programSlot) throws SQLException{
        this.scheduleDAO.create(programSlot);
    }
    
    /**
     * modify the program slot 
     * @param programSlot
     * @param duration
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException 
     */
    public void processModify(ProgramSlot programSlot, Time duration, Timestamp dateOfProgram) throws NotFoundException, SQLException{
        this.scheduleDAO.save(programSlot, duration, dateOfProgram);
    }
    
    /**
     * delete the program slot
     * @param duration
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException 
     */
    public void processDelete(Time duration, Timestamp dateOfProgram) throws NotFoundException, SQLException{
        ProgramSlot programSlot = new  ProgramSlot(duration, dateOfProgram);
        this.scheduleDAO.delete(programSlot);
    }
    
}
