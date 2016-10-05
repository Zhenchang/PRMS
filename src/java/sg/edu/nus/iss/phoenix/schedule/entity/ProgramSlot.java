/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Time;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Zhai
 */
public class ProgramSlot {
    private User presenter;
    private User producer;
    private Time duration;
    private String dateOfProgram;
    private Time startTime;
    private String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    private String programName;
    
    public ProgramSlot(){
        
    }

    public ProgramSlot(Time duration, String dateOfProgram) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
    }

    public User getPresenter() {
        return presenter;
    }

    public User getProducer() {
        return producer;
    }

    public Time getDuration() {
        return duration;
    }

    public String getDateOfProgram() {
        return dateOfProgram;
    }


    public Time getStartTime() {
        return startTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setPresenter(User presenter) {
        this.presenter = presenter;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }
    
    
}
