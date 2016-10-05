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
import java.util.Calendar;
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
    /**
     * create an empty program slot
     * @return program slot object
     */
    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    /**
     * get the program slot
     * @param duration
     * @param dateOfProgram
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    @Override
    public ProgramSlot getObject(Time duration, String dateOfProgram) throws NotFoundException, SQLException {
        ProgramSlot valueObject = this.createValueObject();
        valueObject.setDuration(duration);
        valueObject.setDateOfProgram(dateOfProgram);
        load(valueObject);
        return valueObject;
    }

    /**
     * 
     * @param valueObject
     * @throws NotFoundException
     * @throws SQLException 
     */
    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {
        String sql = "SELECT * FROM `program-slot` WHERE (`duration` = ? AND `dateOfProgram` = ?) ";
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram().toString());
            singleQuery(stmt, valueObject);
        } finally {
            if (stmt != null){
                stmt.close();
            }
        }   
    }
    
    /**
     * load whole week's program slot
     * @param week
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public List<ProgramSlot> load(String week) throws NotFoundException, SQLException {
        String sql = "SELECT * FROM `program-slot` WHERE `startDate` = ?  ";
        PreparedStatement stmt = null;
        List<ProgramSlot> searchResults;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, week);
            searchResults = listQuery(stmt);
        } finally {
            if (stmt != null){
                stmt.close();
            }
        }   
        return searchResults;
    }
    

    /**
     * load all the program slot in database
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    @Override
    public List<ProgramSlot> loadAll() throws SQLException, NotFoundException {
        String sql = "SELECT * FROM `program-slot` ORDER BY dateOfProgram";
        List<ProgramSlot> searchResults = listQuery(this.connection.prepareStatement(sql));
        return searchResults;
    }

    /**
     * create new record in table
     * @param valueObject
     * @throws SQLException 
     */
    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        try {
            this.checkFather(valueObject);
            sql = "INSERT INTO `program-slot` (`duration`, `dateOfProgram`, `startTime`, `program-name`, `presenter`, `producer`, `startDate`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setTime(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram());
            stmt.setTime(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setObject(5, valueObject.getPresenter().getId());
            stmt.setObject(6, valueObject.getProducer().getId());
            stmt.setString(7, valueObject.getStartDate());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } catch (NotFoundException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                if (stmt != null)
                        stmt.close();
        }
    }
    
    /**
     * check week and year if exists
     * @param programSlot
     * @throws SQLException
     * @throws NotFoundException 
     */
    private void checkFather(ProgramSlot programSlot) throws SQLException, NotFoundException{
        if(this.checkYear(programSlot.getDateOfProgram()) == -1){
            this.createYear(this.getYear(programSlot.getDateOfProgram()), programSlot.getProducer().getId());
        }
        if(this.checkWeek(programSlot.getStartDate()) == null){
            this.createWeek(this.getYear(programSlot.getDateOfProgram()), programSlot.getProducer().getId(), programSlot.getStartDate());
        }
    }

    /**
     * create year in the annual-schedule table
     * @param year
     * @param assignedBy
     * @throws SQLException 
     */
    private void createYear(String year, String assignedBy) throws SQLException{
        String sql = "";
        PreparedStatement stmt = null;
        
        try {
            sql = "insert into `annual-schedule`(year, assingedBy) values(?, ?); ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, year);
            stmt.setString(2, assignedBy);

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

    /**
     * create week in the week-schedule table
     * @param year
     * @param assignedBy
     * @param startDate
     * @throws SQLException 
     */
    private void createWeek(String year, String assignedBy, String startDate) throws SQLException{
        String sql = "";
        PreparedStatement stmt = null;
        
        try {
            sql = "insert into `weekly-schedule`(startDate, assignedBy, year) values(?, ?, ?); ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, startDate);
            stmt.setString(2, assignedBy);
            stmt.setString(3, year);

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

    /**
     * check if the annual is already exits in the table,
     * if exists return the number of the year,
     * else return -1
     * @param startTime
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    private int checkYear(String date) throws SQLException, NotFoundException{
        List<String> annuals = this.getAllAnnual();
        String year = this.getYear(date);
        
        for(int i = 0;i < annuals.size();i++){
            if(year.equals(annuals.get(i))){
                return 1;
            }
        }
        return -1;
    }

    /**
     *     check if the week is already exits in the table,
     * if exists return the timestamp of the week startDate in the table,
     * else return -1
     * @param startTime
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    private String checkWeek(String startDate) throws SQLException, NotFoundException{
        List<String> weekstamps = this.getAllWeek(this.getYear(startDate));
        for(int i = 0; i < weekstamps.size();i++){
            if(weekstamps.get(i).equals(startDate))
                return weekstamps.get(i);
        }
        return null;
    }

    /**
     * return the year by given the timestamp
     * @param startTime
     * @return 
     */
    private String getYear(String date){
        return date.split("-")[0];
    }

    /**
     * return the week of the year by given the timestamp
     * @param startTime
     * @return 
     */
    private int getWeek(Timestamp startTime){
        long timestamp = startTime.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 
     * @param valueObject
     * @param duration
     * @param dateOfProgram
     * @throws NotFoundException
     * @throws SQLException 
     */
    @Override
    public void save(ProgramSlot valueObject, Time duration, String dateOfProgram) throws NotFoundException, SQLException {

        String sql = "UPDATE `program-slot` SET `duration` = ?, `dateOfProgram` = ?, `startTime` = ?, `program-name` = ?, `presenter` = ?, `producer` = ? WHERE (`duration` = ? AND `dateOfProgram` = ?) ";
        PreparedStatement stmt = null;
        
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram().toString());
            stmt.setTime(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setObject(5, valueObject.getPresenter().getId());
            stmt.setObject(6, valueObject.getProducer().getId());

            stmt.setTime(7, duration);
            stmt.setString(8, dateOfProgram.toString());

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

    /**
     * delete the record in database
     * @param valueObject
     * @throws NotFoundException
     * @throws SQLException 
     */
    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {
            String sql = "DELETE FROM `program-slot` WHERE (`startTime` = ? AND `dateOfProgram` = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getStartTime());
            stmt.setString(2, valueObject.getDateOfProgram());
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

    /**
     * clear the program slot table
     * @throws SQLException 
     */
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

    /**
     * get the record number in program slot table
     * @return
     * @throws SQLException 
     */
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

    /**
     * search program slot
     * @param duration
     * @param dateOfProgram
     * @return ProgramSlot object or null if not found
     * @throws SQLException 
     */
    @Override
    public ProgramSlot searchMatching(Time duration, String dateOfProgram) throws SQLException {
        try {
            return (getObject(duration, dateOfProgram));
        } catch (NotFoundException ex) {
            logger.log(Level.WARNING, "Fail to find user: {0}", duration.toString() + "and" + dateOfProgram.toString());
        }
        return (null);
    }
    
    /**
     * update the database
     * @param stmt
     * @return result set
     * @throws SQLException 
     */
    private int databaseUpdate(PreparedStatement stmt) throws SQLException {
        
        int result = stmt.executeUpdate();
        return result;
    }
    
    /**
     * query the table
     * @param stmt
     * @param valueObject
     * @throws SQLException
     * @throws NotFoundException 
     */
    private void singleQuery(PreparedStatement stmt, ProgramSlot valueObject) throws SQLException, NotFoundException{

        try (ResultSet result = stmt.executeQuery()) {

            if (result.next()) {

                valueObject.setDuration(result.getTime("duration"));
                valueObject.setDateOfProgram(result.getDate("dateOfProgram").toString());
                valueObject.setPresenter(this.userDao.getObject(result.getString("presenter")));
                valueObject.setProducer(this.userDao.getObject(result.getString("producer")));
                valueObject.setProgramName(result.getString("program-name"));
                valueObject.setStartTime(result.getTime("starttime"));

            } else {
                throw new NotFoundException("Program Slot Not Found!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    /**
     * get all annual in db
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public List<String> getAllAnnual() throws NotFoundException, SQLException {
        String sql = "select year from `annual-schedule` ";
        List<String> annuals = new ArrayList<String>();
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            ResultSet result= stmt.executeQuery();
            while (result.next()) {
                annuals.add(result.getString("year"));
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return annuals;
    }
    
    /**
     * get all week in a year in the db
     * @param year
     * @return
     * @throws NotFoundException
     * @throws SQLException 
     */
    public List<String> getAllWeek(String year) throws NotFoundException, SQLException {
        String sql = "select startDate from `weekly-schedule` where year = ?";
        List<String> weeks = new ArrayList<String>();
        PreparedStatement stmt = null;
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, year);
            ResultSet result= stmt.executeQuery();
            while (result.next()) {
                weeks.add(result.getString("startDate"));
            }

        } finally {
            if (stmt != null)
                stmt.close();
        }
        return weeks;
    }
    
    /**
     * query multiple record from the table
     * @param stmt
     * @return
     * @throws SQLException
     * @throws NotFoundException 
     */
    private List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException, NotFoundException{
        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        try (ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                ProgramSlot programSlot = createValueObject();
                
                programSlot.setDuration(result.getTime("duration"));
                programSlot.setDateOfProgram(result.getDate("dateOfProgram").toString());
                programSlot.setPresenter(this.userDao.getObject(result.getString("presenter")));
                programSlot.setProducer(this.userDao.getObject(result.getString("producer")));
                programSlot.setProgramName(result.getString("program-name"));
                programSlot.setStartTime(result.getTime("starttime"));
                searchResults.add(programSlot);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return searchResults;
    }
    
    /**
     * open db connection
     * @return 
     */
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
