/**
 * Specific Decorator for handling a GPS record 
 */
public class GPSDecorator extends Decorator
{
    private int posX;
    private int posY;
    private int posZ;

    // Decoration
    public GPSDecorator(int inX, int inY, int inZ, Record next)
    {
        super(next);
         // Initializes to the classfield - retrieved from File IO.
        this.posX = inX;
        this.posY = inY;
        this.posZ = inZ;
    }

    // Implementation SPECIFIC to the GPS Decorator:
    @Override
    public String getInfo()
    {
        return next.getInfo() + "; GPS @ [X Y Z]: [" + posX + " " + posY + " " + posZ + "]. ";
    }
}
