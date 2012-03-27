package SimpleCharts.Axis;

import java.text.Format;
import java.util.List;

/**
 *
 * @author frank
 */
public interface ValueTickFactory
{
    public double calculateTickSize(Range range, int max_labels, double min_tick_size);
    public List<ValueTick> generateLabels(Range range, int max_labels, double min_tick_size);
    public ValueTick getTickMark(double value);
    public double getTickSize();
    public double getMinValue();
    public double getMaxValue();
    public String createLabel(double value);
    public void setFormat(Format format);
    public Format getFormat();
}
