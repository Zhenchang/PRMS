/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ManageScheduleService;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduleService;

/**
 *
 * @author zz
 */
public class ReviewSelectScheduleTest {
    
    ReviewSelectScheduleService reviewSelectScheduleService;
    ProgramSlot programSlot;
    
    public ReviewSelectScheduleTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.reviewSelectScheduleService = new ReviewSelectScheduleService();
        this.programSlot = new ProgramSlot();
        this.programSlot.setDateOfProgram(Timestamp.valueOf("2016-8-9 00:30:00"));
        this.programSlot.setDuration(Time.valueOf("00:30:00"));
        User presenter = new User();
        presenter.setId("presenter");
        Role role = new Role("presenter");
        ArrayList<Role> roles = new ArrayList();
        roles.add(role);
        presenter.setRoles(roles);
        this.programSlot.setPresenter(presenter);
        User producer = new User();
        producer.setId("presenter");
        Role role1 = new Role("producer");
        ArrayList<Role> roles1 = new ArrayList();
        roles.add(role1);
        presenter.setRoles(roles1);
        this.programSlot.setProducer(producer);
        this.programSlot.setProgramName("ppk");
        this.programSlot.setStartTime(Timestamp.valueOf("2016-8-9 00:30:00"));
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGetAllWeeks(){
        try {
            List<WeeklySchedule> list = this.reviewSelectScheduleService.getAllWeek(2016);
            Assert.assertTrue(list.size() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(ScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * test the getAllProgramSlotsByTimestamp
     */
    @Test
    public void testGetAllAnnual(){
        try {
            Timestamp timestamp;
            timestamp = Timestamp.valueOf("2016-9-27 00:30:00");
            List<AnnualSchedule> list = this.reviewSelectScheduleService.getAllAnnual();
            Assert.assertTrue(list.size() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(ScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}