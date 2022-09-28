package edu.curtin.comp2003.rover;
import edu.curtin.comp2003.states.*;
import java.util.Base64;


// Context class.
public class Rover implements Observer
{
    // Variable that keeps track of current state
    private RoverState state;
    // Four states in the pattern
    private RoverState drivingState;
    private RoverState stoppedState;
    private RoverState soilState;
    // API Classes' classfields/objects.
    private EarthComm earthComm;
    private EngineSystem engineSys;
    private Sensors sensor;
    private SoilAnalyser soilAnlsr;
    // Quantities we want to keep track of.    
    private double target_distance; 




    public Rover(EarthComm ec, EngineSystem esys, Sensors sens, SoilAnalyser soil)
    {
        // Similar to the examples given in Lecture Slides.
        drivingState = new DrivingState();
        stoppedState  = new StoppedState();
        soilState  = new SoilAnalysisState();
        earthComm = ec;
        engineSys = esys;
        sensor = sens;
        soilAnlsr = soil;
        target_distance = 0;
        state = stoppedState;
    }

    // Event is sent from main to this class
    // Rover implements Observer interface, & thus implement the 'newReminder' (newAction) method.
    // which splits string and checks if [0] is one of "D" "P" "E" "S" etc and call the right method -- case statement below
    
    // Observer interface method
    public void newAction(String msg)  // 1. New command 
    {
        //System.out.println("Msg = " + msg + ".");
        
        String[] parts = msg.split(" ");        
        //System.out.println("p0 = " + parts[0] + ".");
        //System.out.println("p1 = " + parts[1] + ".");    
        switch(parts[0]) // P []
        {
            case "D":
            {
                state.driveRover(this, msg);
                break;
            }
            case "T":
            {
                state.doTurn(this, msg);
                break;
            }
            case "S":
            {
                state.analyseSoil(this, msg);
                break;
            }

            case "P":
            {
                this.takePicture(msg);
                break;
            }
            case "E":
            {
                this.makeReport();
                break;
            }
            
            /*  // Testing: *works*
            case "R":
            {
                this.soilAnlsr.setData("soil".getBytes());
                break; // Used to see if Soil state can be exited.
            }
            
            case "V":
            {
                this.sensor.setVis(3);
                break; // Used to see if auto-msg is displayed
            }
            case "X":
            {
                this.sensor.setVis(5);
                break; // Used to see if auto-msg is displayed
            } */

            default:
                earthComm.sendMessage("! " + msg);
                break;
        }
    }

    @Override
    public void haltMvmt() { // 2. Destination reached 
        state.stopRover(this, "");
    }

    @Override // "E" / Low vis levels
    public void reportSituation() { this.makeReport(); }

    @Override
    public void publishResults()
    { // When soilAn.pollAnalysis() returns non null, print.
        state.analyseSoil(this, "");
    }

    // ===================================================================
    // ===================================================================
    // Taking a photo ("P") & making a report ("E") is valid regardless of the situation i.e. 
    // Not state dependent behaviour ~ can happen in any state therefore implementing here.
    public void takePicture(String msg)  // "P"
    {
        byte[] byteArr;
        String export;
        // No validation is required.
        byteArr = sensor.takePhoto(); // Process is instantaneous
        // Return message upon finishing photo is to be in Base64.
        export = Base64.getEncoder().encodeToString(byteArr);     
        earthComm.sendMessage("P " + export);
    }

    public void makeReport() // "E"
    {
        // readTemp, readVis, readLL will return doubles ~ all retrieved from Sensors.
        String export = "";

        // Report output: "E <temperature> <visibility> <light lvl>"
        export = String.format("E %.2f %.2f %.2f", sensor.readTemperature(), sensor.readVisibility(), sensor.readLightLevel());

        // Return = environmental status report (which is instantaneous upon it being called).
        earthComm.sendMessage(export);
        
        // CASE: Must also output even if no "E" was requested, if and when sensor.readVisibility() < 4 && > 5
            // However this is dealt by Observer - it will notify observer when vis levels are as such.
            // How does it know vis levels? It does rover.getSensor.getVis and so on. -- one of the events we poll for.
        
    }


    // Important part of pattern.
    // To make one state obsolete, and replace it with another state
    public void setState(RoverState state) { this.state = state; }

    // Getters for each state (to be used in setState() in the State classes)
    public RoverState getDrivingState() { return drivingState; }
    public RoverState getStoppedState() { return stoppedState; }
    public RoverState getSoilState() { return soilState; }


    // Getters for the API Class' Objects. Generated by VSCode.
    public EarthComm getEarthComm() { return earthComm; }

    public EngineSystem getEngineSys() { return engineSys; }

    public Sensors getSensor() { return sensor; }

    public SoilAnalyser getSoilAnlsr() { return soilAnlsr; }

    // Getters & Setters for values we keep track of.

    public double getTarget_distance() { return target_distance; }

    public void setTarget_distance(double target_distance) { this.target_distance = target_distance; }
    
}
