package DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
public class SimpleDataModel<T> implements DataModel<T>
{
    private final List<ChangeListener> change_listeners;
    private final List<? extends T> data;
    
    public SimpleDataModel(List<? extends T> data)
    {
        this.data = data;
        change_listeners = new ArrayList<ChangeListener>();
    }
    @Override
    public void addChangeListener(ChangeListener listener)
    {
        change_listeners.add(listener);
    }

    @Override
    public T get(int index)
    {
        return data.get(index);
    }
    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    @Override
    public void removeChangeListener(ChangeListener listener)
    {
        change_listeners.remove(listener);
    }

    @Override
    public int size()
    {
        return data.size();
    }

}
