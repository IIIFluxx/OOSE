/**
 * Specific Decorator for handling a Date record 
 */

public class DateDecorator extends Decorator
{
    private String date;
    // Decoration
    public DateDecorator(String inDate, Record next)
    {
        super(next);
        this.date = inDate; // Initializes to the classfield - retrieved from File IO.
    }

    // Implementation SPECIFIC to the Date Decorator -- overrides the getInfo method in Decorator.java --> ImageRecord.java --> Record.java
    @Override
    public String getInfo()
    {
        return next.getInfo() + ". Date = " + date;
    }
}
