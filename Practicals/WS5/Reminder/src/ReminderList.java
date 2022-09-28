import java.util.*;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList
{
    private List<Reminder> reminders;
    private List<Observer> obsList;

    public ReminderList()
    {
        reminders = new ArrayList<Reminder>();
        obsList = new ArrayList<Observer>(); 
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
        notifyObservers();
    }

    public void addObserver(Observer o) {obsList.add(o);}
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
        notifyObservers();
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
        notifyObservers();
    }

    public void notifyObservers()
    {
        for(Observer ob: obsList) { ob.newReminder(reminders); }
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }
}
