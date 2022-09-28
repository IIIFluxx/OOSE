package edu.curtin.comp2003.states;
import edu.curtin.comp2003.rover.*;
import java.util.*;


public class SoilAnalysisState implements RoverState {

    // All produce errors in State Class. 
    @Override
    public void driveRover(Rover context, String msg) { // ✔️ ERROR
        // Cannot drive whilst in soil state - print error.
        context.getEarthComm().sendMessage("! " + msg);
    }

    @Override
    public void stopRover(Rover context, String msg) { // ✔️ ERROR
        // Already stopped before transitioning to here - print error
        context.getEarthComm().sendMessage("! " + msg);
        
    }

    @Override
    public void analyseSoil(Rover context, String msg) { // ✔️ 
        if(context.getSoilAnlsr().pollAnalysis() != null)
        {
            byte[] byteArr;
            String export = "";

            // Output = "S ..." where ... = byte[] return from poll analysis.
            // No validation is required because
            // **This method only gets called when return from pollAnalysis is NOT null **

            byteArr = context.getSoilAnlsr().pollAnalysis();

            // Return message in Base64.
            export = Base64.getEncoder().encodeToString(byteArr);     
            context.getEarthComm().sendMessage("S " + export);
            context.setState(context.getStoppedState());

            // ASSUMPTION: After polling results != null, this method would be called via observers.
            // I assume that once we display the results once, that the soil analysis results get reset so that
            // Upon next run, pollAnalaysis() will return null instead of previous bytes.
            // For testing purposes I had done: context.getSoilAnlsr().setData(null);
        }
        else
        {
            // Cannot self-transition so print error
            context.getEarthComm().sendMessage("! " + msg);
        }
    }

    @Override
    public void doTurn(Rover context, String msg) { // ✔️ ERROR
        // Cannot turn whilst in soil state - print error.
        context.getEarthComm().sendMessage("! " + msg);
    }   
    
}
