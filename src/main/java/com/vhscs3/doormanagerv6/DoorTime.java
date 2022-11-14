/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author irad_Allen
 */
public class DoorTime implements Serializable{
    
    GregorianCalendar	open;
    GregorianCalendar	close;

    public DoorTime(GregorianCalendar open, GregorianCalendar close) {
        this.open = open;
        this.close = close;
    }

    public GregorianCalendar getOpen() {
        return open;
    }

    public void setOpen(GregorianCalendar open) {
        this.open = open;
    }

    public GregorianCalendar getClose() {
        return close;
    }

    public void setClose(GregorianCalendar close) {
        this.close = close;
    }

    @Override
    public String toString() {
        
        String am_pm = (open.get(Calendar.AM_PM) == 0) ? "am" : "pm";
        int hour = (open.get(Calendar.HOUR)==0)? 12 : open.get(Calendar.HOUR);
        String minute = (open.get(Calendar.MINUTE) < 10)? "0"+open.get(Calendar.MINUTE) : ""+open.get(Calendar.MINUTE);
        String opFormatted = "" + hour+ ":" + minute + am_pm ;
        
        am_pm = (close.get(Calendar.AM_PM) == 0) ? "am" : "pm";
        hour = (close.get(Calendar.HOUR)==0)? 12 : close.get(Calendar.HOUR);
        minute = (close.get(Calendar.MINUTE) < 10)? "0"+close.get(Calendar.MINUTE) : ""+close.get(Calendar.MINUTE);        
        String clFormatted = "" + hour+ ":" + minute + am_pm ;
        
        return opFormatted + ", " + clFormatted;
    }
   
}
