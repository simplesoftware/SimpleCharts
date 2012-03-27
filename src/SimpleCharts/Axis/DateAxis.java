package SimpleCharts.Axis;

import SimpleCharts.Chart.ChartComponent;
import SimpleCharts.Chart.Utilities;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.List;

/**
 * An axis for displaying numerical data.
 * <P>
 * If the axis is set up to automatically determine its range to fit the data,
 * you can ensure that the range includes zero (statisticians usually prefer
 * this) by setting the <code>autoRangeIncludesZero</code> flag to
 * <code>true</code>.
 * <P>
 * The <code>NumberAxis</code> class has a mechanism for automatically
 * selecting a tick unit that is appropriate for the current axis range. 
 */
public class DateAxis extends AbstractValueAxis
{
    /** The default value for the autoRangeIncludesZero flag. */
    public static final boolean DEFAULT_AUTO_RANGE_INCLUDES_ZERO = false;
    
    public static final ValueTickFactory DEFAULT_TICK_FACTORY = new DateTickFactory();
    
    /**
     * A flag that affects the axis range when the range is determined
     * automatically.  If the auto range does NOT include zero and this flag
     * is TRUE, then the range is changed to include zero.
     */
    private boolean autoRangeIncludesZero;
    
    /** The override number format. */
    private NumberFormat numberFormatOverride;

    /**
     * TODO - following should be in a renderer
     */
    private double label_spacing;
    private double label_margin_in_m;
    private int tick_mark_length;
    
    /**
     * Constructs a number axis, using default values where necessary.
     * 
     * @param position - the chart position for this axis (one of ChartComponent position constants).
     */
    public DateAxis(int position)
    {
        this(position, null);
    }
    /**
     * Constructs a number axis, using default values where necessary.
     *
     * @param position - the chart position for this axis (one of ChartComponent position constants).
     * @param label  the axis label (<code>null</code> permitted).
     */
    public DateAxis(int position, String label)
    {
        super(position, label, DEFAULT_TICK_FACTORY);
        this.autoRangeIncludesZero = DEFAULT_AUTO_RANGE_INCLUDES_ZERO;
        this.numberFormatOverride = null;
        
        /**
         * TODO following should be in a renderer
         */
        this.label_spacing = 1;
        this.tick_mark_length = 6;
        this.label_margin_in_m = 0.5;
    }
    
    /**
     * Returns the flag that indicates whether or not the automatic axis range
     * (if indeed it is determined automatically) is forced to include zero.
     *
     * @return The flag.
     */
    public boolean getAutoRangeIncludesZero()
    {
        return this.autoRangeIncludesZero;
    }
    /**
     * Sets the flag that indicates whether or not the axis range, if
     * automatically calculated, is forced to include zero.
     * <p>
     * If the flag is changed to <code>true</code>, the axis range is
     * recalculated.
     * <p>
     * Any change to the flag will trigger an change event notification.
     *
     * @param flag  the new value of the flag.
     *
     * @see #getAutoRangeIncludesZero()
     */
    public void setAutoRangeIncludesZero(boolean flag)
    {
        if (this.autoRangeIncludesZero != flag)
        {
            this.autoRangeIncludesZero = flag;
            fireChangeEvent();
        }
    }
    
    @Override
    public void autoAdjustForRange(Range data_range)
    {
        if(!this.isAutoRange())
            return;
        double upper = data_range.getUpperBound();
        double lower = data_range.getLowerBound();
        if(getAutoRangeIncludesZero())
        {
            lower = Math.min(lower, 0.0);
            upper = Math.max(0.0, upper);
        }
        final double extent = upper - lower;
        final double min_extent = getAutoRangeMinimumExtent();
        if(extent < min_extent)
        {
            final double expand = (min_extent - extent)/2;
            upper = upper + expand;
            lower = lower - expand;
            if(lower == upper)
            {
                final double adjust = Math.abs(lower)/10.0;
                lower = lower - adjust;
                upper = upper + adjust;
            }
        }
        upper += getUpperMargin() * extent;
        lower -= getLowerMargin() * extent;
        setRange(new Range(lower, upper));
    }

    @Override
    public double coordToValue(int coord)
    {
        final Range r = getRange();
        final double extent = r.getExtent();
        final boolean horizontal = getOrientation()==ChartComponent.HORIZONTAL;
        final double length = horizontal?getWidth():getHeight();
        final double value = (coord/length)*extent;
        final boolean inverted = (isInverted() == horizontal);
        if(inverted)
            return r.getUpperBound() - value;
        else
            return value + r.getLowerBound();
    }

