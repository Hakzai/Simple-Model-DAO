/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.crud;

import com.akeir.dao.connection.ConnectionDAO;
import com.akeir.dao.connection.ConnectionFactory;
import com.akeir.dao.model.SimpleDAO;
import com.akeir.dao.constants.DBConstants;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Codeiro
 */
public class SimpleDAOImpl extends ConnectionDAO implements SimpleDAO {

    public SimpleDAOImpl()
    {
        super();
    }
    
    @Override
    public final boolean execute(Map<String, String> objMap, String sql)
    {
        try
        {
            if(sql.toUpperCase().contains(DBConstants.INSERT_STATEMENT))
            {
                save(objMap, sql);
            }
            else if(sql.toUpperCase().contains(DBConstants.UPDATE_STATEMENT))
            {
                update(objMap, sql);
            }
            else if(sql.toUpperCase().contains(DBConstants.DELETE_STATEMENT))
            {
                delete(objMap, sql);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "SimpleDAOImpl.execute() :: DAO error: {0}", ex);
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return true;
    }

    @Override
    public final void save(Map<String, String> objMap, String sql) throws SQLException 
    {
        stmt = conn.prepareStatement(sql);

        int i = 1;
        for(String key : objMap.keySet())
        {
            stmt.setString(i, objMap.get(key));
            i++;
        }            

        stmt.executeUpdate();
    }

    @Override
    public final void update(Map<String, String> objMap, String sql) throws SQLException 
    {
        stmt = conn.prepareStatement(sql);

        int i = 1;
        for(String key : objMap.keySet())
        {
            if(DBConstants.ID_COLUMN_PK.equals(objMap.get(key)))
            {
                continue;
            }
            stmt.setString(i, objMap.get(key));
            i++;
        }                        
        stmt.setString(i, objMap.get(DBConstants.ID_COLUMN_PK));

        stmt.executeUpdate();
    }

    @Override
    public final void delete(Map<String, String> objMap, String sql) throws SQLException 
    {
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, objMap.get(DBConstants.ID_COLUMN_PK));
        stmt.executeUpdate();
    }
}
