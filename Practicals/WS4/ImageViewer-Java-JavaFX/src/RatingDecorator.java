/**
 * Specific Decorator for handling a Rating record 
 */
public class RatingDecorator extends Decorator
{
    private int rating;
    // Decoration
    public RatingDecorator(int inRating, Record next)
    {
        super(next);
        this.rating = inRating; // Initializes to the classfield - retrieved from File IO.
    }

    // Implementation SPECIFIC to the Rating Decorator:
    @Override
    public String getInfo()
    {
        return next.getInfo() + "; Rated at: " + rating;
    }
}
