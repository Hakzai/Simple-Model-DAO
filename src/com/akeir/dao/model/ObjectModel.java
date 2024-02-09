/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.dao.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Codeiro
 */
public abstract class ObjectModel {
    
    private final Map<String, String> objectMap = new HashMap<>();
    
    public Map<String, String> getObjectMap()
    {
        return objectMap;
    }
    
    public void setObjectMap(String key, String value)
    {
        objectMap.put(key, value);
    }
}
