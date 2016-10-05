/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectUserService;

/**
 *
 * @author Vishnu
 */
public class ReviewSelectUserDelegate {
    
    private ReviewSelectUserService service;
    
    public ReviewSelectUserDelegate() {
        service = new ReviewSelectUserService();
    }
    
    /**
     * reviewSelectUser. This method will retrieve all the users in the system.
     * @return List of user.
     */
    public List<User> reviewSelectUser() {
        return service.reviewSelectUser();
    }
    
     /**
     * reviewSelectUser. This method will retrieve the users with specific role.
     * @param role Specific role.
     * @return List of users with specific role.
     */
    public List<User> reviewSelectUser(Role role) {
        return service.reviewSelectUserByRole(role);
    }
}
