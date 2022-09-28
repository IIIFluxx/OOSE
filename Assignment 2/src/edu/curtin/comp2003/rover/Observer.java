package edu.curtin.comp2003.rover;

/**
     * 
     * Purpose: All subscribed observers implement this interface, 
     * so when an event occurs, all observers are notified.
     * e.g. Target driving distance has been reached.
*/

public interface Observer {
    public void newAction(String msg);

    public void haltMvmt();

    public void reportSituation();
    
    public void publishResults();
    
}
