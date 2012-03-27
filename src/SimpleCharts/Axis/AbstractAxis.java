package SimpleCharts.Axis;

import SimpleCharts.Chart.AbstractComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;

/**
 * The base class for all axes in SimpleCharts.
 * @see Axis
 */
abstract public class AbstractAxis extends AbstractComponent implements Axis
{
    /** The default axis label font. */
    public static final Font DEFAULT_AXIS_LABEL_FONT = new Font("SansSerif", Font.PLAIN, 12);
    
    /** The default axis label paint. */
    public static final Paint DEFAULT_AXIS_LABEL_PAINT = Color.black;
    
    /** The default axis line paint. */
    public static final Paint DEFAULT_AXIS_LINE_PAINT = Color.gray;

    /** The default tick label font. */
    public static final Font DEFAULT_TICK_LABEL_FONT = new Font("SansSerif", Font.PLAIN, 10);

    /** The default tick label paint. */
    public static final Paint DEFAULT_TICK_LABEL_PAINT = Color.black;
    
    /** The default tick paint. */
    public static final Paint DEFAULT_TICK_MARK_PAINT = Color.gray;
    
    /** The label for the axis. */
    private String label;
    
    /** The font for displaying the axis label. */
    private Font label_font;
    
    /** The paint for drawing the axis label. */
    private Paint label_paint;
    
    /** The label angle. */
    private double label_angle;
    
    /** The paint used for the axis line. */
    private Paint axis_line_paint;
    
    /**
     * A flag that indicates whether or not minor tick marks are visible for the
     * axis.
     */
    private boolean is_minor_tick_visible;
    
    /**
     * A flag indicating whether or not this axis is inverted.
     */
    private boolean is_inverted;
    
    /** The font used to display the tick labels. */
    private Font tick_label_font;
    
    /** The color used to display the tick labels. */
    private Paint tick_label_paint;
    
    /** The paint used to draw tick marks. */
    private Paint tick_mark_paint;
    
    /** The list of change listeners. */
    private final List<ChangeListener> change_listeners;
    
    /**
     * Constructs an axis, using default values where necessary.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    protected AbstractAxis(String label)
    {
        this.label = label;
        label_font = DEFAULT_AXIS_LABEL_FONT;
        label_paint = DEFAULT_AXIS_LABEL_PAINT;
        axis_line_paint = DEFAULT_AXIS_LINE_PAINT;
        tick_label_font = DEFAULT_TICK_LABEL_FONT;
        tick_label_paint = DEFAULT_TICK_LABEL_PAINT;
        tick_mark_paint = DEFAULT_TICK_MARK_PAINT;
        label_angle = 0.0;
        is_minor_tick_visible = false;
        change_listeners = new ArrayList<ChangeListener>();
    }

    @Override
    public void addListener(ChangeListener listener)
    {
        change_listeners.add(listener);
    }

    @Override
    public Paint getAxisLinePaint()
    {
        return axis_line_paint;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public double getLabelAngle()
    {
        return label_angle;
    }

    @Override
    public Font getLabelFont()
    {
        return label_font;
    }

    @Override
    public Paint getLabelPaint()
    {
        return label_paint;
    }

    @Override
    public Font getTickLabelFont()
    {
        return tick_label_font;
    }

    @Override
    public Paint getTickLabelPaint()
    {
        return tick_label_paint;
    }

    @Override
    public Paint getTickMarkPaint()
    {
        return tick_mark_paint;
    }

    @Override
    public boolean isMinorTicksVisible()
    {
        return is_minor_tick_visible;
    }

    @Override
    public boolean isInverted()
    {
        return is_inverted;
    }
    
    @Override
    public void setInverted(boolean flag)
    {
        this.is_inverted = flag;
    }
    
    @Override
    public void removeListener(ChangeListener listener)
    {
        change_listeners.remove(listener);
    }

    @Override
    public void setAxisLinePaint(Paint paint)
    {
        if (paint == null)
            throw new IllegalArgumentException("Null 'paint' argument.");
        this.axis_line_paint = paint;
        fireChangeEvent();
    }

    @Override
    public void setMinorTicksVisible(boolean flag)
    {
        if(flag != isMinorTicksVisible())
        {
            this.is_minor_tick_visible = flag;
            fireChangeEvent();
        }
    }

    @Override
    public void setLabel(String label)
    {
        final String oldLable = this.label;
        this.label = label;
        if(oldLable != null)
        {
            if(oldLable.equals(label))
                fireChangeEvent();
        }
        else
        {
            if(label != null)
                fireChangeEvent();
        }
    }

    @Override
    public void setLabelAngle(double angle)
    {
        this.label_angle = angle;
        fireChangeEvent();
    }

    @Override
    public void setLabelFont(Font font)
    {
        if (font == null)
            throw new IllegalArgumentException("Null 'font' argument.");
        if(this.label_font != font)
        {
            label_font = font;
            fireChangeEvent();
        }
    }

    @Override
    public void setLabelPaint(Paint paint)
    {
        this.label_paint = paint;
        fireChangeEvent();
    }

    @Override
    public void setTickLabelFont(Font font)
    {
        if (font == null)
            throw new IllegalArgumentException("Null 'font' argument.");
        if(this.tick_label_font != font)
        {
            this.tick_label_font = font;
            fireChangeEvent();
        }
    }

    @Override
    public void setTickLabelPaint(Paint paint)
    {
        if (paint == null)
            throw new IllegalArgumentException("Null 'paint' argument.");
        if(this.tick_label_paint != paint)
        {
            this.tick_label_paint = paint;
            fireChangeEvent();
        }
    }

    @Override
    public void setTickMarkPaint(Paint paint)
    {
        if (paint == null)
            throw new IllegalArgumentException("Null 'paint' argument.");
        if(this.tick_mark_paint != paint)
        {
            this.tick_mark_paint = paint;
            fireChangeEvent();
        }
    }
    
    /**
     * Sets a flag that controls whether or not the axis is visible notifies all registered listeners.
     *
     * @param flag  the flag.
     *
     * @see #isVisible()
     */
    @Override
    public void setVisible(boolean flag)
    {
        if(flag != isVisible())
        {
            super.setVisible(flag);
            fireChangeEvent();
        }
    }
    
    /**
     * Notify all registered listeners.
     */
    protected void fireChangeEvent()
    {
        for(ChangeListener listener : change_listeners)
            listener.axisChanged(this);
    }
}
