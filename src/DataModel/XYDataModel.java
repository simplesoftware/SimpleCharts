
package DataModel;

import SimpleCharts.Axis.Range;

/**
 *
 * @author frank
 */
public interface XYDataModel<T> extends DataModel<T>
{
    public Range getDataDomain();
    public Range getDataRange();
}
