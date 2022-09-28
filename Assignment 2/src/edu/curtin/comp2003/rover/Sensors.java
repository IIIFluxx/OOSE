package edu.curtin.comp2003.rover;

public class Sensors
{
    // Sample values.
    private double temp = 1;
    private double vis = 4.5;
    private double lux = 1;
    /** Performs a temperature reading and returns the result in °C. */
    public double readTemperature() { return temp; }
    //public void setTemp(double temp) { this.temp = temp; } // Used for testing purposes.


    /** Performs a visibility reading and returns the result in km. */
    public double readVisibility() { return vis;}
    public void setVis(double vis) { this.vis = vis; }

    /** Performs a light-level reading, and returns the result in lux (units). */
    public double readLightLevel() { return lux;}
    public void setLux(double lux) { this.lux = lux; } // Used for Testing purposes.

    /** Takes a photo and returns the binary data making up the image. */
    public byte[] takePhoto() {return "photo".getBytes();}
}
