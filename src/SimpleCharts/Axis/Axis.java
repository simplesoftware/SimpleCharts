package SimpleCharts.Axis;

import SimpleCharts.Chart.Component;
import java.awt.Font;
import java.awt.Paint;

/**
 * The interface for all axes in SimpleCharts.
 * Subclasses are divided into those that display values ({@link ValueAxis}) and those that display
 * categories ({@link CategoryAxis}).
 */
public interface Axis extends Component
{
    /**
     * Returns the label for the axis.
     *
     * @return The label for the axis (<code>null</code> possible).
     *
     * @see #getLabelFont()
     * @see #getLabelPaint()
     * @see #setLabel(String)
     */
    public String getLabel();
    
    /**
     * Sets the label for the axis and notifies all
     * registered listeners.
     *
     * @param label  the new label (<code>null</code> permitted).
     *
     * @see #getLabel()
     * @see #setLabelFont(Font)
     * @see #setLabelPaint(Paint)
     */
    public void setLabel(String label);

    /**
     * Returns the color/shade used to draw the axis label.
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setLabelPaint(Paint)
     */
    public Font getLabelFont();
    
    /**
     * Returns the font for the axis label.
     *
     * @return The font (never <code>null</code>).
     *
     * @see #setLabelFont(Font)
     */
    public void setLabelFont(Font font);
    
    /**
     * Get the paint used to draw the axis label.
     * @return the paint.
     */
    public Paint getLabelPaint();
    
    /**
     * Sets the paint used to draw the axis label and notifies all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint);
    
    /**
     * Returns the angle of the axis label.
     *
     * @return The angle (in radians).
     *
     * @see #setLabelAngle(double)
     */
    public double getLabelAngle();
    
    /**
     * Sets the angle for the label and notifies all
     * registered listeners.
     *
     * @param angle  the angle (in radians).
     *
     * @see #getLabelAngle()
     */
    public void setLabelAngle(double angle);
    
    /**
     * Returns the paint used to draw the axis line.
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setAxisLinePaint(Paint)
     */
    public Paint getAxisLinePaint();

    /**
     * Sets the paint used to draw the axis line and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getAxisLinePaint()
     */
    public void setAxisLinePaint(Paint paint);
    
    /**
     * Returns the font used for the tick labels (if showing).
     *
     * @return The font (never <code>null</code>).
     *
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont();
    
    /**
     * Sets the font for the tick labels and sends an {@link AxisChangeEvent}
     * to all registered listeners.
     *
     * @param font  the font (<code>null</code> not allowed).
     *
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font);
    
    /**
     * Returns the color/shade used for the tick labels.
     *
     * @return The paint used for the tick labels.
     *
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint();
    
    /**
     * Sets the paint used to draw tick labels (if they are showing) and
     * sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getTickLabelPaint()
     */
    public void setTickLabelPaint(Paint paint);
    
    /**
     * Returns the flag that indicates whether or not the minor tick marks are
     * showing.
     *
     * @return The flag that indicates whether or not the minor tick marks are
     *         showing.
     *
     * @see #setMinorTickMarksVisible(boolean)
     */
    public boolean isMinorTicksVisible();
    
    /**
     * Return whether or not this axis is inverted (high to low rather than low to high).
     * @return true if inverted, else false.
     */
    boolean isInverted();
    
    /**
     * Sets whether this axis should is displayed inverted (high to low) or not (low to high).
     * @param flag - true is inverted else false.
     */
    public void setInverted(boolean flag);
    
    /**
     * Sets the flag that indicates whether or not the minor tick marks are 
     * showing and sends an {@link AxisChangeEvent} to all registered
     * listeners.
     *
     * @param flag  the flag.
     *
     * @see #isMinorTickMarksVisible()
     */
    public void setMinorTicksVisible(boolean flag);
    
    /**
     * Returns the paint used to draw tick marks (if they are showing).
     *
     * @return The paint (never <code>null</code>).
     *
     * @see #setTickMarkPaint(Paint)
     */
    public Paint getTickMarkPaint();
    
    /**
     * Sets the paint used to draw tick marks and sends an
     * {@link AxisChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getTickMarkPaint()
     */
    public void setTickMarkPaint(Paint paint);
    
    /**
     * Registers an object for notification of changes to the axis.
     *
     * @param listener  the object that is being registered.
     *
     * @see #removeChangeListener(AxisChangeListener)
     */
    public void addListener(ChangeListener listener);

    /**
     * Get the chart position of this axis
     * @return one of ChartComponent position constants.
     */
    public int getPosition();
    
    /**
     * Set the chart position for this axis
     * @param position - one of ChartComponent position constants.
     */
    public void setPosition(int position);
    
    /**
     * Get the orientation of this axis.
     * @return one of ChartComponent orientation constants.
     */
    public int getOrientation();
    
    /**
     * Unregisters an object for notification of changes to the axis.
     *
     * @param listener  the object to deregister.
     *
     * @see #addChangeListener(AxisChangeListener)
     */
    public void removeListener(ChangeListener listener);
    
    public interface ChangeListener
    {
        public void axisChanged(Axis axis);
    }
}
