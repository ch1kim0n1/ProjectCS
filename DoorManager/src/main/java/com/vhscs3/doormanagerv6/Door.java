/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author irad_Allen
 */
public class Door {
    
    Location                location;
    Schedule[]              weeklySchedules;
    String                  name;
    boolean                 status;		//Represents the locked/unlocked state
    TreeMap<GregorianCalendar, Badge>    log;
    String                  building;

    public Door(Location location, Schedule[] weeklySchedules, String name, boolean status, TreeMap<GregorianCalendar, Badge> log, String building) {
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

    public DoorTime getStatus(){
        LocalTime x = LocalTime.now();
        LocalDate y = LocalDate.now();
        int hr = x.getHour();
        int min = x.getMinute();
        
        hr = hr*60;
        int TotMin = min+hr;
        DayOfWeek day = y.getDayOfWeek();  
        int dayNum = day.getValue()-1;
        
        ArrayList<DoorTime> time = new ArrayList<DoorTime>();
        time = weeklySchedules[dayNum].getTimes();
        
        //stuck here
        DoorTime op = time.get(0);//getopen + getclose
        DoorTime cl = time.get(1);
        String opS = op.toString();
        String clS = cl.toString();
        String opHR = opS.substring(0, opS.indexOf(":"));
        String opMIN = opS.substring( opS.indexOf(":")+1, opS.indexOf(":")+3);
        String clHR = opS.substring(0, clS.indexOf(":"));
        String clMIN = opS.substring( clS.indexOf(":")+1, clS.indexOf(":")+3);
        return op;
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
        output += getStatus();
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
