/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectRoleService;

/**
 *
 * @author Liu Zhenchang
 */
public class ReviewSelectRoleDelegate {
    
    private ReviewSelectRoleService service = null;
    
    public ReviewSelectRoleDelegate() {
        service = new ReviewSelectRoleService();
    }
    
    /**
     * Retrieve all roles.
     * 
     * @return List of roles.
     */
    public List<Role> reviewSelectRole() {
        return service.reviewSelectRole();
    }
}
