/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author irad_Allen
 */
public class LogEntry implements Serializable{
    
    private int id;
    private int hour;
    private int minute;
    private int second;

    public LogEntry(int id, int hour, int minute, int second) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public GregorianCalendar getTimeStamp() {
        
        return new GregorianCalendar(2022, 9, 4, hour, minute, second);
    }
    
    public int getEmployeeID() {  
        return id;
    }
    
}
