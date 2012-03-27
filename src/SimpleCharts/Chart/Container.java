package SimpleCharts.Chart;

import SimpleCharts.Chart.Layout.LayoutManager;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author frank
 */
public interface Container extends Component
{
    /**
     * Get the number of components in this container.
     * @return the number of components in this container.
     * @see #getComponent
     */
    public int getComponentCount();
    /**
     * Gets the nth component in this container.
     * @param n - the index of the component to get.
     * @return  the n<sup>th</sup> component in this container.
     */
    public Component getComponent(int n);
    /**
     * Appends the specified component to the end of this container.
     * @param c - the component to be added.
     * @return  the component argument.
     */
    public Component add(Component c);
    /**
     * Adds the specified component to this container at the given position.
     * @param c - the component to be added.
     * @param index - the position at which to insert the component.
     * @return the component argument.
     */
    public Component add(Component c, int index);
    
    /**
     * Adds the specified component to this container and notifies the layout manager
     * passing along the given constraint.
     * @param c - the new component to add
     * @param constraint - a layout specific constraint to be passed to the layout manager along with the newly added component.
     * @return the component argument
     */
    public Component add(Component c, String constraint);
    /**
     * Remove the component specified by <code>index</code> from this container.
     * @param index - the index of the component to remove.
     */
    public void remove(int index);
    /**
     * Remove the specified component from this container.
     * @param c - the component to remove.
     */
    public void remove(Component c);
    /**
     * Removes all components from this container.
     */
    public void removeAll();
    /**
     * Return the layout manager for this container.
     * @return - the (@link LayoutManager) for this container.
     */
    public LayoutManager getLayout();
    /**
     * Set the layout manager for this container.
     * @param mgr - the new layout manager for this container.
     */
    public void setLayout(LayoutManager mgr);
    /**
     * Causes this container to lay out its components.
     */
    public void doLayout();
    /**
     * Locates the component that contains the x,y position. The top-most child
     * component is returned in the case where there is an overlap.
     * @param x - the <i>x</i> coordinate
     * @param y - the <i>y</i> coordinate
     * @return  null if a component does not contain the position. If there is no
     * child component at the position, the container itself is returned.
     */
    public Component getComponentAt(int x, int y);
    /**
     * Locates the component that contains the specified point. The top-most child
     * component is returned in the case where there is an overlap.
     * @param p - the point.
     * @return  null if a component does not contain the position. If there is no
     * child component at the position, the container itself is returned.
     */
    public Component getComponentAt(Point p);
    /**
     * Locates the visible child component that contains the specified position. The top-most
     * child component is returned in the case where there is an overlap. If the containing
     * child component is a container, the method will continue searching for the deepest
     * nested child component. Components that are not visible are ignored.
     * @param x - the <i>x</i> coordinate
     * @param y - the <i>y</i> coordinate
     * @return <code>null</code> if there is no visible child that contains the position in
     * the coordinate space of this container; otherwise the child component.
     */
    public Component findComponentAt(int x, int y);
    /**
     * Locates the visible child component that contains the specified position. The top-most
     * child component is returned in the case where there is an overlap. If the containing
     * child component is a container, the method will continue searching for the deepest
     * nested child component. Components that are not visible are ignored.
     * @param p - the position.
     * @return <code>null</code> if there is no visible child that contains the position in
     * the coordinate space of this container; otherwise the child component.
     */
    public Component findComponentAt(Point p);
    /**
     * Checks if the specified component is contained in the component hierarchy of this container.
     * @param c - the component.
     * @return <code>true</code> if it is an ancestor; <code>false</code> otherwise.
     */
    public boolean isAncestorOf(Component c);
    
    /**
     * Paints the children of this container.
     * @param g - the graphics context.
     */
    public void paintChildren(Graphics g);
}
