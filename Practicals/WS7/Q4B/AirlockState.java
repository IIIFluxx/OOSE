public interface AirlockState
{
    public void pressurise(Airlock context);

    public void depressurise(Airlock context);
    
    public void openInnerDoor(Airlock context);

    public void openOuterDoor(Airlock context);

    public void updatePressure(Airlock context, double pressure);
}


/*
- Pressurized --> Depressurizing: We call openInnerDoor()

- Depressurizing --> Depressurized : We call depressurize()

- Depressurized --> Pressurizing: We call openOuterDoor().

- Pressurizing --> Pressurized: We call pressurize().

- The pumping process (either pressurising or depressurising the airlock) can be halted & reversed if needed.


just dont know for updatePressure in the states
actually i do
for the decrementing ones when it reaches the value u switch states

*/