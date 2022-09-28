public class DepressurizingState implements AirlockState {
    public void pressurise(Airlock context){}

    public void depressurise(Airlock context) {
        while (context.getPressure() > 5)
        {
            context.getPump().beginReturn(); //Assuming this changes the pressure value by 1 each call
            updatePressure(context, context.getPressure());
        }
        context.setState(DepressurizedState);
    }
    
    public void openInnerDoor(Airlock context) {}

    public void openOuterDoor(Airlock context) {}

    public void updatePressure(Airlock context, double inPressure) {
        
        // Run when changes occur i.e. only in Depressurising and Pressurising States.
			context.setPressure(inPressure); // Updates every second.
    }
}