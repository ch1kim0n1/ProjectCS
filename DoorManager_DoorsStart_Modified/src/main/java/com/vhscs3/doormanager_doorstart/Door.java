/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanager_doorstart;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author irad_Allen
 */
public class Door implements Serializable{
    
    Location            location;
    Schedule[]          weeklySchedules;
    String              name;
    boolean             status;		//Represents the locked/unlocked state
    TreeSet<LogEntry>   log;
    String              building;

    public Door(String name, Location location, TreeSet<LogEntry> log,  Schedule[] weeklySchedules, boolean status, String building) {
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

    public void setLog(TreeSet<LogEntry> log) {
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

    public TreeSet<LogEntry> getLog() {
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
        
        output += "\tMonday - "     + weeklySchedules[0].getName() + "\n";
        output += "\tTuesday - "    + weeklySchedules[1].getName() + "\n";
        output += "\tWednesday - "  + weeklySchedules[2].getName() + "\n";
        output += "\tThursday - "   + weeklySchedules[3].getName() + "\n";
        output += "\tFriday - "     + weeklySchedules[4].getName() + "\n";
        output += "\tSaturday - "   + weeklySchedules[5].getName()+ "\n";
        output += "\tSunday - "     + weeklySchedules[6].getName() + "\n";
        
        return output;
    }

}
