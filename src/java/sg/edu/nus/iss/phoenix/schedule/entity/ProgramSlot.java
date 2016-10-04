/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Time;
import java.sql.Timestamp;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Zhai
 */
public class ProgramSlot {
    private User presenter;
    private User producer;
    private Time duration;
    private Timestamp dateOfProgram;
    private Timestamp startTime;
    private String programName;
    
    public ProgramSlot(){
        
    }

    public ProgramSlot(Time duration, Timestamp dateOfProgram) {
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

    public Timestamp getDateOfProgram() {
        return dateOfProgram;
    }


    public Timestamp getStartTime() {
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

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setDateOfProgram(Timestamp dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }
    
    
}
