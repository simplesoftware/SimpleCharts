package SimpleCharts.Chart;

import SimpleCharts.Chart.Border.Border;
import java.awt.*;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;

/**
 * The interface for all components that can be contained in a (@link Chart).
 */
public interface Component
{
    /**
     * Get the parent for this component.
     * @return the (@link Container) for this component or <code>null</code> if
     * this is a top child of the chart.
     */
    public Container getParent();
    
    /**
     * Set the parent of this component to the specified container.
     * @param c - the new parent.
     */
    public void setParent(Container c);
    
    /**
     * Returns the current x coordinate of the component's origin.
     * This method is preferable to writing component.getBounds().x, or component.getLocation().x because it doesn't cause any heap allocations. 
     * @return the current x coordinate of the component's origin
     */
    public int getX();
    
    /**
     * Returns the current y coordinate of the component's origin.
     * This method is preferable to writing component.getBounds().y, or component.getLocation().y because it doesn't cause any heap allocations.
     * @return the current y coordinate of the component's origin
     */
    public int getY();
    
    /**
     * Stores the x,y origin of this component into p and returns p.
     * If p is null a new Point is allocated.
     * This version of getLocation is useful if the caller wants to avoid allocating a new Point object on the heap. 
     * @param p - the return value, modified to the component's location 
     * @return p or new (@link Point) containing the origin of this component
     */
    public Point getLocation(Point p);
    
    /**
     * Moves this component to a new location.
     * The top-left corner of the new location is specified by the x and y parameters in the coordinate space of this component's parent.
     *
     * @param x - the x-coordinate of the new top-left corner of this component in the parent's coordinate space.
     * @param y - the y-coordinate of the new top-left corner of this component in the parent's coordinate space.
     */
    public void setLocation(int x, int y);
    
    /**
     * Return the vertical alignment for this component.
     * @return the value of the alignmentX property.
     */
    public float getAlignmentX();
    
    /**
     * Return the horizontal alignment for this component.
     * @return the value of the alignmentY property.
     */
    public float getAlignmentY();
    
    /**
     * Sets the vertical alignment.
     * @param alignmentX - the new vertical alignment.
     */
    public void setAlignmentX(float alignmentX);
    
    /**
     * Sets the horizontal alignment.
     * @param alignmentY - the new  horizontal alignment.
     */
    public void setAlignmentY(float alignmentY);
    
    /**
     * Get the preferred size of this component.
     * @return the preferred dimension of this component.
     */
    public Dimension getPreferredSize();
    
    /**
     * Get the minimum size of the component.
     * @return the minimum size of this component.
     */
    public Dimension getMinimumSize();
    
    /**
     * Get the maximum size of the component.
     * @return the maximum size of this component.
     */
    public Dimension getMaximumSize();
    
    /**
     * Sets the minimum size of this component to a constant value.
     * Subsequent calls to getMinimumSize will always return this value; the component's UI will not be asked to compute it.
     * Setting the minimum size to null restores the default behavior. 
     * @param preferredSize - the new minimum size of this component
     */
    public void setMinimumSize(Dimension minimumSize);
    
    /**
     * Sets the maximum size of this component to a constant value.
     * Subsequent calls to getMaximumSize will always return this value;
     * the component's UI will not be asked to compute it.
     * Setting the maximum size to null restores the default behavior. 
     * @param maximumSize - a Dimension containing the desired maximum allowable size
     */
    public void setMaximumSize(Dimension maximumSize);
    
    /**
     * Sets the preferred size of this component.
     * If preferredSize is null, the UI will be asked for the preferred size.
     * @param preferredSize - The new preferred size, or null
     */
    public void setPreferredSize(Dimension preferredSize);
    
    /**
     * Checks whether the preferred size for this component was
     * explicitly using <code>setPreferredSize</code>.
     * @return <code>true</code> if size explicity set, otherwise <code>false</code>.
     */
    public boolean isPreferredSizeSet();
    
    /**
     * Returns the current width of this component.
     * This method is preferable to writing component.getBounds().width, or component.getSize().width because it doesn't cause any heap allocations.
     * @return the current width of this component
     */
    public int getWidth();
    
