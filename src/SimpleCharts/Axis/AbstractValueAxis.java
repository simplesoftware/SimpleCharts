package SimpleCharts.Axis;

import SimpleCharts.Chart.ChartComponent;

/**
 * The base class for axes that display value data, where values are measured
 * using the <code>double</code> primitive.  The two key subclasses are
 * {@link DateAxis} and {@link NumberAxis}.
 */
abstract public class AbstractValueAxis extends AbstractAxis implements ValueAxis
{
    /** The default axis r. */
    public static final Range DEFAULT_RANGE = new Range(0.0, 1.0);
    
    /** The default auto-r value. */
    public static final boolean DEFAULT_AUTO_RANGE = true;
    
    /** The default value for the lower margin (0.05 = 5%). */
    public static final double DEFAULT_LOWER_MARGIN = 0.05;

    /** The default value for the upper margin (0.05 = 5%). */
    public static final double DEFAULT_UPPER_MARGIN = 0.05;
    
    /** The default auto-tick-unit-selection value. */
    public static final boolean DEFAULT_AUTO_TICK_UNIT_SELECTION = true;

    /** The default minimum auto range. */
    public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE = 0.00000001;
    
    /** The axis r. */
    private Range range;
    
    /**
     * Flag that indicates whether the axis automatically scales to fit the
     * chart data.
     */
    private boolean is_auto_range;
    
    /**
     * The upper margin percentage.  This indicates the amount by which the
     * maximum axis value exceeds the maximum data value (as a percentage of
     * the r on the axis) when the axis r is determined automatically.
     */
    private double upper_margin;
    
    /**
     * The lower margin.  This is a percentage that indicates the amount by
     * which the minimum axis value is "less than" the minimum data value when
     * the axis r is determined automatically.
     */
    private double lower_margin;
    
    /** The minimum size for the 'auto' axis range (excluding margins). */
    private double minimum_auto_range_extent;
    
    /**
     * Flag that indicates whether or not the tick unit is selected
     * automatically.
     */
    private boolean is_auto_tick_unit_selection;
    
    /**
     * The chart position for this axis (one of ChartComponent position constants).
     */
    private int position;
    
    /**
     * The tick factory.
     */
    private ValueTickFactory ticks_factory;
    
    /**
     * The minimum tick size.
     */
    private double minimum_tick_size;
    
    /**
     * Constructs a value axis.
     *
     * @param position - the position for this axis (one of ChartComponent position constants).
     * @param label - the axis label (<code>null</code> permitted).
     * @param ticks_factory - the factory to use to auto-generate tick marks.
     */
    protected AbstractValueAxis(int position, String label, ValueTickFactory ticks_factory)
    {
        super(label);
        this.position = position;
        this.ticks_factory = ticks_factory;
        is_auto_range = DEFAULT_AUTO_RANGE;
        is_auto_tick_unit_selection = DEFAULT_AUTO_TICK_UNIT_SELECTION;
        lower_margin = DEFAULT_LOWER_MARGIN;
        upper_margin = DEFAULT_UPPER_MARGIN;
        range = DEFAULT_RANGE;
        minimum_auto_range_extent = DEFAULT_AUTO_RANGE_MINIMUM_SIZE;
        minimum_tick_size = 0;
    }
    
    @Override
    public void setMinimumTickSize(double min_tick_size)
    {
        this.minimum_tick_size = min_tick_size;
    }
    
    @Override
    public double getMinimumTickSize()
    {
        return minimum_tick_size;
    }
    
    @Override
    public double getLowerMargin()
    {
        return lower_margin;
    }

    @Override
    public Range getRange()
    {
        return range;
    }

    @Override
    public double getUpperMargin()
    {
        return upper_margin;
    }

    @Override
    public boolean isAutoRange()
    {
        return is_auto_range;
    }

    @Override
    public boolean isAutoTickUnitSelection()
    {
        return this.is_auto_tick_unit_selection;
    }

    @Override
    public void setAutoTickUnitSelection(boolean flag)
    {
        if(flag != this.is_auto_tick_unit_selection)
        {
            is_auto_tick_unit_selection = flag;
            fireChangeEvent();
        }
    }

    @Override
    public void setAutoRange(boolean flag)
    {
        if(is_auto_range != flag)
        {
            is_auto_range = flag;
            fireChangeEvent();
        }
    }

    @Override
    public void setLowerMargin(double margin)
    {
        if(this.lower_margin != margin)
        {
            lower_margin = margin;
//            if(isAutoRange())
//                autoAdjustRange();
            fireChangeEvent();
        }
    }

    @Override
    public void setRange(Range range)
    {
        if(this.range != range)
        {
            this.range = range;
            fireChangeEvent();
        }
    }

    @Override
    public void setUpperMargin(double margin)
    {
        if(this.upper_margin != margin)
        {
            this.upper_margin = margin;
//            if(isAutoRange()) THIS SHOULD BE CALLED VIA CHANGE EVENT FROM PLOT
//                autoAdjustRange();
            fireChangeEvent();
        }
    }

    @Override
    public double getUpperBound()
    {
        return range.getUpperBound();
    }
    
    @Override
    public double getLowerBound()
    {
        return range.getLowerBound();
    }
    
    @Override
    public double getAutoRangeMinimumExtent()
    {
        return minimum_auto_range_extent;
    }
    
    @Override
    public void setAutoRangeMinimumExtent(double extent)
    {
        this.minimum_auto_range_extent = extent;
        fireChangeEvent();
    }
    
    @Override
    public void setRange(double lower, double upper)
    {
        setRange(new Range(lower, upper));
    }
    
    @Override
    public void setRangeWithMargins(Range range)
    {
        if (range == null)
            throw new IllegalArgumentException("Null 'range' argument.");
        setRange(range.addMargins(lower_margin, upper_margin));
    }
    
    @Override
    public void centerRange(double value)
    {
        final double center = range.getCenterValue();
        final Range adj = new Range(range.getLowerBound() + value - center,
                range.getUpperBound() + value - center);
        setRange(adj);
    }
    
    @Override
    public void resizeRange(double percent)
    {
        resizeRange(percent, range.getCenterValue());
    }
    
    @Override
    public void resizeRange(double percent, double anchorValue)
    {
        if(percent > 0.0)
        {
            final double halfLength = range.getExtent() * percent / 2;
            final Range adj = new Range(anchorValue - halfLength,
                    anchorValue + halfLength);
            setRange(adj);
        }
        else
            setAutoRange(true);
    }
    
    @Override
    public void zoomRange(double lowerPercent, double upperPercent)
    {
        final double start = range.getLowerBound();
        final double length = range.getExtent();
        final Range adjusted = new Range(start + length * lowerPercent,
                    start + length * upperPercent);
        setRange(adjusted);
    }
    
    @Override
    public void pan(double percent)
    {
        final Range r = getRange();
        final double length = r.getExtent();
        final double adj = length * percent;
        final double lower = r.getLowerBound() + adj;
        final double upper = r.getUpperBound() + adj;
        setRange(lower, upper);
    }
    
    @Override
    public ValueTickFactory getTicksFactory()
    {
        return ticks_factory;
    }
    
    @Override
    public void setTicksFactory(ValueTickFactory factory)
    {
        ticks_factory = factory;
    }
    @Override
    public int getPosition()
    {
        return position;
    }
    @Override
    public void setPosition(int position)
    {
        this.position = position;;
    }
    @Override
    public int getOrientation()
    {
        return (position == ChartComponent.TOP || position == ChartComponent.BOTTOM)
                ?ChartComponent.HORIZONTAL:ChartComponent.VERTICAL;
    }
}
