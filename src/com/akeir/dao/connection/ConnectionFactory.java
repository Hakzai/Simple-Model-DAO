/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Codeiro
 */
public final class ConnectionFactory {
    
    private ConnectionFactory() { }
    
    private static final Logger LOG = Logger.getGlobal();
    
    private static String DRIVER = "com.example.jdbc.Driver";
    private static String URL = "jdbc:example://localhost:3306/Example";
    private static String USER = "root";
    private static String PASS = "123456";
    
    public static final Connection getConnection()
    {
        try
        {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Connection Error", ex);
        }
    }
    
    public static final void closeConnection(Connection conn)
    {
        if(conn != null)
        {
            try 
            {
                conn.close();
            } catch (SQLException ex) {                
                LOG.log(Level.SEVERE, "ConnectionFactory.closeConnection() :: DAO error: {0}", ex);
            }
        }
    }
    
    public static final void closeConnection(Connection conn, PreparedStatement stmt)
    {
        closeConnection(conn);   
        
        if(stmt != null)
        {
            try 
            {
                stmt.close();
            } catch (SQLException ex) {                
                LOG.log(Level.SEVERE, "ConnectionFactory.closeConnection() :: DAO error: {0}", ex);
            }
        }
    }
    
    public static final void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs)
    {
        closeConnection(conn, stmt);  
        
        if(rs != null)
        {
            try 
            {
                rs.close();
            } catch (SQLException ex) {                
                LOG.log(Level.SEVERE, "ConnectionFactory.closeConnection() :: DAO error: {0}", ex);
            }
        }
    }
    
    /*
    * Method must be used in App Start to define variables for DB Connection
    */
    public static void setParametersForDB(String driver, String url, String user, String pass)
    {
        DRIVER = driver;
        URL = url;
        USER = user;
        PASS = pass;
    }
}
