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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author Liu Zhenchang
 */
public class UserServiceTest {
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    int a = 1;
    int b = 2;

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() throws NotFoundException, SQLException {
        System.out.println("Create User");
        User user = new User("test");
        UserDao userDao = mock(UserDao.class);
        when(userDao.getObject("test")).thenReturn(null);
        UserService instance = new UserService(userDao);
        try{
            instance.createUser(user);
        } catch(Exception e){
            fail();
        }
        
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(true);
    }
    
      /**
     * Test of createUser method, when there is a duplicate id, in the class UserService.
     */
    @Test
    public void testDuplicateUserValidation() throws NotFoundException, SQLException {
        System.out.println("Create User");
        User user = new User("test");
        UserDao userDao = mock(UserDao.class);
        when(userDao.getObject("test")).thenReturn(new User("test"));
        UserService instance = new UserService(userDao);
        try{
            instance.createUser(user);
            fail();
        } catch(Exception e){
            if(e.getMessage().equals("Duplicate Id!")) Assert.assertTrue(true);
            //Duplicate Id!
        }
     
    }

    /**
     * Test of modifyUser method, of class UserService.
     */
    @Test
    public void testModifyUser() {
        System.out.println("Modify User");
        User user = new User("test");
        UserDao userDao = mock(UserDao.class);
        UserService instance = new UserService(userDao);
        try{
            instance.modifyUser(user);
        } catch(Exception e){
            fail();
        }
        
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(true);
    }

    /**
     * Test of deleteUser method, of class UserService.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        User user = new User("test");
        UserDao userDao = mock(UserDao.class);
        UserService instance = new UserService(userDao);
        try{
            instance.deleteUser(user);
        } catch(Exception e){
            fail();
        }
        
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(true);
    }
    
}
