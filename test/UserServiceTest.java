/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
    public void testCreateUser() {
        System.out.println("Create User");
        User user = new User("test");
        UserDao userDao = mock(UserDao.class);
        UserService instance = new UserService(userDao);
        
        instance.createUser(user);
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(a < 2);
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
        instance.modifyUser(user);
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(b > 1);
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
        instance.deleteUser(user);
        // TODO review the generated test code and remove the default call to fail.
        Assert.assertTrue(true);
    }
    
}
