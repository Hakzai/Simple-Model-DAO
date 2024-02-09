/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.crud;

import com.akeir.dao.constants.DBConstants;
import com.akeir.dao.connection.ConnectionDAO;
import com.akeir.dao.connection.ConnectionFactory;
import com.akeir.dao.model.ReadOnlyDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Codeiro
 */
public class ReadOnlyDAOImpl extends ConnectionDAO implements ReadOnlyDAO {
    
    public ReadOnlyDAOImpl()
    {
        super();
    }
    
    public boolean execute(List<Map> objMapList, String sql)
    {
        getConnection();
        try
        {
            if(sql.toUpperCase().contains(DBConstants.SELECT_ALL_STAR_SYMBOL))
            {
                listAll(objMapList, sql);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "ReadOnlyDAOImpl.execute() :: DAO error: {0}", ex);
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return true;
    }
    
    public void listAll(List<Map> objMapList, String sql) throws SQLException
    {
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while(rs.next())
        {
            Map<Integer, String> objectMap = new HashMap<>();
            for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
            {
                objectMap.put(i, rs.getString(i));
            }
            objMapList.add(objectMap);
        }
    }
}
