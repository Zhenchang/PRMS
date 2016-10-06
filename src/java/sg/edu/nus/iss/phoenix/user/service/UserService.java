/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Liu Zhenchang
 */
public class UserService {
    
    DAOFactory factory;
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public UserService() {
        factory = new DAOFactoryImpl();
        userDao = factory.getUserDAO();
    }
    
    /**
     * createUser. This method will create a new user.
     * @param user 
     * @throws java.sql.SQLException 
     */
    public void createUser(User user) throws SQLException, Exception  {
        boolean isDuplicate = false;
        try {
            isDuplicate = userDao.getObject(user.getId()) != null;
        } catch (NotFoundException ex) {
        }
        if(isDuplicate) throw new Exception("Duplicate Id!");
        userDao.create(user);
    }
    
    /**
     * modifyUser. This method will modify specific user information.
     * @param user The user to be modify. 
     */
    public void modifyUser(User user) {
        try {
            userDao.save(user);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(ReviewSelectUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * deleteUser. This method will delete the specific user.
     * @param user The user to be delete. 
     */
    public void deleteUser(User user) {
        try {
            userDao.delete(user);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(ReviewSelectUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
