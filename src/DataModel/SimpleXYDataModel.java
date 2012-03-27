package DataModel;

import SimpleCharts.Axis.Range;
import java.util.List;

/**
 *
 * @author frank
 */
public class SimpleXYDataModel extends SimpleDataModel<XYData> implements XYDataModel<XYData>
{
    private Range domain, range;
    
    public SimpleXYDataModel(List<? extends XYData> data)
    {
        super(data);
        calculateRanges();
    }
    @Override
    public Range getDataDomain()
    {
        return domain;
    }
    @Override
    public Range getDataRange()
    {
        return range;
    }
    private void calculateRanges()
    {
        if(!isEmpty())
        {
            double x_high, x_low;
            double y_high, y_low;
            XYData item = get(0);
            x_high = x_low = item.getX();
            y_high = y_low = item.getY();
            for(int i=1; i<size(); ++i)
            {
                item = get(i);
                x_high = Math.max(x_high, item.getX());
                x_low = Math.min(x_low, item.getX());
                y_high = Math.max(y_high, item.getY());
                y_low = Math.min(y_low, item.getY());
            }
            domain = new Range(x_low, x_high);
            range = new Range(y_low, y_high);
        }
    }
}
