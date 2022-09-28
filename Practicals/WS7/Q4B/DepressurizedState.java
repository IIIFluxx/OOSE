public class DepressurizedState implements AirlockState {
    
    public void pressurise(Airlock context){
        openOuterDoor(context);
        
    }

    public void depressurise(Airlock context) {}
    
    public void openInnerDoor(Airlock context) {
    }

    public void openOuterDoor(Airlock context) {
        
        if(context.getInner().IsOpen() == false)
        {
            context.getOuter().open();
            context.setState(PressurizingState);
        }
    }

    public void updatePressure(Airlock context, double pressure) {}
}