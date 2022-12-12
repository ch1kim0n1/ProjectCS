/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanager_doorstart;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author irad_Allen
 */
public class Schedule implements Serializable{
    
    private java.awt.Color      color;			//color displayed on the school map
    private ArrayList<DoorTime> times;
    private String              name;
    private String              description;

    public Schedule(String name, String description, Color color, ArrayList<DoorTime> times ) {
        this.color = color;
        this.times = times;
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<DoorTime> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<DoorTime> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        
        String output = name + " (" + color.getRGB() + ")\n";
        output += "\t" + description + "\n";
        
        for (DoorTime time : times) {
            output += "\t" + time.toString() + "\n";
        }
               
        return output;
    }
       

}
