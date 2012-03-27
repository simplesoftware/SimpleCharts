package SimpleCharts.Chart;

import SimpleCharts.ChartPanel;
import SimpleCharts.Plot.Plot;

/**
 * The <code>Chart</code> interface
 */
public interface Chart extends Component, Container
{
    /**
     * Get the <code>Plot</code> for this <code>Chart</code> .
     * @return the <code>Plot</code> for this <code>Chart</code>.
     * @see Component
     */
    public Plot getPlot();
    /**
     * Set the plot for this chart.
     * @param plot - the new plot for this chart.
     */
    public void setPlot(Plot plot);
    /**
     * Get the legend for this <code>Chart</code>.
     * @return the <code>Container</code> representing the legend or <code>null</code> if not set.
     * @see Component
     */
    public Container getLegend();
    
    /**
     * Set the legend for this chart.
     * @param legend - the new legend for this chart.
     */
    public void setLegend(Container legend);
    
    /**
     * Get the title for this <code>Chart</code>.
     * @return the <code>Component</code> representing the chart title or <code>null</code> if not set.
     * @see Component
     */
    public Component getTitle();
    
    /**
     * Set the title for this chart.
     * @param title - the new title component for this chart.
     */
    public void setTitle(Component title);
    
    /**
     * Get the panel for this chart.
     * @return the (@link ChartPanel) for this chart
     */
    public ChartPanel getChartPanel();
    
    /**
     * Set the panel  for this chart.
     * @param panel the new (@link ChartPanel) for this chart.
     */
    public void setChartPanel(ChartPanel panel);
    
    /**
     * Add a listener to be notified about chart change events.
     * @param listener - the listener to add.
     */
    public void addChangeListener(ChangeListener listener);
    
    /**
     * Remove the specified listener from the list of subscribers to
     * be notified from chart change events.
     * @param listener - the listener to remove.
     */
    public void removeChangeListener(ChangeListener listener);
    
    /**
     * An event listener for Chart
     */
    public interface ChangeListener
    {
        /**
         * Invoked after a chart undergoes a change that requires a repaint.
         * @param event the (@link Chart.ChangeEvent) that caused the change.
         */
        public void chartChanged(ChangeEvent event);
    }
    /**
     * Chart event
     */
    public interface ChangeEvent
    {
    }
}
