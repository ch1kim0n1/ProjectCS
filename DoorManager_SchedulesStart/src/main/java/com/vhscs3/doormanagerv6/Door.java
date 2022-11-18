/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;

/**
 *
 * @author irad_Allen
 */
public class Door implements Serializable{
    
    Location                            location;
    Schedule[]                          weeklySchedules;
    String                              name;
    boolean                             status;		//Represents the locked/unlocked state
    TreeMap<GregorianCalendar, Badge>   log;
    String                              building;

    public Door(String name, Location location, TreeMap<GregorianCalendar, Badge> log,  Schedule[] weeklySchedules, boolean status, String building) {
        this.location = location;
        this.weeklySchedules = weeklySchedules;
        this.name = name;
        this.status = status;
        this.log = log;
        this.building = building;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLog(TreeMap<GregorianCalendar, Badge> log) {
        this.log = log;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public TreeMap<GregorianCalendar, Badge> getLog() {
        return log;
    }

    public String getBuilding() {
        return building;
    }

    public Schedule[] getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(Schedule[] weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }
    

    @Override
    public String toString() {
        
        String output = name + ": Building " + building + " (" + location.toString() + ")\n";
        output += "\tStatus: " + ((status)? "OPEN" : "CLOSED") + "\n";
        
        output += "\tMonday - "     + weeklySchedules[0].name + "\n";
        output += "\tTuesday - "    + weeklySchedules[1].name + "\n";
        output += "\tWednesday - "  + weeklySchedules[2].name + "\n";
        output += "\tThursday - "   + weeklySchedules[3].name + "\n";
        output += "\tFriday - "     + weeklySchedules[4].name + "\n";
        output += "\tSaturday - "   + weeklySchedules[5].name + "\n";
        output += "\tSunday - "     + weeklySchedules[6].name + "\n";
        
        return output;
    }

}
