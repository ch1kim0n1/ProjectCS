/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author irad_allen
 */
public class DoorManagerModel implements Serializable {
    
        HashMap<String, Door>       doors;             //Collection of doors, door name is the key
        HashMap<String, Schedule>   schedules;         //Collection of schedules, schedule name is the key
        HashMap<Integer, Badge>     badges;

    public DoorManagerModel(HashMap<String, Door> doors, HashMap<String, Schedule> schedules, HashMap<Integer, Badge> badges) {
        this.doors = doors;
        this.schedules = schedules;
        this.badges = badges;
    }
        
    
}
