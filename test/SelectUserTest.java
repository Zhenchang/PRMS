/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.service.ManageScheduleService;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;

/**
 *
 * @author zz
 */
public class SelectUserTest {
    
    ManageUserDelegate manageUserDelegate;
    ManageScheduleService manageScheduleService;
    
    public SelectUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        manageUserDelegate = new ManageUserDelegate();
        manageScheduleService = new ManageScheduleService();
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
    public void testGetPresenter(){
        
        try {
            System.out.println("size: "+this.manageUserDelegate.getAllPresenter().size());
            Assert.assertTrue(this.manageUserDelegate.getAllPresenter().size() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(SelectUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetProgramSlot(){

        try {
            System.out.println("size: "+this.manageScheduleService.getAllProgramSlots().size());
            Assert.assertTrue(this.manageScheduleService.getAllProgramSlots().size() != 0);
        } catch (Exception ex) {
            Logger.getLogger(SelectUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
