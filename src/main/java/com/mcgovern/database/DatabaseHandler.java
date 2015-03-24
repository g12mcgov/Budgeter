package com.mcgovern.database;

/**
 * Created by grantmcgovern on 3/23/15.
 */

import com.mcgovern.logger.LoggingHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.sql.PreparedStatement;

public class DatabaseHandler {
    
    final static String database_host = "localhost";
    final static String database_name = "members";
    final static String database_user = "root";
    final static String database_password = "nantucket";
    
    /* Maintain Database Connection */
    public static Connection conn;
    public static boolean isconnected;
    
    /* Method to check if database is up and running. */
    public static boolean connected() {
        if(isconnected)
            return true;
        else
            return false;
            
    }
    
    public static void establishConnection() {
        String databaseConnection = "jdbc:mysql://" + database_host + "/" + database_name + "?user=" + database_user + "&password=" + database_password;
        System.out.println(databaseConnection);
        
        try {
            conn = DriverManager.getConnection(databaseConnection);

            Statement statement = conn.createStatement();
            
            String query = "SELECT * FROM budget.statements";
            
            ResultSet response = statement.executeQuery(query);
           
            HashMap res = handleResultSet(response);
            
            System.out.println(res);
            
        } catch (SQLException e) {
            LoggingHandler.databaseLogger(e);
        }
    }
    
    private static HashMap handleResultSet(ResultSet input) throws SQLException {

        HashMap result = new HashMap();
        
        while(input.next()) {

            Date date = input.getDate("date");
            int balance = input.getInt("balance");
            int account = input.getInt("account");
            String email = input.getString("email");

            result.put("date", date);
            result.put("balance", balance);
            result.put("account", account);
            result.put("email", email);
        
        }
        
        return result;
    }

    private Date getCurrentDate() {
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        Date dateRepresentation = cal.getTime();
        
        return dateRepresentation;
    }
}