    /**
     * Returns the current height of this component.
     * This method is preferable to writing component.getBounds().height, or component.getSize().height because it doesn't cause any heap allocations.
     * @return the current height of this component
     */
    public int getHeight();
    
    /**
     * Resizes this component so that it has width width and height height.
     * @param width - the new width of this component in pixels
     * @param height - the new height of this component in pixels
     */
    public void setSize(int width, int height);
    
    /**
     * Stores the bounds of this component into "return value" r and returns r.
     * If r is null a new Rectangle is allocated.
     * This version of getBounds is useful if the caller wants to avoid allocating a new Rectangle object on the heap. 
     * @param r - the return value, modified to the component's size
     * @return r or a new Rectangle containing the bounds of this component
     */
    public Rectangle getBounds(Rectangle r);
    
    /**
     * Moves and resizes this component. The new location of the top-left corner is specified by x and y, and the new size is specified by width and height.
     * 
     * @param x - the new x-coordinate of this component
     * @param y - the new y-coordinate of this component
     * @param width - the new width of this component
     * @param height - the new height of this component
     */
    public void setBounds(int x, int y, int width, int height);
    
    /**
     * Returns the bounds of this component.
     * @return - a new Rectangle containing the bounds of this component.
     */
    public Rectangle getBounds();
    
    /**
     * If a border has been set on this chart component, returns the it's insets;
     * otherwise an (@link Insets) object is returned with all its values zeroed.
     * @return the insets for this chart component.
     * @see @setBorder
     */
    public Insets getInsets();
    
    /**
     * Sets this component's border.
     * @param border - the new (@link Border) for this component.
     */
    public void setBorder(Border border);
    
    /**
     * Gets this component's border.
     * @return - this component's (@link Border) or <code>null</code> if it is not set.
     */
    public Border getBorder();
    
    /**
     * Get the background color of this component.
     * @return the background (@link Color) for this chart component.
     */
    public Color getBackground();
    /**
     * Get the foreground color of this component.
     * @return the foreground (@link Color) for this chart component.
     */
    public Color getForeground();
    /**
     * Sets the background color of this chart component. The background color is only used if the component
     * is opaque.
     * @param color - the desired background (@link Color).
     */
    public void setBackground(Color color);
    /**
     * Sets the foreground color of this chart component.
     * @param color - the desired foreground (@link Color).
     */
    public void setForeground(Color color);

    /**
     * Returns true if this component is completely opaque.
     * An opaque component paints every pixel within its rectangular bounds.
     * A non-opaque component paints only a subset of its pixels or none at all, allowing the pixels underneath it to "show through".
     * Therefore, a component that does not fully paint its pixels provides a degree of transparency.
     * Subclasses that guarantee to always completely paint their contents should override this method and return true. 
     * @return true if this component is completely opaque
     */
    public boolean isOpaque();
    
    /**
     * If true the component paints every pixel within its bounds.
     * Otherwise, the component may not paint some or all of its pixels, allowing the underlying pixels to show through.
     * The default value of this property is true.
     * However, the default value for this property on most standard component subclasses is look-and-feel dependent. 
     * @param flag - true if this component should be opaque
     */
    public void setOpaque(boolean flag);
    
    /**
     * Determines whether this component is enabled.
     * An enabled component can respond to user input and generate events.
     * Components are enabled by default.
     * A component may be enabled or disabled by calling its setEnabled method. 
     * @return true if the component is enabled, false otherwise
     */
    public boolean isEnabled();
    
    /**
     * Determines whether this component should be visible when its parent is visible.
     * Components are visible by default
     * @return true if the component is visible, false otherwise
     */
    public boolean isVisible();
    
    /**
     * Makes the component visible or invisible.
     * @param flag - true to make the component visible; false to make it invisible.
     */
    public void setVisible(boolean flag);
    
    /**
     * Sets whether or not this component is enabled. A component that is enabled may respond to user input,
     * while a component that is not enabled cannot respond to user input. Some components may alter their visual
     * representation when they are disabled in order to provide feedback to the user that they cannot take input.
     * 
     * @param flag - true if this component should be enabled, false otherwise
     */
    public void setEnabled(boolean flag);
    
