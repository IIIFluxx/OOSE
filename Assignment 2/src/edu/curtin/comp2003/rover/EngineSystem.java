package edu.curtin.comp2003.rover;

public class EngineSystem
{
    private double distance = 0;

    public void startDriving() { System.out.println("Started driving rover");}

    /**
     * Stops driving.
     *
     * If stopDriving() is called while the rover is already stopped, it will 
     * throw an exception.
     */
    public void stopDriving() { System.out.println("Stopped rover");}

    /**
     * Immediately turns the rover by the specified angle anticlockwise (negative
     * for clockwise).
     */
    public void turn(double angle) {System.out.println("Turned " + angle + "degrees");}

    /**
     * Returns the total distance that the rover has ever driven, since it first 
     * landed on Mars. This figure is never reset. It remains constant while the 
     * rover is stopped, and increases while the rover is driving. 
     */     
    
    // ASSUMPTION that API method would return updated distance double - returned double is used to set Target distance in Rover.
    public double getDistanceDriven() {return distance;} // Used for retrieving distance.
    // public void incrementDistance() {distance++;} // Used for testing purposes.
    // public void setDistance(double distance) { this.distance = distance; }
}