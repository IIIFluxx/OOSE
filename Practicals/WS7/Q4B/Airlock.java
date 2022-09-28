public class Airlock
{

    private AirlockState state = new PressurizedState();
    private double pressure;
    private Door inner;
    private Door outer;
    private Pump pump;
	private Sensor sensor;

    // Important for State Pattern
    public void setState(AirlockState state) {
        this.state = state;
    }

    public void pressurise() {
        state.pressurise(this);
    }

    public void depressurise(){
        state.depressurise(this);
    }

    public void openInnerDoor(){
        // Open door.
        state.openInnerDoor(this);;
    }

    public void openOuterDoor(){
        // Open door
        state.openOuterDoor(this);;

    }

    public void updatePressure(double pressure) {
		state.updatePressure(this, pressure);
    } //TODO: Sort out pressure ^.

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Door getInner() {
        return inner;
    }

    public Door getOuter() {
        return outer;
    }

    public Pump getPump() {
        return pump;
    }

    public Sensor getSensor() {
        return sensor;
    }



    
}
