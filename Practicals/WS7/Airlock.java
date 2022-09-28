public class Airlock 
{

    private double pressure;
    private String state;
	private Door inner;
	private Door outer;
	private Pump pump;
	private Sensor sensor;
	
	public Airlock()
	{}

    public void pressurise() {
        // begins pumping air back into the airlock.
        // Later, you open outer door, Air gets pumped back in. Air gets pressurized -- -- pressure must be less than 5 kPa
        if(pressure < 5 && state.equals("depressurised"))
        {
			// GUARD: IF INNER DOOR IS CLOSED
			if(!inner.isOpen()) // If guard returns false, we allow.
			{
				openOuterDoor();
				while (pressure < 90)
				{
					pump.beginExtraction(); //Assuming this changes the pressure value by 1 each call
				}
				state = "pressurised";				
			}
            
        }
    }

    public void depressurise(){
        // begins pumping air out of the airlock.
        //     ** So to do maintenance, you open inner door, close door. Air gets depressurised -- pressure must be more than 90 kpa.
        if(pressure > 90 && state.equals("pressurised"))
        {
			// GUARD: IF OUTER DOOR IS CLOSED
			if(!outer.isOpen()) // If guard returns false, we allow.
			{
				openInnerDoor();
				while (pressure > 5)
				{
					pump.beginReturn(); //Assuming this changes the pressure value by 1 each call
				}
				state = "depressurised";
			}
        }
    }

    public void openInnerDoor(){
        // Open door.
        inner.open();
    }

    public void openOuterDoor(){
        // Open door
        outer.open();
    }

    public void updatePressure(double pressure) {
		if(sensor.getPressure() > 90 || sensor.getPressure() < 5)
		{ // Run when changes occur.
			this.pressure = pressure; // Updates every second.
		}
    }

    
}
