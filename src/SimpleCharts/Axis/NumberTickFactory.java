package SimpleCharts.Axis;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
public class NumberTickFactory implements ValueTickFactory
{
    private double tick_size, min_value, max_value;
    private Format formatter;
    
    public NumberTickFactory()
    {
        this(new DecimalFormat("#,##0.#########"));
    }
    public NumberTickFactory(NumberFormat formatter)
    {
        this.formatter = formatter;
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
    public String createLabel(double value)
    {
        return formatter.format(value);
    }
    @Override
    public ValueTick getTickMark(double value)
    {
        return new ValueTick(createLabel(value), value);
    }
    @Override
    public Format getFormat()
    {
        return formatter;
    }
    @Override
    public void setFormat(Format format)
    {
        this.formatter = format;
    }
    @Override
    public double calculateTickSize(Range range, int max_labels, double min_tick_size)
    {
        if(max_labels < 2)
            return 0.0;
        final double r = nicenum(range.getExtent(), false);
        return Math.max(Double.isNaN(min_tick_size)?0:min_tick_size,nicenum(r/(max_labels-1), true));
    }
    @Override
    public List<ValueTick> generateLabels(Range range, int max_labels, double min_tick_size)
    {
        final List<ValueTick> result = new ArrayList<ValueTick>();
        if(max_labels < 2 || Double.isNaN(range.getUpperBound()) || Double.isNaN(range.getLowerBound()))
            return result;
        final double r = nicenum(range.getExtent(), false);
        tick_size = Math.max(Double.isNaN(min_tick_size)?0:min_tick_size,nicenum(r/(max_labels-1), true));
        assert !Double.isNaN(range.getUpperBound());
        assert !Double.isNaN(range.getLowerBound());
        min_value = Math.floor(range.getLowerBound()/tick_size)*tick_size;
        max_value = Math.ceil(range.getUpperBound()/tick_size)*tick_size;
        for(double x = min_value; x <= max_value; x += tick_size)
        {
            if(result.isEmpty() == false && x == result.get(result.size()-1).getValue())
                break;
            result.add(new ValueTick(createLabel(x), x));
        }
        return result;
    }
    private static double nicenum(double x, boolean round)
    {
        final double exp = (int)Math.floor(Math.log10(x));
        final double f = x/Math.pow(10, exp);
        final double nf;
        if(round)
        {
            if(f < 1.5) nf = 1;
            else if(f < 3) nf = 2;
            else if(f < 7) nf = 5;
            else nf = 10;
        }
        else
        {
            if(f <= 1) nf = 1;
            else if(f <= 2) nf = 2;
            else if(f <= 5) nf = 5;
            else nf = 10;
        }
        return nf*Math.pow(10,exp);
    }
}
