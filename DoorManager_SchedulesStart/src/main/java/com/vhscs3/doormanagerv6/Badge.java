/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.io.Serializable;

/**
 *
 * @author irad_Allen
 */
public class Badge implements Serializable{
    
    int     id;					//Leander ISD Employee Number
    String  position;				//Leander ISD Occupation
    String  name;				//Leander ISD Employee Name

    public Badge(int id, String position, String name) {
        this.id = id;
        this.position = position;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " " + name + " (" + position + ")";
    }
    
}
