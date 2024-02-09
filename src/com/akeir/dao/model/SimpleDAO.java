/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.model;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Codeiro
 */
public interface SimpleDAO {
    
    boolean execute(Map<String, String> objMap, String sql);
    
    void save(Map<String, String> objMap, String sql) throws SQLException;
    void update(Map<String, String> objMap, String sql) throws SQLException;
    void delete(Map<String, String> objMap, String sql) throws SQLException;
}
