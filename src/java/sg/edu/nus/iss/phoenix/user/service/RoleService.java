/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Liu Zhenchang
 */
public class RoleService {
    
    private DAOFactory factory = null;
    private RoleDao dao = null;
    
    public RoleService() {
        factory = new DAOFactoryImpl();
        dao = factory.getRoleDAO();
    }
    
    /**
     * Retrieve the role by name.
     * 
     * @param role Specific role name.
     * @return Specific role object. Return null if no role is found.
     */
    public Role findRole(String role) {
        try {
            return dao.getObject(role);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
