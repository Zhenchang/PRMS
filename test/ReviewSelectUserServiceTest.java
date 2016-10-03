/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import static org.mockito.Mockito.mock;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.service.ManageScheduleService;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;
import sg.edu.nus.iss.phoenix.user.service.ManageUserService;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import static org.mockito.Mockito.mock;

/**
 *
 * @author zz
 */
public class ReviewSelectUserServiceTest {
    
    ManageUserDelegate manageUserDelegate;
    ManageScheduleService manageScheduleService;
    
    public ReviewSelectUserServiceTest() {
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
            UserDao userDao = mock(UserDao.class);
            ManageUserService manageUserService = new ManageUserService(userDao);
            User user = new User("a");
            List<Role> roles = new ArrayList<Role>();
            roles.add(new Role("presenter"));
            user.setRoles(roles);
            User[] users = { user };
            when(userDao.searchMatching(user)).thenReturn(Arrays.asList(users));
            System.out.println("size: "+ manageUserService.getAllPresenter().size());
            Assert.assertTrue(manageUserService.getAllPresenter().size() == 0);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectUserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetProgramSlot(){

        try {
            System.out.println("size: "+this.manageScheduleService.getAllProgramSlots().size());
            Assert.assertTrue(this.manageScheduleService.getAllProgramSlots().size() != 0);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectUserServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
