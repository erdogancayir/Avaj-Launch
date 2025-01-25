import java.util.List;
import java.util.ArrayList;

class Tower
{
    private List<Flyable> observers = new ArrayList<Flyable>();
    
    public void register(Flyable p_flyable)
    {
        // check duplicate
        if (observers.contains(p_flyable))
            return;

        observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable)
    {
        observers.remove(p_flyable);
    }

    protected void ConditionChanged()
    {
        for (Flyable flyable : observers)
        {
            flyable.updateConditions();
        }
    }
}