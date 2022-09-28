package edu.curtin.comp2003.rover;

public class SoilAnalyser
{
    /**
     * Begins a soil analysis. The soil analysis will complete some time later, 
     * and its result can be retrieved by calling pollAnalysis().
     *
     * If startAnalysis() is called while analysis is already underway, it will 
     * throw an exception.
     */
    private byte[] data = null;
    
    // public void setData(byte[] data) { this.data = data; } // Used for testing purposes

    public void startAnalysis() {System.out.println("Started soil analysis");}

    /**
     * Retrieves the results of a soil analysis, if they're ready yet. If no new 
     * results have been produced, this method returns null.
     */
    public byte[] pollAnalysis() {return data;}
    //public byte[] pollAnalysis(byte[] thing) {return null;}
}