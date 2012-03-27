package SimpleCharts.Plot;

import SimpleCharts.Axis.Axis;
import SimpleCharts.Chart.ChartComponent;
import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Container;
import SimpleCharts.Chart.Layout.LayoutManager;
import java.awt.Dimension;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class PlotLayout implements LayoutManager
{
    private final PlotArea plot_area;
    
    public PlotLayout(PlotArea plot_area)
    {
        this.plot_area = plot_area;
    }
    @Override
    public void addLayoutComponent(String hint, Component component)
    {
    }
    @Override
    public void removeLayoutComponent(Component component)
    {
    }
    @Override
    public Dimension minimumLayoutSize(Container container)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public Dimension preferredLayoutSize(Container container)
    {
        final int width = container.getWidth();
        final int height = container.getHeight();
        final Insets insets = container.getInsets();

        int left = insets.left;
        int top = insets.top;
        int right = width - insets.right - 1;
        int bottom = height - insets.bottom - 1;
        for(int i=0; i<container.getComponentCount(); ++i)
        {
            final ChartComponent component = (ChartComponent)container.getComponent(i);
            if(component == plot_area)
                continue;
            final Dimension dim = component.getPreferredSize();
            switch(component.getPosition())
            {
                case ChartComponent.TOP:
                    top += dim.getHeight();
                    break;
                case ChartComponent.LEFT:
                    left += dim.getWidth();
                    break;
                case ChartComponent.BOTTOM:
                    bottom -= dim.getHeight();
                    break;
                case ChartComponent.RIGHT:
                    right -= dim.getWidth();
                    break;
            }
        }
        return new Dimension(right-left+1, bottom-top+1);
    }
    @Override
    public void layoutContainer(Container container)
    {
        final int width = container.getWidth();
        final int height = container.getHeight();
        final Insets insets = container.getInsets();
        final Insets plot_area_insets = plot_area.getInsets();
        System.out.println("Laying out plot width="+width+", height="+height+", ncomps="+container.getComponentCount());
        
        /**
         * Size axis
         */
        int left = insets.left;
        int top = insets.top;
        int right = width - insets.right - 1;
        int bottom = height - insets.bottom - 1;
        int plot_area_width;
        int plot_area_height;
        
        do
        {
            plot_area_width = right - left - plot_area_insets.left - plot_area_insets.right + 1;
            plot_area_height = bottom - top - plot_area_insets.top - plot_area_insets.bottom + 1;
            left = insets.left;
            top = insets.top;
            right = width - insets.right - 1;
            bottom = height - insets.bottom - 1;
            for(int i=0; i<container.getComponentCount(); ++i)
            {
                if(container.getComponent(i) == plot_area)
                    continue;

                final Axis a = (Axis)container.getComponent(i);
                if(!a.isVisible())
                    continue;

                final Dimension d = a.getPreferredSize();
                switch(a.getPosition())
                {
                    case ChartComponent.LEFT:
                        a.setSize(d.width, plot_area_height);
                        left += d.width;
                        break;
                    case ChartComponent.RIGHT:
                        a.setSize(d.width, plot_area_height);
                        right -= d.width;
                        break;
                    case ChartComponent.TOP:
                        a.setSize(plot_area_width, d.height);
                        top += d.height;
                        break;
                    case ChartComponent.BOTTOM:
                        a.setSize(plot_area_width, d.height);
                        bottom -= d.height;
                        break;
                }
            }
        } while(plot_area_width > right - left + 1
                || plot_area_height > bottom - top + 1);

        /*
         * Layout plot area
         */
        plot_area_width += (plot_area_insets.left+plot_area_insets.right);
        plot_area_height += (plot_area_insets.top+plot_area_insets.bottom);
        if(plot_area != null)
            plot_area.setBounds(left, top, plot_area_width, plot_area_height);

        /*
         * Position axes
         */
        insets.right = width - insets.right;
        insets.bottom = height - insets.bottom;
        for(int i=container.getComponentCount()-1; i>=0; --i)
        {
            final Component component = container.getComponent(i);
            if(!component.isVisible())
                continue;
            if(!(component instanceof Axis))
                continue;

            final Dimension dim = component.getPreferredSize();
            switch(((Axis)component).getPosition())
            {
            case ChartComponent.LEFT:
                component.setLocation(insets.left, top+plot_area_insets.top);
                insets.left += dim.width;
                break;
            case ChartComponent.TOP:
                component.setLocation(left+plot_area_insets.left, insets.top);
                insets.top += dim.height;
                break;
            case ChartComponent.RIGHT:
                component.setLocation(insets.right - dim.width, top+plot_area_insets.top);
                insets.right -= dim.width;
                break;
            case ChartComponent.BOTTOM:
                component.setLocation(left+plot_area_insets.left, insets.bottom - dim.height);
                insets.bottom -= dim.height;
                break;
            }
        }
    }
}
