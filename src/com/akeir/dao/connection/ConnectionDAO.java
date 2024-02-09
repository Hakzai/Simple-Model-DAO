/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Codeiro
 */
public class ConnectionDAO {
    
    public static Logger LOG = Logger.getGlobal();
    
    public ConnectionDAO()
    {
        conn = ConnectionFactory.getConnection();
        rs = null;
    }
    
    public Connection conn;
    public PreparedStatement stmt;
    public ResultSet rs;
    
    public void getConnection()
    {
        try
        {
            if(conn.isClosed())
            {
                conn = ConnectionFactory.getConnection();
            } 
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "ConnectionDAO.getConnection() :: Error on start connection to database: {0}", ex.getMessage());
        }
    }
}
