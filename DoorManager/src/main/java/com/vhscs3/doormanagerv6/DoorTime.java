/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author irad_Allen
 */
public class DoorTime {
    
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
        String opFormatted = "" + open.get(Calendar.HOUR) + ":" + open.get(Calendar.MINUTE) + am_pm ;
        
        am_pm = (close.get(Calendar.AM_PM) == 0) ? "am" : "pm";
        String clFormatted = "" + close.get(Calendar.HOUR) + ":" + close.get(Calendar.MINUTE) + am_pm ;
        
        return opFormatted + ", " + clFormatted;
    }
   
}
