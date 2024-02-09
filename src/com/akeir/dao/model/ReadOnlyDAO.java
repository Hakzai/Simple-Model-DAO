/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Codeiro
 */
public interface ReadOnlyDAO {
    
    boolean execute(List<Map> objMapList, String sql);
    
    void listAll(List<Map> objMapList, String sql) throws SQLException;
}
