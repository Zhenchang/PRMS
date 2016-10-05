/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Ankan
 */
public class ReviewSelectScheduleService {
    DAOFactory factory;
    ScheduleDAO scheduleDAO;

    public ReviewSelectScheduleService(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }
    
    public ReviewSelectScheduleService(){
        this.factory = new DAOFactoryImpl();
        this.scheduleDAO = new ScheduleDAOImpl();
    }
    
    /**
     * return all the weeks in the database in a specific year(Integer)
     * @param year
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public List<String> getAllWeek(String year) throws NotFoundException, SQLException{
        return this.scheduleDAO.getAllWeek(year);
    }
    
    /**
     * return all the year
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public List<String> getAllAnnual() throws NotFoundException, SQLException{
        return this.scheduleDAO.getAllAnnual();
    }
}
