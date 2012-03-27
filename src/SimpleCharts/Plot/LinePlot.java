package SimpleCharts.Plot;

import DataModel.DataModel;
import DataModel.XYData;
import DataModel.XYDataModel;
import SimpleCharts.Axis.Axis;
import SimpleCharts.Axis.NumberAxis;
import SimpleCharts.Axis.ValueAxis;
import SimpleCharts.Chart.Border.CompoundBorder;
import SimpleCharts.Chart.Border.LineBorder;
import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Label;
import SimpleCharts.Chart.*;
import SimpleCharts.Chart.Border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class LinePlot extends AbstractContainer implements Plot, Axis.ChangeListener, DataModel.ChangeListener
{
    private int position;
    private int orientation;
    private String name;
    private ValueAxis domain_axis, range_axis;
    private Component legend_item;
    private Color line_color, point_color;
    private Stroke stroke;
    private int max_point_width;
    private int min_point_width;
    private boolean show_points;
    private XYDataModel<? extends XYData> data;
    private final List<Plot.ChangeListener> change_listeners;
    
    public LinePlot(String name)
    {
        this.name = name;
        this.position = CENTER;
        this.orientation = HORIZONTAL;
        domain_axis = new NumberAxis(ChartComponent.BOTTOM, "Domain");
        domain_axis.addListener(this);
        add(domain_axis);
        range_axis = new NumberAxis(ChartComponent.LEFT, "Range");
        range_axis.addListener(this);
        add(range_axis);
        change_listeners = new ArrayList<Plot.ChangeListener>();
        line_color = Color.black;
        point_color = null;
        stroke = new BasicStroke();
        max_point_width = 8;
        min_point_width = 3;
        show_points = true;
        final PlotArea plot_area = new LinePlotArea();
        setLayout(new PlotLayout(plot_area));
        add(plot_area);
    }
    @Override
    public int getPosition()
    {
        return position;
    }
    @Override
    public void setPosition(int position)
    {
        this.position = position;
    }
    @Override
    public int getOrientation()
    {
        return orientation;
    }
    @Override
    public void setOrientation(int orientation)
    {
        this.orientation = orientation;
    }
    @Override
    public String getName()
    {
        return name;
    }
    public ValueAxis getDomainAxis()
    {
        return domain_axis;
    }
    public ValueAxis getRangeAxis()
    {
        return range_axis;
    }
    public final void setDomainAxis(ValueAxis axis)
    {
        if(domain_axis != null)
        {
            remove(domain_axis);
            domain_axis.removeListener(this);
        }
        domain_axis = axis;
        domain_axis.addListener(this);
        add(domain_axis);
    }
    public final void setRangeAxis(ValueAxis axis)
    {
        if(range_axis != null)
        {
            range_axis.removeListener(this);
            remove(range_axis);
        }
        range_axis = axis;
        range_axis.addListener(this);
        add(range_axis);
    }
    public void setLineColor(Color color)
    {
        line_color = color;
    }
    public void setPointColor(Color color)
    {
        point_color = color;
    }
    public void setStroke(Stroke stroke)
    {
        this.stroke = stroke;
    }
    public void setShowPoints(boolean b)
    {
        show_points = b;
    }
    @Override
    public void addChangeListener(Plot.ChangeListener listener)
    {
        change_listeners.add(listener);
    }
    @Override
    public void removeChangeListener(Plot.ChangeListener listener)
    {
        change_listeners.remove(listener);
    }
    public void setDataModel(XYDataModel<? extends XYData> data)
    {
        if(this.data != null)
            this.data.removeChangeListener(this);
        this.data = data;
        if(data != null)
            data.addChangeListener(this);
        System.out.println("domain range = "
                +Utilities.formatDate(data.getDataDomain().getLowerBound())
                +" to "+Utilities.formatDate(data.getDataDomain().getUpperBound()));
        domain_axis.autoAdjustForRange(data.getDataDomain());
        range_axis.autoAdjustForRange(data.getDataRange());
        fireDataModelChanged();
    }
    @Override
    public void dataModelChanged()
    {
    }
    public void fireDataModelChanged()
    {
        for(Plot.ChangeListener listener : change_listeners)
            listener.dataModelChanged(this);
    }
    @Override
    public Component createLegendItem()
    {
        return legend_item = new LineLegendItem();
    }
    @Override
    public Component[] getLegendItems()
    {
        if(legend_item == null)
            legend_item = createLegendItem();
        return new Component[]{legend_item};
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    @Override
    public void axisChanged(Axis axis)
    {
    }
    private class LineLegendItem extends Label
    {
        private final static int gap = 5;
        private final static int line_length = 15;
        
        LineLegendItem()
        {
            super(LinePlot.this.getName());
        }
        @Override
        public void paintComponent(Graphics g)
        {
            final FontMetrics fm = g.getFontMetrics();
            final Insets insets = getInsets();
            final int height = getHeight();
            final Rectangle2D rect = fm.getStringBounds(getText(), g);
            final int y = (height - (int)rect.getHeight())/2 + fm.getAscent();
            g.setColor(getForeground());
            g.drawString(getText(), insets.left, y);
            final int x = rect.getBounds().width + gap;
            final int line_y = height/2;
            g.setColor(line_color);
            g.drawLine(x, line_y, x+line_length, line_y);
        }
        @Override
        public Dimension getPreferredSize()
        {
            final Graphics g = getGraphics();
            final FontMetrics fm = g.getFontMetrics();
            final Rectangle2D rect = fm.getStringBounds(getText(), g);
            return new Dimension(rect.getBounds().width+line_length+gap+1, rect.getBounds().height);
        }
    }
    private class LinePlotArea extends PlotArea
    {
        LinePlotArea()
        {
            setBorder(new CompoundBorder(new EmptyBorder(3,3,3,3), new LineBorder(Color.black)));
            setBackground(Color.white);
        }
        @Override
        public void paintComponent(Graphics g)
        {
            final Graphics2D g2 = (Graphics2D)g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            try
            {
                final Insets insets = getInsets();
                final int width = getWidth();
                final int height = getHeight();
                g2.setColor(getBackground());
                g2.fillRect(insets.left, insets.top, width-insets.left-insets.right, height-insets.top-insets.bottom);

                if(data != null || !data.isEmpty())
                {
                    g2.setStroke(stroke);
                    XYData item = data.get(0);
                    int last_x = domain_axis.valueToCoord(item.getX());
                    int last_y = range_axis.valueToCoord(item.getY());
                    final int point_width = Math.min(width/data.size(), max_point_width);
                    for(int i=1; i<data.size(); ++i)
                    {
                        item = data.get(i);
                        final int x = domain_axis.valueToCoord(item.getX());
                        final int y = range_axis.valueToCoord(item.getY());
                        g2.setColor(line_color);
                        g2.drawLine(last_x, last_y, x, y);
                        if(show_points && point_width > min_point_width)
                        {
                            final Color color = point_color!=null?point_color:line_color;
                            g2.setPaint(color);
                            g2.fillRect(last_x-point_width/2, last_y-point_width/2, point_width, point_width);
                            g2.setPaint(line_color);
                            g2.drawRect(last_x-point_width/2, last_y-point_width/2, point_width-1, point_width-1);
                        }
                        last_x = x;
                        last_y = y;
                    }
                    if(show_points && point_width > min_point_width)
                    {
                        final Color color = point_color!=null?point_color:line_color;
                        g2.setPaint(color);
                        g2.fillRect(last_x-point_width/2, last_y-point_width/2, point_width, point_width);
                        g2.setPaint(line_color);
                        g2.drawRect(last_x-point_width/2, last_y-point_width/2, point_width-1, point_width-1);
                    }
                }
            }
            finally
            {
                g2.dispose();
            }
        }
    }
}
