/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vhscs3.doormanagerv6;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.time.LocalTime;

/**
 *
 * @author kondraty_946191
 */
public class DataFactory {
    private HashMap<Integer, Badge> badges;
    private HashMap<String, Schedule> schedules;
    private HashMap<String, Door> doors;
    private TreeMap<Integer, LogEntry> logs;

    public DataFactory() {
        buildBadges();
        buildSchedules();
        buildLog();
        buildDoors();
    }

    private void buildBadges() {
        
            /*initializes the badges HashMap with sample data from a dat file
            that includes the employee id, name, and role. The method uses a
            scanner to read the data, then build a Badge object. The object is then
            placed in a HashMap using the employee id as the key, and the badge object as the value.
            */
        try{ 
            badges = new HashMap<>();
            
            Scanner file = new Scanner(new File("sampleData" + File.separator + "badges.txt"));
            
            //first line of data file is column headers. skips line 1 so that
            //data is read beginning with line 2
            file.nextLine();
            
            while (file.hasNextLine()){
                String lineData = file.nextLine();
                
                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                
                int id = Integer.parseInt(line.next());
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
        try{ 
            schedules = new HashMap<>();
            
            Scanner file = new Scanner(new File("sampleData" + File.separator + "schedules.txt"));
            
            String firstLine = file.nextLine();
            System.out.println(firstLine);
            
            while (file.hasNextLine()){
                String lineData = file.nextLine();
                System.out.println(lineData);
                
                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                
                String name = line.next();
                String colorString = line.next();
                String Descrip = line.next();
                String timeS = line.next();
                
                //code to generate color
                //Color color
                Color color = null;
                if(colorString.equals("GRAY")){
                    color = new Color(128,128,128);
                }
                else if (colorString.equals("YELLOW")){
                    color = new Color(255,255,0);
                }
                else if (colorString.equals("PINK")){
                    color = new Color(255,192,20);
                }
                else if (colorString.equals("ORANGE")){
                    color = new Color(255, 165, 0    );
                }
                else if (colorString.equals("BLUE")){
                    color = new Color(0,0,255);
                }
                else if (colorString.equals("PURPLE")){
                    color = new Color(160, 32, 240);
                }
                else if (colorString.equals("RED")){
                    color = new Color(255, 0, 0);
                }
                else if (colorString.equals("GREEN")){
                    color = new Color(0, 255, 0);
                }
                
                //Code to generate the times ArrayList<DoorTime>)
                ArrayList<DoorTime> times = new ArrayList<DoorTime>();
                Scanner scan = new Scanner(timeS);
                scan.useDelimiter(",");
                while(scan.hasNext()){
                    String chunk = scan.next();
                    
                    int opH = Integer.parseInt(chunk.substring(0,2));
                    int opM = Integer.parseInt(chunk.substring(2,4));
                    int clH = Integer.parseInt(chunk.substring(5,7));
                    int clM = Integer.parseInt(chunk.substring(7));
                    
                    GregorianCalendar open = new GregorianCalendar(2022,9,4,opH,opM);
                    GregorianCalendar close = new GregorianCalendar(2022,9,4,clH,clM);
                    times.add(new DoorTime(open, close));
                }
               
                //schedules put(name, schedules);
                //Schedule schedule = new Schedule(Descrip, times, name, color);
                Schedule schedule = new Schedule(color, times, name, Descrip);
                schedules.put(name, schedule);
            }
            
            // Verify that the badges objects are created properly
            for(String key : schedules.keySet()){
                System.out.println(schedules.get(key));
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Badges dat file not found");
        }
    }
    
    private void buildLog(){
       
        try{
            
             logs = new TreeMap<>();
            Scanner logf = new Scanner(new File("sampleData" + File.separator + "logs.txt"));
            
            String firstLineLog = logf.nextLine();
            
            
            while(logf.hasNext()){
                int logNumber = Integer.parseInt(logf.next());
                
                int EmplayeeID = Integer.parseInt(logf.next());
                int hour = Integer.parseInt(logf.next());
                int minute = Integer.parseInt(logf.next());
                int second = Integer.parseInt(logf.next());
                LogEntry logRef = new LogEntry(EmplayeeID, hour, minute, second);

                logs.put(logNumber, logRef);
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("ERROR: Log dat file not found");
        }
    }
    
    private void buildDoors()
    {
        
        try{ 
            TreeMap<GregorianCalendar, Badge> log2 = new TreeMap<>(); 
            doors = new HashMap<>();
            Badge Badge = null;
            Scanner file = new Scanner(new File("sampleData" + File.separator + "doors.txt"));
            //System.out.println(file.nextLine());
            file.nextLine();
            
            //first line of data file is column headers. skips line 1 so that
            //data is read beginning with line 2
            Schedule[] weeklyschedule = new Schedule[7];
            
            while (file.hasNextLine()){
                String lineData = file.nextLine();
                
                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                
                String name = line.next();
                String location = line.next();
                for(int i =0; i < weeklyschedule.length; i++){
                    weeklyschedule[i] = schedules.get(line.next());
                }
                boolean realStatus = false;
                String status = line.next();
                if(status.equals("TRUE")){
                    realStatus = true;
                }
                Scanner logNums = new Scanner(line.next());
                logNums.useDelimiter(", ");
                while(logNums.hasNext())
                {
                    int LogCaseNum = Integer.parseInt(logNums.next());
                    int id = logs.get(LogCaseNum).getWorkerID();
                    Badge = badges.get(id);
                    GregorianCalendar date = logs.get(LogCaseNum).getDate();
                    
                    log2.put(date,Badge);
                }
                
                String building = line.next();
                
                
                Scanner xy = new Scanner(location);
                xy.useDelimiter(", ");
                int x = xy.nextInt();
                int y = xy.nextInt();
                Location location2 = new Location(x, y);
                
                Boolean Status = true;
                if(status.equals("FALSE")){
                    Status =false;
                }

               Door doors2 = new Door(location2, weeklyschedule, name, realStatus, log2, building);
               doors.put(name, doors2); 
                
                
                    
               for(String key : doors.keySet()){
                System.out.println(doors.get(key));
            }
             
        }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Badges dat file not found");
        }
    }    
    
}
