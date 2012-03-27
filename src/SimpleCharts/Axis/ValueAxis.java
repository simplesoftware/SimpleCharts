package SimpleCharts.Axis;

/**
 * The base class for axes that display value data, where values are measured
 * using the <code>double</code> primitive.  The two key subclasses are
 * {@link DateAxis} and {@link NumberAxis}.
 */
public interface ValueAxis extends Axis
{
    /**
     * Automatically sets the axis range to fit the range of values in the
     * dataset.
     * @param data_range - the data range.
     */
    public void autoAdjustForRange(Range data_range);
    
    /**
     * Returns the range for the axis.
     *
     * @return The axis range (never <code>null</code>).
     *
     * @see #setRange(Range)
     */
    public Range getRange();
    
    /**
     * Sets the range attribute and notifies all
     * registered listeners.  As a side-effect, the auto-range flag is set to
     * <code>false</code>.
     *
     * @param range  the range (<code>null</code> not permitted).
     *
     * @see #getRange()
     */
    public void setRange(Range range);
    
    /**
     * Returns the flag that controls whether or not the axis range is
     * automatically adjusted to fit the data values.
     *
     * @return The flag.
     *
     * @see #setAutoRange(boolean)
     */
    public boolean isAutoRange();

    /**
     * Sets a flag that determines whether or not the axis range is
     * automatically adjusted to fit the data, and notifies registered
     * listeners that the axis has been modified.
     *
     * @param auto  the new value of the flag.
     *
     * @see #isAutoRange()
     */
    public void setAutoRange(boolean flag);
    
    /**
     * Returns the upper margin for the axis, expressed as a percentage of the
     * axis range.  This controls the space added to the lower end of the axis
     * when the axis range is automatically calculated (it is ignored when the
     * axis range is set explicitly). The default value is 0.05 (five percent).
     *
     * @return The upper margin.
     *
     * @see #setUpperMargin(double)
     */
    public double getUpperMargin();
    
    /**
     * Sets the upper margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin  the margin percentage (for example, 0.05 is five percent).
     *
     * @see #getLowerMargin()
     * @see #setLowerMargin(double)
     */
    public void setUpperMargin(double margin);
    
    /**
     * Returns the lower margin for the axis, expressed as a percentage of the
     * axis range.  This controls the space added to the lower end of the axis
     * when the axis range is automatically calculated (it is ignored when the
     * axis range is set explicitly). The default value is 0.05 (five percent).
     *
     * @return The lower margin.
     *
     * @see #setLowerMargin(double)
     */
    public double getLowerMargin();
    
    /**
     * Sets the lower margin for the axis (as a percentage of the axis range)
     * and sends an {@link AxisChangeEvent} to all registered listeners.  This
     * margin is added only when the axis range is auto-calculated - if you set
     * the axis range manually, the margin is ignored.
     *
     * @param margin  the margin percentage (for example, 0.05 is five percent).
     *
     * @see #getLowerMargin()
     * @see #setUpperMargin(double)
     */
    public void setLowerMargin(double margin);
    
    public double getAutoRangeMinimumExtent();
    
    public void setAutoRangeMinimumExtent(double extent);
    
    /**
     * Get the factory used to auto-generate tick marks
     * for this axis.
     * @return the factory.
     * @see ValueTickFactory
     */
    public ValueTickFactory getTicksFactory();
    
    /**
     * Set the factory used to auto-generate tick marks
     * for this axis.
     * @param factory - the new factory.
     */
    public void setTicksFactory(ValueTickFactory factory);
    
    /**
     * Returns a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.
     *
     * @return A flag indicating whether or not the tick unit is automatically
     *         selected.
     *
     * @see #setAutoTickUnitSelection(boolean)
     */
    public boolean isAutoTickUnitSelection();
    
    /**
     * Sets a flag indicating whether or not the tick unit is automatically
     * selected from a range of standard tick units.  If the flag is changed,
     * registered listeners are notified that the chart has changed.
     *
     * @param flag  the new value of the flag.
     *
     * @see #isAutoTickUnitSelection()
     */
    public void setAutoTickUnitSelection(boolean flag);
    
    /**
     * Returns the lower bound of the axis range.
     *
     * @return The lower bound.
     *
     * @see #setLowerBound(double)
     */
    public double getLowerBound();
    
    /**
     * Returns the upper bound for the axis range.
     *
     * @return The upper bound.
     *
     * @see #setUpperBound(double)
     */
    public double getUpperBound();
    
    /**
     * Sets the axis range and notifies all
     * registered listeners.  As a side-effect, the auto-range flag is set to
     * <code>false</code>.
     *
     * @param lower  the lower axis limit.
     * @param upper  the upper axis limit.
     *
     * @see #getRange()
     * @see #setRange(Range)
     */
    public void setRange(double lower, double upper);
    
    /**
     * Sets the range for the axis (after first adding the current margins to
     * the specified range) and notifying all
     * registered listeners.
     *
     * @param range  the range (<code>null</code> not permitted).
     */
    public void setRangeWithMargins(Range range);
    
    /**
     * Converts a data value to a coordinate.
     * <p>
     * Note that it is possible for the coordinate to fall outside the area.
     *
     * @param value  the data value.
     * @return The coordinate.
     *
     * @see #coordToValue(double)
     */
    public int valueToCoord(double value);
    
    /**
     * Converts a coordinate to the corresponding data value.
     *
     * @param coord  the coordinate in Java2D space.
     * @return The data value.
     * @see #valueToCoord(double)
     */
    public double coordToValue(int coord);
    
    /**
     * Centers the axis range about the specified value and notifies all registered listeners.
     *
     * @param value  the center value.
     */
    public void centerRange(double value);
    
    /**
     * Increases or decreases the axis range by the specified percentage about
     * the central value and notifies all registered
     * listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0).
     * To halve the length of the axis range, use 50% (0.5).
     *
     * @param percent  the resize factor.
     * @see #resizeRange(double, double)
     */
    public void resizeRange(double percent);
    
    /**
     * Increases or decreases the axis range by the specified percentage about
     * the specified anchor value and sends an {@link AxisChangeEvent} to all
     * registered listeners.
     * <P>
     * To double the length of the axis range, use 200% (2.0).
     * To halve the length of the axis range, use 50% (0.5).
     *
     * @param percent  the resize factor.
     * @param anchorValue  the new central value after the resize.
     *
     * @see #resizeRange(double)
     */
    public void resizeRange(double percent, double anchorValue);
    
    /**
     * Zooms in on the current range.
     *
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent);
    
    /**
     * Slides the axis range by the specified percentage.
     *
     * @param percent  the percentage.
     */
    public void pan(double percent);
    
    public void setMinimumTickSize(double min_tick_size);
    
    public double getMinimumTickSize();
}
