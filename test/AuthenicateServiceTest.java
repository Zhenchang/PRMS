/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
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
import org.junit.Rule;
import org.mockito.Mock;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Vishnu
 */
public class AuthenicateServiceTest {

    
    public AuthenicateServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
//        authenicateService = new AuthenticateService();
    }
    
    @After
    public void tearDown() {
//        authenicateService = null;
    }
    
    
    @Test
    public void testValidateUserIdPassword() {
        
        User user = new User();
        user.setName("David");
        user.setPassword("david");
        user.setId("a");
        
        RoleDao roleDao = mock(RoleDao.class);
        UserDao userDao = mock(UserDao.class);
        AuthenticateService authenicateService = new AuthenticateService(userDao, roleDao);
        
        try {
            when(userDao.searchMatching("a")).thenReturn(new User("a"));
        } catch (SQLException ex) {
            Logger.getLogger(AuthenicateServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User returnedUser = authenicateService.validateUserIdPassword(user);
        Assert.assertTrue(returnedUser.getId().equals(user.getId()));
        
    }
    
    @Test
    public void testEvaluateAccessPreviledge(){
        
        User user = new User();
        user.setName("Lee");
        user.setPassword("lee");
        Role role = new Role("presenter");
        List<Role> roles = new ArrayList();
        roles.add(role);
        user.setRoles(roles);
        RoleDao roleDao = mock(RoleDao.class);
        UserDao userDao = mock(UserDao.class);
        AuthenticateService authenicateService = new AuthenticateService(userDao, roleDao);
        try {
            when(roleDao.getObject(user.getRoles().get(0).getRole())).thenReturn(new Role("presenter"));
        } catch (Exception ex) {
            Logger.getLogger(AuthenicateServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        User returned = authenicateService.evaluateAccessPreviledge(user);
        Assert.assertTrue(returned == user);
    }
}
