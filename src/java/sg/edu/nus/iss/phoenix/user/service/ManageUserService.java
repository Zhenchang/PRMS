/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author zz
 */
public class ManageUserService {
    
    private DAOFactoryImpl daoFactroy;
    private UserDao userDao;
    
    public ManageUserService(){
        this.daoFactroy = new DAOFactoryImpl();
        this.userDao = new UserDaoImpl();
    }
    
    public User getUserById(String id) throws NotFoundException, SQLException{
        User user = this.userDao.getObject(id);
        return user;
    }
    
    public List<User> getAllPresenter() throws SQLException{
        List<User> users;
        User presenter = new User();
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("presenter"));
        presenter.setRoles(roles);
        users = this.userDao.searchMatching(presenter);
        return users;
    }
    
    public List<User> getAllProducer() throws SQLException{
        List<User> users;
        User presenter = new User();
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("producer"));
        presenter.setRoles(roles);
        users = this.userDao.searchMatching(presenter);
        return users;
    }
}