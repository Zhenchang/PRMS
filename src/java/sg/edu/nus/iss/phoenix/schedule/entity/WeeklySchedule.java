/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Timestamp;

/**
 *
 * @author zz
 */
public class WeeklySchedule {
    private int year;
    private String assignedBy;
    private Timestamp startDate;

    public WeeklySchedule(int year, String assignedBy, Timestamp startDate) {
        this.year = year;
        this.assignedBy = assignedBy;
        this.startDate = startDate;
    }
    
    

    public int getYear() {
        return year;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    
    public WeeklySchedule(Timestamp time){
        this.startDate = time;
    }
    
    
}
