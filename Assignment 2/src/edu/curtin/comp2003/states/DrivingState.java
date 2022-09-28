package edu.curtin.comp2003.states;
import edu.curtin.comp2003.rover.*;

public class DrivingState implements RoverState {

    // State Pattern means now I can do e.g. context.getEngineSys().startDriving();
    // and e.g. context.setState(rover.getStoppedState());   to make state transitions.
    
    @Override
    public void driveRover(Rover context, String msg) {     // ✔️
        // Just updates the target distance upon it being called
        // First validate msg to make sure it is format: "D <positive number>"
        String[] parts = msg.split(" ");
        try
        {
            double distance = Double.parseDouble(parts[1]);

            if(parts[0].equals("D") && distance > 0)
            {
                // Then if valid, set distance as follows:
                context.setTarget_distance(context.getEngineSys().getDistanceDriven() + distance);
                //System.out.println("Target set to " + context.getTarget_distance());
                // Else continue driving. | No need for context.getEngineSys().startDriving(); because alr driving
            }
            else
            {
                context.getEarthComm().sendMessage("! " + msg);
            }
        }
        // Catch errors
        catch(NumberFormatException e)
        {
            context.getEarthComm().sendMessage("! " + msg);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            context.getEarthComm().sendMessage("! " + msg);
        }
    }

    @Override
    public void stopRover(Rover context, String msg) {     // ✔️
        // Rover would only stop after reached its distance.
        // If statement in main() - would only call state.stopRover and thus stop rover ONLY if distance met
            // Which is an (if statement /observer pattern which you do in main() )
            context.getEngineSys().stopDriving();
            context.setState(context.getStoppedState());
            context.getEarthComm().sendMessage("D");
            //System.out.println("Target = " + context.getTarget_distance());
            //System.out.println("Cur Dist = " + context.getEngineSys().getDistanceDriven());
            context.setTarget_distance(0); // Reset target distance.
    }

    @Override
    public void analyseSoil(Rover context, String msg) {     // ✔️ ERROR
        // Print error - can't transition to this.
        context.getEarthComm().sendMessage("! " + msg);
    }

    @Override
    public void doTurn(Rover context, String msg) {     // ✔️
        // Allow turn
        String[] parts = msg.split(" ");
        try
        {
            double angle = Double.parseDouble(parts[1]);

            if(parts[0].equals("T") && (angle >= -180 && angle <= 180))
            { // If valid
                context.getEngineSys().turn(angle);
            }
            else
            {
                context.getEarthComm().sendMessage("! " + msg);
            }
        }
        catch(NumberFormatException e)
        {
            context.getEarthComm().sendMessage("! " + msg);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            context.getEarthComm().sendMessage("! " + msg);
        }
    }
    
    
}
