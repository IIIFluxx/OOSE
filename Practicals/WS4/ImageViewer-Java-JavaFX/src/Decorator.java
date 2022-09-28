// DECORATOR SUPERCLASS / INTERMEDIATE SUPERCLASS
// All the individual decorator methods will implement THIS class
public abstract class Decorator implements Record
{
    // ** Code based from Lecture Slides **
    protected Record next; // Represents the 'next' Decorator or Implementation in the LL/Chain
    public Decorator(Record next) {this.next = next;}
    
    @Override public String getFilename() {return next.getFilename();}
    
    @Override public String getInfo() {return next.getInfo();}

    // Every method here just passes it onto the 'next'.
    
}