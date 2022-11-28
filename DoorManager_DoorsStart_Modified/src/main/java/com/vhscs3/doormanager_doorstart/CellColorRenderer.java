/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanager_doorstart;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author twoal
 */
public class CellColorRenderer extends DefaultTableCellRenderer { 
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

        
        String colorString = (String) table.getValueAt(row, 2);
        
        String[] rgb = colorString.split(", ");
        
        int red = Integer.parseInt(rgb[0]);
        int blue = Integer.parseInt(rgb[1]);
        int green = Integer.parseInt(rgb[2]);
        
        Color color = new Color(red, blue, green);

        if (column == 2) {
            c.setBackground(color); 
        } else if (table.getSelectedRow() == row) {
            c.setBackground(Color.LIGHT_GRAY);
        } else {
            c.setBackground(Color.WHITE);
        }
        
        return c; 
    } 

}