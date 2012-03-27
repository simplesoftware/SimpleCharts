package SimpleCharts.Plot;

import SimpleCharts.Chart.ChartComponent;
import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Container;
import SimpleCharts.Chart.LegendItemSource;

/**
 * The interface for all plots in SimpleCharts.
 * 
 */
public interface Plot extends Container, LegendItemSource, ChartComponent
{
    public String getName();
    public void addChangeListener(ChangeListener listener);
    public void removeChangeListener(ChangeListener listener);
    public Component createLegendItem();
    
    public interface ChangeListener
    {
        public void dataModelChanged(Plot plot);
    }
}
