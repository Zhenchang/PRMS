/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;

/**
 *
 * @author Liu Zhenchang
 */
public class ReviewSelectRoleService {
    
    private DAOFactory factory = null;
    private RoleDao dao = null;
    
    public ReviewSelectRoleService() {
        factory = new DAOFactoryImpl();
        dao = factory.getRoleDAO();
    }
    
    /**
     * Select all roles from the system.
     * 
     * @return List of roles.
     */
    public List<Role> reviewSelectRole() {
        List<Role> roles = null;
        try {
            roles = dao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectRoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    } 
}