    @Override
    public int valueToCoord(double value)
    {
        final Range r = getRange();
        final double extent = r.getExtent();
        final boolean horizontal = getOrientation()==ChartComponent.HORIZONTAL;
        final double length = horizontal?getWidth():getHeight();
        final boolean inverted = (isInverted() == horizontal);
        if(inverted)
            return (int)(length - (value-r.getLowerBound())/extent*length);
        else
            return (int)((value - r.getLowerBound())/extent * length);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        final int width = getWidth();
        final int height = getHeight();

        final FontMetrics font_metrics = g.getFontMetrics();
        final double label_margin = font_metrics.charWidth('M')*label_margin_in_m;
        final int ascent = font_metrics.getAscent();
        final int descent = font_metrics.getDescent();
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        g.setColor(getForeground());
        
        if(getOrientation() == ChartComponent.VERTICAL)
        {
            final int max_labels = (int)(height/(font_metrics.getHeight()*label_spacing));
            final List<ValueTick> tick_marks = getTicksFactory().generateLabels(getRange(), max_labels, label_spacing);
            if(tick_marks.isEmpty())
                return;

            // find maximum label width
            double max_label_width = -1;
            for(ValueTick tick : tick_marks)
            {
                final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                max_label_width = Math.max(max_label_width, label_bounds.getWidth());
            }
            if(getPosition()==ChartComponent.LEFT)
            {
                final int axis_line_x = width-1;
                g.drawLine(axis_line_x, 0, axis_line_x, height-1);
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                    final int label_x = (int)(max_label_width - label_bounds.getWidth() + label_margin);
                    final int label_y = valueToCoord(tick.getValue());
                    if(label_y+descent >= height || label_y-ascent < 0)
                        continue;
                    g.drawString(tick.getLabel(), label_x, label_y+label_bounds.getBounds().height/3);
                    g.drawLine(axis_line_x, label_y, axis_line_x-tick_mark_length, label_y);
                }
            }
            else // RIGHT AXIS
            {
                final int axis_line_x = 0;
                g.drawLine(axis_line_x, 0, axis_line_x, height-1);
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                    final int label_x = (int)(axis_line_x + tick_mark_length + label_margin);
                    final int label_y = valueToCoord(tick.getValue());
                    if(label_y+descent >= height || label_y-ascent < 0)
                        continue;
                    g.drawString(tick.getLabel(), label_x, label_y+label_bounds.getBounds().height/3);
                    g.drawLine(axis_line_x, label_y, axis_line_x+tick_mark_length, label_y);
                }
            }
        }
        else // HORIZONTAL
        {
            // find optimal number of tick marks
            int max_labels = (int)(width/(font_metrics.getMaxAdvance()*label_spacing));
            double max_width;
            List<ValueTick> tick_marks;
            do
            {
                max_labels--;
                tick_marks = getTicksFactory().generateLabels(getRange(), max_labels, label_spacing);
                max_width = 0;
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                    max_width = Math.max(max_width, label_bounds.getWidth());
                }
                max_width += label_margin;
            } while(tick_marks.size()*max_width > width);
            if(tick_marks.isEmpty())
                return;
            
            if(getPosition()==ChartComponent.BOTTOM)
            {
                final int axis_line_y = 0;
                g.drawLine(0, axis_line_y, width-1, axis_line_y);
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                    final LineMetrics line_metrics = font_metrics.getLineMetrics(tick.getLabel(), g);
                    final int label_x = valueToCoord(tick.getValue());
                    final int label_y = (int)(axis_line_y + tick_mark_length + label_margin + line_metrics.getAscent());
                    if(label_x+label_bounds.getWidth()/2 >= width || label_x - label_bounds.getWidth()/2< 0)
                        continue;
                    g.drawString(tick.getLabel(), label_x-(int)(label_bounds.getWidth()/2), label_y);
                    g.drawLine((int)label_x, axis_line_y, (int)label_x, axis_line_y+tick_mark_length);
                }
            }
            else // TOP AXIS
            {
                final int axis_line_y = (int)(font_metrics.getHeight() + label_margin*2 + tick_mark_length);
                g.drawLine(0, axis_line_y, width-1, axis_line_y);
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), g);
                    final LineMetrics line_metrics = font_metrics.getLineMetrics(tick.getLabel(), g);
                    final int label_x = valueToCoord(tick.getValue());
                    final int label_y = (int)(line_metrics.getAscent() + label_margin);
                    if(label_x+label_bounds.getWidth()/2 >= width || label_x - label_bounds.getWidth()/2< 0)
                        continue;
                    g.drawString(tick.getLabel(), label_x-(int)(label_bounds.getWidth()/2), label_y);
                    g.drawLine((int)label_x, axis_line_y, (int)label_x, axis_line_y-tick_mark_length);
                }
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        // TODO in renderer
        final Insets insets = getParent().getInsets();
        final int width = getParent().getWidth() - insets.left - insets.right;
        final int height = getParent().getHeight() - insets.top - insets.bottom;

        final FontMetrics font_metrics = getFontMetrics(getFont());
        
        if(getOrientation() == ChartComponent.VERTICAL)
        {
            final int max_labels = (int)(height/(font_metrics.getHeight()*label_spacing));
            final List<ValueTick> tick_marks = getTicksFactory().generateLabels(getRange(), max_labels, label_spacing);
            double max_label_width = 0;
            if(tick_marks != null)
                for(ValueTick tick : tick_marks)
                {
                    final Rectangle2D label_bounds = font_metrics.getStringBounds(tick.getLabel(), getGraphics());
                    max_label_width = Math.max(max_label_width, label_bounds.getWidth());
                }
            final double label_gap = font_metrics.charWidth('M')*label_margin_in_m;
            max_label_width += (label_gap*2 + tick_mark_length);
            return new Dimension((int)max_label_width+1, height);
        }
        else
        {
            final double label_gap = font_metrics.charWidth('M')*label_margin_in_m;
            return new Dimension(width, (int)(font_metrics.getHeight()+label_gap*2+tick_mark_length+1));
        }
    }
}
