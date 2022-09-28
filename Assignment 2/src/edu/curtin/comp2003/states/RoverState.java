package edu.curtin.comp2003.states;
import edu.curtin.comp2003.rover.*;

/**
     * 
     * Purpose: Staet keeps track of the Rover's current state (mode of operation). 
     * These methods invoke a new potential transition
*/

public interface RoverState 
{
    public void driveRover(Rover rover, String msg);
    public void stopRover(Rover rover, String msg);
    public void doTurn(Rover rover, String msg);
    public void analyseSoil(Rover rover, String msg);
}
