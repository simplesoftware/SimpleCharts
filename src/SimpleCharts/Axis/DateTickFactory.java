package SimpleCharts.Axis;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author frank
 */
public class DateTickFactory implements ValueTickFactory
{
    private final static long millisecond = 1;
    private final static long second = millisecond*1000;
    private final static long minute = second*60;
    private final static long hour = minute*60;
    private final static long day = hour*24;
    private final static long month = day*30;
    private final static long year = month*13;
    private Format formatter;
    private double tick_size;
    private double min_value, max_value;
    
    @Override
    public String createLabel(double value)
    {
        return formatter.format((long)value);
    }
    @Override
    public ValueTick getTickMark(double value)
    {
        return new ValueTick(createLabel(value), value);
    }
    @Override
    public double getTickSize()
    {
        return tick_size;
    }
    @Override
    public double getMinValue()
    {
        return min_value;
    }
    @Override
    public double getMaxValue()
    {
        return max_value;
    }
    @Override
    public Format getFormat()
    {
        return formatter;
    }
    @Override
    public void setFormat(Format format)
    {
        formatter = format;
    }
    @Override
    public double calculateTickSize(Range range, int max_labels, double min_tick_size)
    {
        if(max_labels < 2 || Double.isNaN(range.getUpperBound()) || Double.isNaN(range.getLowerBound()))
            return 0.0;
        final double extent = range.getExtent();
        tick_size = extent/max_labels;
        final long unit;
        if(tick_size < second)
        {
            unit = millisecond;
            formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        }
        else if(tick_size < minute)
        {
            unit = second;
            formatter = new SimpleDateFormat("HH:mm:ss");
        }
        else if(tick_size < hour)
        {
            unit = minute;
            formatter = new SimpleDateFormat("HH::mm");
        }
        else if(tick_size < day)
        {
            unit = hour;
            formatter = new SimpleDateFormat("MMM dd HH::mm");
        }
        else if(tick_size < month)
        {
            unit = day;
            formatter = new SimpleDateFormat("MMM dd");
        }
        else if(tick_size < year)
        {
            unit = month;
            formatter = new SimpleDateFormat("MMM yyyy");
        }
        else
        {
            unit = year;
            formatter = new SimpleDateFormat("yyyy");
        }
        return nice_tick(tick_size/unit, unit)*unit;
    }
    @Override
    public List<ValueTick> generateLabels(Range range, int max_labels, double min_tick_size)
    {
        final List<ValueTick> result = new ArrayList<ValueTick>();
        if(max_labels < 2 || Double.isNaN(range.getUpperBound()) || Double.isNaN(range.getLowerBound()))
            return result;
        final double extent = range.getExtent();
        tick_size = extent/max_labels;
        final long unit;
        if(tick_size < second)
        {
            unit = millisecond;
            formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        }
        else if(tick_size < minute)
        {
            unit = second;
            formatter = new SimpleDateFormat("HH:mm:ss");
        }
        else if(tick_size < hour)
        {
            unit = minute;
            formatter = new SimpleDateFormat("HH::mm");
        }
        else if(tick_size < day)
        {
            unit = hour;
            formatter = new SimpleDateFormat("MMM dd HH::mm");
        }
        else if(tick_size < month)
        {
            unit = day;
            formatter = new SimpleDateFormat("MMM dd");
        }
        else if(tick_size < year)
        {
            unit = month;
            formatter = new SimpleDateFormat("MMM yyyy");
        }
        else
        {
            unit = year;
            formatter = new SimpleDateFormat("yyyy");
        }
        tick_size = nice_tick(tick_size/unit, unit)*unit;
        min_value = Math.floor(range.getLowerBound()/tick_size)*tick_size;
        max_value = Math.ceil(range.getUpperBound()/tick_size)*tick_size;

        for(double x = min_value; x <= max_value+tick_size*0.5; x += tick_size)
        {
            if(x >= range.getLowerBound() && x < range.getUpperBound())
                result.add(new ValueTick(formatter.format(x), x));
        }
        return result;
    }
    private double nice_tick(double tick_size, long unit)
    {
        final int exp = (int)Math.floor(Math.log10(tick_size));
        final double fract = tick_size/Math.pow(10, exp);
        
        final double nf;
        if(unit == minute || unit == second)
        {
            if(exp == 0)
            {
                if(fract < 1.5) nf = 1.0;
                else if(fract < 3.0) nf = 2.0;
                else nf = 5.0;
            }
            else
            {
                if(fract < 1.3) nf = 1.0;
                else if(fract <= 1.5) nf = 1.5;
                else if(fract < 5) nf = 3.0;
                else nf = 10.0;
            }
        }
        else if(unit == hour)
        {
            if(exp == 0)
            {
                if(fract <= 1.5) nf = 1.0;
                else if(fract <= 4.0) nf = 2.0;
                else nf = 6.0;
            }
            else
            {
                if(fract <= 1.5) nf = 1.2;
                else nf = 10.0;
            }
        }
        else
        {
            if(fract < 1.5) nf = 1;
            else if(fract < 4) nf = 3;
            else if(fract < 7) nf = 5;
            else nf = 10;
        }
        return nf*Math.pow(10, exp);
    }
}
