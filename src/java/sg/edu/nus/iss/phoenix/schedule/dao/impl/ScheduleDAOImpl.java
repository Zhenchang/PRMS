/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author zz
 */
public class ScheduleDAOImpl implements ScheduleDAO{
    
    private Connection connection;
    private UserDao userDao;
    
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    public ScheduleDAOImpl(){
        this.connection = this.openConnection();
        this.userDao = new UserDaoImpl();
    }

    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    @Override
    public ProgramSlot getObject(Time duration, Timestamp dateOfProgram) throws NotFoundException, SQLException {
        ProgramSlot valueObject = this.createValueObject();
        valueObject.setDuration(duration);
        valueObject.setDateOfProgram(dateOfProgram);
        load(valueObject);
        return valueObject;
    }

    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {
        String sql = "SELECT * FROM `program-slot` WHERE (`duration` = ? AND `dateOfProgram` = ?) ";
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setTimestamp(2, valueObject.getDateOfProgram());
            singleQuery(stmt, valueObject);
        } finally {
            if (stmt != null){
                stmt.close();
            }
        }   
    }

    @Override
    public List<ProgramSlot> loadAll() throws SQLException, NotFoundException {
        String sql = "SELECT * FROM `program-slot` ORDER BY dateOfProgram";
        List<ProgramSlot> searchResults = listQuery(this.connection.prepareStatement(sql));
        return searchResults;
    }

    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO `program-slot` (`duration`, `dateOfProgram`, `startTime`, `program-name`, `presenter`, `producer`)"
                    + " VALUES (?, ?, ?, ?, ?, ?) ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setTime(1, valueObject.getDuration());
            stmt.setTimestamp(2, valueObject.getDateOfProgram());
            stmt.setTimestamp(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setObject(5, valueObject.getPresenter().getId());
            stmt.setObject(6, valueObject.getProducer().getId());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
                if (stmt != null)
                        stmt.close();
        }
    }

    @Override
    public void save(ProgramSlot valueObject, Time duration, Timestamp dateOfProgram) throws NotFoundException, SQLException {

        String sql = "UPDATE `program-slot` SET `duration` = ?, `dateOfProgram` = ?, `startTime` = ?, `program-name` = ?, `presenter` = ?, `producer` = ? WHERE (`duration` = ? AND `dateOfProgram` = ?) ";
        PreparedStatement stmt = null;
        
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setTimestamp(2, valueObject.getDateOfProgram());
            stmt.setTimestamp(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setObject(5, valueObject.getPresenter().getId());
            stmt.setObject(6, valueObject.getProducer().getId());

            stmt.setTime(7, duration);
            stmt.setTimestamp(8, dateOfProgram);

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {
        String sql = "DELETE FROM `program-slot` WHERE (`duration` = ? AND `dateOfProgram` = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setTimestamp(2, valueObject.getDateOfProgram());
            int count = databaseUpdate(stmt);
            if (count == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (count > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
            } finally {
                if (stmt != null)
                    stmt.close();
            }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM `program-slot`";
        PreparedStatement stmt = null;

        try {
            stmt = this.connection.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
            System.out.println("Deleted rows :" + rowcount);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public int countAll() throws SQLException {
        String sql = "SELECT count(*) FROM `program-slot`";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = this.connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRows;
    }

    @Override
    public ProgramSlot searchMatching(Time duration, Timestamp dateOfProgram) throws SQLException {
        try {
            return (getObject(duration, dateOfProgram));
        } catch (NotFoundException ex) {
            logger.log(Level.WARNING, "Fail to find user: {0}", duration.toString() + "and" + dateOfProgram.toString());
        }
        return (null);
    }
    
    private int databaseUpdate(PreparedStatement stmt) throws SQLException {
        
        int result = stmt.executeUpdate();
        return result;
    }
    
    private void singleQuery(PreparedStatement stmt, ProgramSlot valueObject) throws SQLException, NotFoundException{

        try (ResultSet result = stmt.executeQuery()) {

            if (result.next()) {

                valueObject.setDuration(result.getTime("duration"));
                valueObject.setDateOfProgram(result.getTimestamp("dateOfProgram"));
                valueObject.setPresenter(this.userDao.getObject(result.getString("presenter")));
                valueObject.setProducer(this.userDao.getObject(result.getString("producer")));
                valueObject.setProgramName(result.getString("program-name"));
                valueObject.setStartTime(result.getTimestamp("starttime"));

            } else {
                throw new NotFoundException("Program Slot Not Found!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    private List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException, NotFoundException{
        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                ProgramSlot programSlot = createValueObject();
                
                programSlot.setDuration(result.getTime("duration"));
                programSlot.setDateOfProgram(result.getTimestamp("dateOfProgram"));
                programSlot.setPresenter(this.userDao.getObject(result.getString("presenter")));
                programSlot.setProducer(this.userDao.getObject(result.getString("producer")));
                programSlot.setProgramName(result.getString("program-name"));
                programSlot.setStartTime(result.getTimestamp("starttime"));
                searchResults.add(programSlot);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return searchResults;
    }
    
    
    private Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            conn = DriverManager.getConnection(
                    DBConstants.dbUrl, DBConstants.dbUserName,
                    DBConstants.dbPassword);
        } catch (SQLException e) {
        }
        return conn;
    }
    
    private void closeConnection() {
            try {
                this.connection.close();
            } catch (SQLException e) {
        }
    }
}