    /**
     * Returns the component graphics content, which lets you draw on a component.
     * @return the graphics context of this component.
     */
    public Graphics getGraphics();
    
    /**
    * Invoked by the container of this component to draw this component.
    * 
    * @param g - the Graphics context in which to paint
    */
    public void paint(Graphics g);
    
    /**
     * Repaints the the specified region of this component.
     * This method causes a call to this component's paint method as soon as possible.
     * @param x - the <i>x</i> coordinate of the region
     * @param y - the <i>y</i> coordinate of the region
     * @param width - the <i>width</i> of the region
     * @param height - the <i>height</i> of the region
     */
    public void repaint(int x, int y, int width, int height);
    
    /**
     * Repaints this component.
     * This method causes a call to this component's paint method as soon as possible.
     */
    public void repaint();
    
    /**
     * Supports deferred automatic layout.
     * Adds this component to a list of components that need to be validated.
     * This method will automatically be called on this component when a property value changes such that size, location, or internal layout of this component has been affected.
     */
    public void revalidate();
    
    /**
     * Mark this component as needing to be validated.
     */
    public void invalidate();
    
    /**
     * Checks whether this component needs to be rebounded.
     * @return <code>true</code> if this component is at the proper location and has the proper size.
     * <code>false</false> if this component needs to be rebounded.
     */
    public boolean isValid();
    
    /**
     * Marks this component has having been rebounded.
     */
    public void validate();
    
    /**
     * Gets the FontMetrics for the specified Font. 
     * @param font - the font for which font metrics is to be obtained 
     * @return the font metrics for font
     */
    public FontMetrics getFontMetrics(Font font);
    
    /**
     * Gets the cursor set in the component.
     * If the component does not have a cursor set, the cursor of its parent is returned.
     * If no cursor is set in the entire hierarchy, Cursor.DEFAULT_CURSOR is returned. 
     * @return the cursor
     */
    public Cursor getCursor();
    
    /**
     * Returns JPopupMenu that assigned for this component.
     * If this component does not have a JPopupMenu assigned to it, null is returned.
     * @return JPopupMenu assigned for this component or null if no pop up assigned
     */
    public JPopupMenu getComponentPopupMenu();

    /**
     * Sets the JPopupMenu for this JComponent.
     * The UI is responsible for registering bindings and adding the necessary listeners such that the JPopupMenu will be shown at the appropriate time.
     * When the JPopupMenu is shown depends upon the look and feel:
     * some may show it on a mouse event, some may enable a key binding.
     * If <code>popup</code> is null, and getInheritsPopupMenu returns true, then getComponentPopupMenu will be delegated to the parent.
     * This provides for a way to make all child components inherit the <code>popupmenu</code> of the parent. 
     * @param menu - the <code>popup</code> that will be assigned to this component may be null
     */
    public void setComponentPopupMenu(JPopupMenu menu);
    
    /**
     * Gets the font of this component.
     * 
     * @return this component's font; if a font has not been set for this component, the font of its parent is returned
     */
    public Font getFont();
    
//    /**
//     * Invoke this method to print the component.
//     * @param g - the Graphics context in which to paint
//     */
//    public void print(Graphics2D g);
    
    /**
     * Set the font for this component.
     * @param font - the new (@link Font).
     */
    public void setFont(Font font);
    
    /**
     * Returns the instance of JToolTip that should be used to display the tool tip.
     * @return the <code>JToolTip</code> used to display this toolTip
     */
    public JToolTip createToolTip();
    
    /**
     * Gives the UI delegate an opportunity to define the precise shape of this component for the sake of mouse processing. 
     * 
     * @param x - the x coordinate of the point
     * @param y - the x coordinate of the point
     * @return true if this component logically contains x,y
     */
    public boolean contains(int x, int y);
    
    /**
     * Forwards the scrollRectToVisible() message to the JComponent's parent.
     * Components that can service the request override this method and perform the scrolling. 
     * @param rectangle - the visible Rectangle
     */
    public void scrollRectToVisible(Rectangle rectangle);
}

