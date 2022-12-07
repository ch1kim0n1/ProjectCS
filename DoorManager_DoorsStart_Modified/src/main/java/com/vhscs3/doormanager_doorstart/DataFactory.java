/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanager_doorstart;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author irad_allen
 */
public class DataFactory implements Serializable{
    
    private HashMap<Integer, Badge>     badges;
    private HashMap<String, Schedule>   schedules;
    private HashMap<String, Door>       doors;
    private HashMap<Integer, LogEntry>  logEntries;
    
    public DataFactory () {
        
        buildBadges();
        buildSchedules();
        buildLogEntries();
        buildDoors();
               
    }

    private void buildBadges() {   
    /* buildBadges() initializes the badges HashMap with sample data from a dat
        file that includes the employee id, name, and role.  The method uses a
        scanner to read the data, then build a Badge object.  The object is then
        placed in a HashMap using the employee id as the key, and the Badge object
        as the value.
    */
            
        try {    
            
            badges = new HashMap<>();
            
            Scanner file = new Scanner(new File("sampleData" + File.separator + "badges.txt"));
            
            // FIrst line of dat file is column headers.  Skips line 1 so that
            // data is read beginnng with line 2
            file.nextLine();
            
            while (file.hasNextLine()) {
                
                String lineData = file.nextLine();
                
                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                
                int id      = Integer.parseInt(line.next());
                String name = line.next();
                String role = line.next();
                
                Badge badge = new Badge(id, role, name);
                
                badges.put(id, badge);
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Badges dat file not found");
        }
     
    }

    private void buildSchedules() {
    /* buildSchedules() initializes the schedules HashMap with sample data 
        from a dat file that includes the name, description, schedule color
        for the graphic display, and times doors are open. The method uses a 
        scanner to read the data, then build a Schedule object.  The object 
        is then placed in a HashMap using the name as the key, and the 
        Schedule object as the value.

        Input Format
        <name>   <description>   <Color> <times - open:close,open:close,...>
    */       
        
        try {
            
            schedules = new HashMap<>();
            Scanner file = new Scanner(new File("sampleData" + File.separator+ "schedules.txt"));
            
            file.nextLine();
            
            while (file.hasNextLine()) {
                
                String lineData = file.nextLine();
                
                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                
                String name         = line.next();
                String colorString  = line.next();      // covert String to a Color object
                String description  = line.next();     
                String timesString  = line.next();      // convert String to an ArrayList<DoorTime>
               
                // Code to generate color
                Color color;
                
                color = switch (colorString) {
                    case "GRAY"     -> Color.GRAY;
                    case "YELLOW"   -> Color.YELLOW;
                    case "PINK"     -> Color.PINK;
                    case "ORANGE"   -> Color.ORANGE;
                    case "BLUE"     -> Color.BLUE;
                    case "PURPLE"   -> Color.MAGENTA;
                    case "RED"      -> Color.RED;
                    default         -> Color.GREEN;
                };
                
                
                // Code to generate the times ArrayList<DoorTime)
                ArrayList<DoorTime> times = new ArrayList<>();
                Scanner scanner = new Scanner(timesString);
                scanner.useDelimiter(",");
                
                while (scanner.hasNext()) {
                    
                    String chunk = scanner.next();          // holds open and close times in 24 hour format. Each time is seprated by a colon
                    
                    int colon = chunk.indexOf(":");
                    
                    // Gets the open time in 24 hour format and converts to a GregorianCalendar object on 10/4/2022 with data time
                    String openChunk       = chunk.substring(0,colon);
                    int openHour           = Integer.parseInt(openChunk.substring(0,2));
                    int openMinute         = Integer.parseInt(openChunk.substring(2));
                    GregorianCalendar open = new GregorianCalendar(2022,9,4,openHour, openMinute);
                    
                    // Gets the close time in 24 hour format and converts to a GregorianCalendar object on 10/4/2022 with data time
                    String closeChunk       = chunk.substring(colon+1);
                    int closeHour           = Integer.parseInt(closeChunk.substring(0,2));
                    int closeMinute         = Integer.parseInt(closeChunk.substring(2));
                    GregorianCalendar close = new GregorianCalendar(2022,9,4,closeHour, closeMinute);
                    
                    DoorTime time = new DoorTime(open, close);
                    
                    times.add(time);
                }

                Schedule schedule = new Schedule(name, description, color, times);
                schedules.put(name, schedule);
                
            }
   
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Schedules data file not found.");
        }
 
    }

    @SuppressWarnings("empty-statement")
    private void buildDoors() {
    /* buildSchedules() initializes the doors HashMap with sample data 
        from a dat file that includes the name, location, Schedule[7], building 
        and times doors are open. The method uses a scanner to read the data, 
        then build a door object.  The object is then placed in a HashMap using 
        the name as the key, and the Door object as the value.
        
        Method uses a helper LogEntry class to help sync up a log entry in the
        Door object with log data for that entry.  The HashMap<Integer, LogEntry>
        object will hold the log number as the key, and a LogEntry object that 
        specifies the badge id and hour, minute, second, second fraction for a
        GregorianCalendar timestamp.

        Input Format
        <name>  <Location>  <Schedule[0>..<Schedule[6], <logNumbers>
    */         
         
        try {

            doors = new HashMap<>();
            
            Scanner file = new Scanner(new File("sampleData" + File.separator + "doors.txt"));
            
            file.nextLine();
            
            while (file.hasNextLine()) {
                
                Scanner line = new Scanner(file.nextLine()) ;
                line.useDelimiter("\t");
                
                Schedule[] weeklySchedules = new Schedule[7];
                
                //Name Location MondayTuesdayWednesdayThursdayFridaySaturdaySunday Status Log Building
                
                String name             = line.next();
                String locationString   = line.next();
                
                // Create the schedule for each day of the week
                for (int i=0; i<weeklySchedules.length; i++) {
                  weeklySchedules[i] = schedules.get(line.next());
                }
                
                String status           = line.next();
                String log              = line.next();;
                String building         = line.next();
                
                // Parse locationString for x and y coordinates                
                String[] time = locationString.split(", ");
                int locX = Integer.parseInt(time[0]);
                int locY = Integer.parseInt(time[1]);
                Location location = new Location(locX, locY);
                
                // Build Door.log based on log id in the logEntries HashMap
                TreeSet<LogEntry> logs = new TreeSet<>();
                
                Scanner logString = new Scanner(log);
                logString.useDelimiter(", ");
                
                while (logString.hasNext()) {                   
                    LogEntry logEntry = logEntries.get(Integer.parseInt(logString.next()));
                    logs.add(logEntry);
                }
                
                doors.put(name, new Door(name, location, logs, weeklySchedules, false, building));
                
            }
 
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Door data file not found.");
        }     
        
    }

    private void buildLogEntries() {
    /*  buildLogEntries() reads the data from the logs data file and builds
            LogEntry objects. These objects are used when createing the Door objects
            to reference specific timestamps associated with badges.  All LogEntry is
            stored in a HashMap<Integer, LogEntry> where the key is the log id, and
            the specific log timestamp associeted with the loge id is the LogEntry.
    */    
         
        try {

            logEntries = new HashMap<>();
            
            Scanner file = new Scanner(new File("sampleData" + File.separator + "logs.txt"));
            file.nextLine();
            
            while (file.hasNextLine()) {
                
                Scanner line = new Scanner(file.nextLine()) ;
                line.useDelimiter("\t");
                
                int logID       = Integer.parseInt(line.next());
                int employeeId  = Integer.parseInt(line.next());
                int hour        = Integer.parseInt(line.next());
                int minute      = Integer.parseInt(line.next());
                int second      = Integer.parseInt(line.next());
                
                logEntries.put(logID, new LogEntry(employeeId , hour, minute, second));
                
            }
 
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
               
    }
    
    public DoorManagerModel getModel(){      
        return new DoorManagerModel(doors, schedules, badges);
    }
    
  
}
