package SimpleCharts.Chart;

import SimpleCharts.Chart.Layout.ChartLayout;
import SimpleCharts.ChartPanel;
import SimpleCharts.Plot.Plot;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPopupMenu;

/**
 *
 * @author frank
 */
public class SimpleChart extends AbstractContainer implements Chart, Plot.ChangeListener
{
    private ChartPanel panel;
    private Plot plot;
    private Container legend;
    private Component title;
    private List<Component> subtitles;
    private List<Chart.ChangeListener> change_listeners;
    
    public SimpleChart()
    {
        this(null, null, new Legend());
    }
    public SimpleChart(String title)
    {
        this(new ChartTitle(title), null, new Legend());
    }
    public SimpleChart(Component title)
    {
        this(title, null, new Legend());
    }
    public SimpleChart(Component title, Plot plot)
    {
        this(title, plot, new Legend());
    }
    public SimpleChart(String title, Plot plot)
    {
        this(new ChartTitle(title), plot, new Legend());
    }
    public SimpleChart(Component title, Plot plot, Container legend)
    {
        setLayout(new ChartLayout());
        setTitle(title);
        setLegend(legend);
        setPlot(plot);
        this.subtitles = new ArrayList<Component>();
        this.change_listeners = new ArrayList<Chart.ChangeListener>();
    }
    @Override
    public Plot getPlot()
    {
        return plot;
    }
    @Override
    public final void setPlot(Plot plot)
    {
        if(this.plot != null)
        {
            plot.removeChangeListener(this);
            if(this.plot instanceof LegendItemSource && legend != null)
            {
                final Component[] legend_items = plot.getLegendItems();
                for(Component c : legend_items)
                    legend.remove(c);
            }
            remove(this.plot);
        }
        this.plot = plot;
        if(plot != null)
        {
            add(plot);
            getLegendItems();
        }
    }
    @Override
    public Container getLegend()
    {
        return legend;
    }
    @Override
    public final void setLegend(Container legend)
    {
        if(this.legend != null)
            remove(this.legend);
        this.legend = legend;
        if(legend != null)
        {
            add(legend);
            getLegendItems();
        }
    }
    private void getLegendItems()
    {
        if(plot != null)
        {
            if(plot instanceof LegendItemSource && legend != null)
            {
                final Component[] legend_items = plot.getLegendItems();
                for(Component c : legend_items)
                    legend.add(c);
            }
        }
    }
    @Override
    public Component getTitle()
    {
        return title;
    }
    @Override
    public final void setTitle(Component title)
    {
        if(this.title != null)
            remove(this.title);
        this.title = title;
        if(title != null)
            add(title);
    }
    @Override
    public ChartPanel getChartPanel()
    {
        return panel;
    }
    @Override
    public void setChartPanel(ChartPanel panel)
    {
        this.panel = panel;
    }
    @Override
    public Graphics getGraphics()
    {
        return panel!=null?panel.getGraphics():null;
    }
    @Override
    public Font getFont()
    {
        return panel!=null?panel.getFont():null;
    }
    @Override
    public Color getBackground()
    {
        return panel!=null?panel.getBackground():null;
    }
    @Override
    public Color getForeground()
    {
        return panel!=null?panel.getForeground():null;
    }
    @Override
    public void scrollRectToVisible(Rectangle rectangle)
    {
        if(panel != null)
            panel.scrollRectToVisible(rectangle);
    }
    @Override
    public JPopupMenu getComponentPopupMenu()
    {
        return panel!=null?panel.getComponentPopupMenu():null;
    }
    @Override
    public void repaint()
    {
        if(panel != null)
        {
            System.out.println("panel repaint requested");
            panel.repaint();
        }
    }
    @Override
    public void repaint(int x, int y, int width, int height)
    {
        if(panel != null)
        {
            System.out.println("selective repaint called");
            panel.repaint(x, y, width, height);
        }
    }
    @Override
    public void revalidate()
    {
        if(isValid() == false && panel != null)
        {
            System.out.println("panel revalidate called");
            panel.revalidate();
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    @Override
    public void addChangeListener(Chart.ChangeListener listener)
    {
        change_listeners.add(listener);
    }
    @Override
    public void removeChangeListener(Chart.ChangeListener listener)
    {
        change_listeners.remove(listener);
    }
    @Override
    public void dataModelChanged(Plot plot)
    {
        
    }
}
