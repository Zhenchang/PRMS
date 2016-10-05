/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.util.DateUtil;

/**
 *
 * @author zz
 */
public class ManageScheduleService {

    DAOFactory factory;
    ScheduleDAO scheduleDAO;

    public ManageScheduleService() {
        this.factory = new DAOFactoryImpl();
        this.scheduleDAO = new ScheduleDAOImpl();
    }

    /**
     * getAllProgramSlots. This method will return all the program slots.
     *
     * @return List of Program Slots
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<ProgramSlot> getAllProgramSlots() throws SQLException, NotFoundException {
        return this.scheduleDAO.loadAll();
    }

    /**
     * return all program slots in a specific week
     *
     * @param week
     * @return
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<ProgramSlot> getAllProgramSlots(String week) throws SQLException, NotFoundException {
        return this.scheduleDAO.load(week);
    }

    /**
     * create a new program slot
     *
     * @param programSlot
     * @throws SQLException
     */
    public void processCreate(ProgramSlot programSlot) throws SQLException {
        this.scheduleDAO.create(programSlot);
    }

    /**
     *
     * @param originWeek
     * @param dateInTargetWeek
     * @throws SQLException
     * @throws NotFoundException
     */
    public void processCopyWeeklySchedule(String originWeek, String dateInTargetWeek) throws SQLException, NotFoundException {
        System.out.println("dateInTargetWeek" + dateInTargetWeek);
        String firstDayInTargetWeek = DateUtil.getDateInWeek(DateUtil.getWeekByDate(dateInTargetWeek), Integer.parseInt(dateInTargetWeek.split("-")[0]))[0];
        List<ProgramSlot> slots = this.scheduleDAO.load(originWeek);
        Calendar cal = Calendar.getInstance();
        String[] strs = originWeek.split("-");
        cal.set(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
        Date originWeekDate = cal.getTime();
        strs = firstDayInTargetWeek.split("-");
        cal.set(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
        Date targetWeekDate = cal.getTime();
        int diff = (int)TimeUnit.MILLISECONDS.toDays(targetWeekDate.getTime() - originWeekDate.getTime());

        System.out.println("originWeekDate:" + originWeek);
        System.out.println("dateInTargetWeek:" + dateInTargetWeek);
        System.out.println("firstDayInTargetWeek:" + firstDayInTargetWeek);
        System.out.println("difference:" + diff);

        for (int i = 0; i < slots.size(); i++) {
            ProgramSlot slot = slots.get(i);
            slot.setStartDate(firstDayInTargetWeek);
            //Set dateOfProgram
            strs = slot.getDateOfProgram().split("-");
            cal.set(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
            cal.add(Calendar.DAY_OF_MONTH, diff);
            slot.setDateOfProgram(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH));
            this.scheduleDAO.create(slot);
        }
    }

    /**
     * processModify . This method will start the process of modifying an
     * existing program slot.
     *
     * @param programSlot
     * @param duration
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException
     */
    public void processModify(ProgramSlot programSlot, Time duration, String dateOfProgram) throws NotFoundException, SQLException {
        this.scheduleDAO.save(programSlot, duration, dateOfProgram);
    }

    /**
     * processDelete . This method will start the process of deleting an
     * existing program slot.
     *
     * @param startTime
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException
     */
    public void processDelete(Time startTime, String dateOfProgram) throws NotFoundException, SQLException {
        ProgramSlot programSlot = new ProgramSlot();
        programSlot.setStartTime(startTime);
        programSlot.setDateOfProgram(dateOfProgram);
        this.scheduleDAO.delete(programSlot);
    }

}
