/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

/**
 *
 * @author Liu Zhenchang
 */
public class ReviewSelectUserService {

    DAOFactory factory;
    UserDao userDao;
    
    public ReviewSelectUserService() {
        factory = new DAOFactoryImpl();
        userDao = factory.getUserDAO();
    }
    
    /**
     * Retrieve all users from the system.
     * 
     * @return A list of user. 
     */
    public List<User> reviewSelectUser() {
        List<User> users = new ArrayList();
        try {
            users = userDao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    /**
     * @param role Specific role of the user.
     * @return A list of user with specific role.
     */
    public List<User> reviewSelectUserByRole(Role role) {
        List<User> users = new ArrayList();
        try {
            users = userDao.loadByRole(role);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
