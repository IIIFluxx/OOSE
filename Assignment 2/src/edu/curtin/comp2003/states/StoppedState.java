package edu.curtin.comp2003.states;
import edu.curtin.comp2003.rover.*;

public class StoppedState implements RoverState {

    @Override
    public void driveRover(Rover context, String msg) {     // ✔️

        // Do both -- call drive & set to driving state.
        // rover.getEngineSys().startDriving();
        // rover.setState(rover.getDrivingState());

        String[] parts = msg.split(" ");
        try
        {
            double distance = Double.parseDouble(parts[1]);

            if(parts[0].equals("D") && distance > 0)
            {
                context.getEngineSys().startDriving();
                // The distance we need to drive is w/e is after "D" so just save it
                context.setTarget_distance(context.getEngineSys().getDistanceDriven() + distance);
                //System.out.println("Target set to " + context.getTarget_distance());
                context.setState(context.getDrivingState());
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

    @Override
    public void stopRover(Rover context, String msg) {     // ✔️ ERROR 
        // Can't self transition -- print error
        context.getEarthComm().sendMessage("! " + msg);
    }

    @Override
    public void analyseSoil(Rover context, String msg) {     // ✔️
        // CAN do this -- allow transition.
        if(msg.equals("S"))
        {
            context.getSoilAnlsr().startAnalysis();
            context.setState(context.getSoilState());
        }
        else
        {
            context.getEarthComm().sendMessage("! " + msg);
        }
    }


    @Override
    public void doTurn(Rover context, String msg) {     // ✔️
        // Allow turn
        String[] parts = msg.split(" ");
        try
        {
            double angle = Double.parseDouble(parts[1]);

            if(parts[0].equals("T") && (angle >= -180 && angle <= 180))
            {
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
