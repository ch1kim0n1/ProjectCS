/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanager_doorstart;

import java.awt.Color;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayWeek = localDate.getDayOfWeek();
        int dayOfWeek = dayWeek.getValue()-1;
        Schedule schedule = weeklySchedules[dayOfWeek];
        
        LocalDateTime localTime = LocalDateTime.now();
        int hourNow = localTime.getHour();
        int minNow = localTime.getMinute();
        GregorianCalendar timeNow  = new GregorianCalendar(2022, 9, 4, hourNow, minNow);
        ArrayList<DoorTime> Times = new ArrayList<DoorTime>(schedule.getTimes());
        for(DoorTime timeCheck: Times){
            GregorianCalendar openTime = timeCheck.getOpen();
            GregorianCalendar closeTime = timeCheck.getClose();

            if (timeNow.after(openTime) && timeNow.before(closeTime)){
                status = true;
            }
            
        }
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
    public Schedule getToday()
    {
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayWeek = localDate.getDayOfWeek();
        int dayOfWeek = dayWeek.getValue()-1;
        return weeklySchedules[dayOfWeek];
    }
    public Schedule getScheduleOfDay(int day)
    {
        return weeklySchedules[day];
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
