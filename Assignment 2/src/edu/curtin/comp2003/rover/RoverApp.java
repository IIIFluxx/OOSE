package edu.curtin.comp2003.rover;
import java.util.*;

public class RoverApp
{
    private static List<Observer> obsList;
    
    public static void main(String[] args)
    {
        // API Class objects that get injected in
        EarthComm earthComm = new EarthComm();
        EngineSystem engineSys = new EngineSystem();
        Sensors sensor = new Sensors();
        SoilAnalyser soilAnlsr = new SoilAnalyser();
        // List of current observers.
        obsList = new ArrayList<Observer>();

        Rover r = new Rover(earthComm, engineSys, sensor, soilAnlsr);
        addObserver(r);

        // while(true)
            // poll everything (earthComm.poll)
            // perform actions if needed // if statements to check 
            // sleep

        // Event Loop
        while(true)
        {
            // Perform actions IF needed.
            // 4 Events to poll for.

            // 1. New command received (so I'm assuming if valid)
            String comm;
            //comm = null;
            comm = r.getEarthComm().pollCommand();

            // Assignment Logic:
                // in main(): so if command != null  { notifyobserver(command) } which is in a while loop which calls the observer interface's method.
                // Which would send it (the event) to Rover. Rover would implement Observer interface, and then implement the 'newReminder' (newAction) method
                // which would split string and check if [0] is one of "D" "P" "E" "S" etc and call the corresponding method


            // Validate command
            if(comm != null && parseCommand(comm) == true)
            {
                //System.out.println("Sending " + comm);

                // Notify Observer
                notifyObComm(comm);
                // notifyObComm("afbasfasf");  // ✔️ output = ! afbasfasf
                //notifyObComm("D afbasfasf");  // ✔️ output = ! D afbasfasf
                //notifyObComm("T afbasfasf");  // ! T afbasfasf
                //notifyObComm("T 50");  // ✔️
                //notifyObComm("D 50");  // ✔️
                //notifyObComm("P"); // ✔️
                //notifyObComm("E"); // ✔️
            }
            else
            {
                r.getEarthComm().sendMessage("! " + comm);
            }

            // 2. Distance driven - to know when to stop driving i.e.
            if (r.getEngineSys().getDistanceDriven() >= r.getTarget_distance() // >= as we don't know how much distance gets incremented by
            && (r.getEngineSys().getDistanceDriven() != 0 || r.getTarget_distance() != 0))
            {
                notifyObDrive();
            }
            /* else // Used whilst Testing to increment distance variable in EngineSys class
            {
                //System.out.println("inc");
                r.getEngineSys().incrementDistance();
            } */

            // 3. Visibility check -- reporting / instantaneous
            if(r.getSensor().readVisibility() < 4 || r.getSensor().readVisibility() > 5)
            {
                // Notify observer.
                notifyObVis();
            }

            // Results from soil analysis are back
            if(r.getSoilAnlsr().pollAnalysis() != null)
            {
                // Notify observer.
                notifyObRes();
            }

            try { Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }
   
    // PURPOSE: Makes some small checks like ensuring command contains appropriate length
    public static boolean parseCommand(String msg)
    {
        boolean export = true;
        String[] parts = msg.split(" ");
        if(parts.length < 1 || parts.length > 2)
        {
            System.out.println("Invalid");
            export = false;
        }

        // Messy code but protects case if you give 2 arguments for P/E/S ~ counts as invalid.
        if(parts[0].equals("P") || parts[0].equals("E") || parts[0].equals("S"))
        {
            if(parts.length != 1) // E.g. { "S 1" or "S aa" }
            {
                export = false;
            }
        }


        return export;

    }

    // Observer notify methods
    public static void notifyObComm(String msg) 
    {
        for(Observer ob: obsList) { ob.newAction(msg); }
    }

    public static void notifyObDrive() 
    {
        for(Observer ob: obsList) { ob.haltMvmt(); }
    }

    public static void notifyObVis()
    {
        for(Observer ob: obsList) { ob.reportSituation(); }
    }

    public static void notifyObRes()
    {
        for(Observer ob: obsList) { ob.publishResults(); }
    }
    // Add observers
    public static void addObserver(Observer o) {obsList.add(o);}

}