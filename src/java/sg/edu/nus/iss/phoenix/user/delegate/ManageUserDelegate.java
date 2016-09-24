/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.ManageUserService;

/**
 *
 * @author zz
 */
public class ManageUserDelegate {
    
    ManageUserService manageUserService;
    
    public ManageUserDelegate(){
        this.manageUserService = new ManageUserService();
    }
    
    public User getUserById(String id) throws NotFoundException, SQLException{
        return this.manageUserService.getUserById(id);
    }
    
    public List<User> getAllPresenter() throws SQLException{
        return this.manageUserService.getAllPresenter();
    }
    
    public List<User> getAllProducer() throws SQLException{
        return this.manageUserService.getAllProducer();
    }
}
