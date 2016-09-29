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
    
    /**
     * Constructor. It takes no argument and provides the most simple way to create an object instance.
     */
    public ReviewSelectUserService() {
        factory = new DAOFactoryImpl();
        userDao = factory.getUserDAO();
    }
    
    /**
     * reviewSelectUser. This method will return a list of all users in the system.
     * @return List of Users
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
     * reviewSelectUserByRole. his method will return a list of all users in the system based on the role.
     * @param role
     * @return List of Users
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
