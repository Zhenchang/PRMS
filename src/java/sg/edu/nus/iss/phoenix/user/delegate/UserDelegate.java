/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author Liu Zhenchang
 */
public class UserDelegate {
    
    UserService service = null;
    
    public UserDelegate() {
        service = new UserService();
    }
    
    /**
     * Modify user.
     * 
     * @param user User information to be updated.
     */
    public void processModify(User user) {
        service.modifyUser(user);
    }
    
    /**
     * Delete the specific user.
     * 
     * @param user User to be deleted.
     */
    public void processDelete(User user) {
        service.deleteUser(user);
    }
    
    /**
     * Create a new user.
     * 
     * @param user User to be created. 
     */
    public void processCreate(User user) {
        service.createUser(user);
    }
}
