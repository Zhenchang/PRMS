/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.user.service.RoleService;

/**
 *
 * @author Liu Zhenchang
 */
public class RoleDelegate {
    
    RoleService service = null;
    
    public RoleDelegate() {
        service = new RoleService();
    }
    
    /**
     * Select role by name.
     * 
     * @param role Name of the role.
     * @return Selected role. Return null if no role is found.
     */
    public Role find(String role) {
        return service.findRole(role);
    }
}
