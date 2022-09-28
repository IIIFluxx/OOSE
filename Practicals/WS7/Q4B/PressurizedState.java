public class PressurizedState implements AirlockState {
    public void pressurise(Airlock context){}

    public void depressurise(Airlock context) {
        
    }
    
    public void openInnerDoor(Airlock context) {
        if(context.getOuter().IsOpen() == false)
        {
            context.getInner().open();
            context.setState(DepressurizingState);
        }
    }

    public void openOuterDoor(Airlock context) {}

    public void updatePressure(Airlock context, double pressure) {}
}