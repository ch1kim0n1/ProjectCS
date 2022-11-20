/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.util.GregorianCalendar;

/**
 *
 * @author kondraty_946191
 */
public class LogEntry 
{
    private int id;
    private int hr;
    private int min;
    private int sec;

    public LogEntry(int id, int hr, int min, int sec) {
        this.id = id;
        this.hr = hr;
        this.min = min;
        this.sec = sec;
    }
    
    public GregorianCalendar getDate(){
        return new GregorianCalendar(2022, 9, 4, hr, min, sec);
    }
    
    public int getWorkerID(){
        return id;
    }
    
}
