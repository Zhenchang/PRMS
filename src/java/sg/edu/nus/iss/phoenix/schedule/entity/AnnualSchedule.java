/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 *
 * @author Zhai
 */
public class AnnualSchedule {
    private int year;
    private String assignedBy;
    
    public AnnualSchedule(int year, String assignedBy){
        this.year = year;
        this.assignedBy = assignedBy;
    }

    public int getYear() {
        return year;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
    
    public AnnualSchedule(int year){
        this.year = year;
    }
}
