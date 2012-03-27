package DataModel;

/**
 * An observable random-access data model template.
 */
public interface DataModel<T>
{
    /**
     * Return the number of elements contained in the model
     * @return number of elements
     */
    public int size();
    /**
     * Checks whether the model contains any elements
     * @return <code>size() == 0</code>
     */
    public boolean isEmpty();
    /**
     * Add the given <code>ChangeListener</code> to the list of observers for this model
     * @param listener the observer to add
     * @see DataModel.ChangeListener <code>ChangeListener</code>
     */
    public void addChangeListener(ChangeListener listener);
    /**
     * Removes the given <code>ChangeListener</code> from the list of observers for this model
     * @param listener the observer to remove
     * @see DataModel.ChangeListener <code>ChangeListener</code>
     */
    public void removeChangeListener(ChangeListener listener);
    /**
     * An observer for <code>DataModel</code>
     */
    public interface ChangeListener
    {
        /**
         * Invoked as a result of any change to this <code>DataModel</code>.
         */
        public void dataModelChanged();
    }
    /**
     * Returns the element in the model at position <code>index</code>
     * @param index the position ranging from 0 to <code>size() - 1</code>
     * @return the <code>XYData</code> element at position <code>index</code>
     */
    public T get(int index);
}
