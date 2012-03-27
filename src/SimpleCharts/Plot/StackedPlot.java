package SimpleCharts.Plot;

import java.util.List;

/**
 *
 * @author frank
 */
abstract public class StackedPlot implements Plot
{
    private List<Plot> left, right, top, bottom;
}
