/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduleService;

/**
 *
 * @author zz
 */
public class ReviewSelectScheduleDelegate {
    
    ReviewSelectScheduleService reviewSelectScheduleService;
    
    public ReviewSelectScheduleDelegate(){
        reviewSelectScheduleService = new ReviewSelectScheduleService();
    }
    
    public List<Timestamp> getAllWeek(int year) throws NotFoundException, SQLException{
        return this.reviewSelectScheduleService.getAllWeek(year);
    }
    
    public List<Integer> getAllAnnual() throws NotFoundException, SQLException{
        return this.reviewSelectScheduleService.getAllAnnual();
    }
    
}
