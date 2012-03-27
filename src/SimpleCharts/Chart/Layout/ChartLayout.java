package SimpleCharts.Chart.Layout;

import SimpleCharts.Chart.ChartComponent;
import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Container;
import SimpleCharts.Plot.Plot;
import java.awt.Dimension;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class ChartLayout implements LayoutManager
{
    public ChartLayout()
    {
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
            if(component instanceof Plot)
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
        System.out.println("Laying out chart width="+width+", height="+height+", ncomps="+container.getComponentCount());
        
        /**
         * Find plot area boundaries
         */
        int left = insets.left;
        int top = insets.top;
        int right = width - insets.right - 1;
        int bottom = height - insets.bottom - 1;
        int plot_area_width;
        int plot_area_height;
        Component plot = null;

        do
        {
            plot_area_width = right - left + 1;
            plot_area_height = bottom - top + 1;
            left = insets.left;
            top = insets.top;
            right = width - insets.right - 1;
            bottom = height - insets.bottom - 1;
            for(int i=0; i<container.getComponentCount(); ++i)
            {
                final ChartComponent c = (ChartComponent)container.getComponent(i);
                if(c instanceof Plot)
                {
                    plot = c;
                    continue;
                }
                if(!c.isVisible())
                    continue;
                final Dimension d = c.getPreferredSize();
                switch(c.getPosition())
                {
                    case ChartComponent.LEFT:
                        left += d.width;
                        break;
                    case ChartComponent.RIGHT:
                        right -= d.width;
                        break;
                    case ChartComponent.TOP:
                        top += d.height;
                        break;
                    case ChartComponent.BOTTOM:
                        bottom -= d.height;
                        break;
                }
            }
        } while(plot_area_width > right - left + 1
                || plot_area_height > bottom - top + 1);

        /*
         * Layout plot area
         */
        if(plot != null)
            plot.setBounds(left, top, right-left+1, bottom-top+1);
        
        /*
         * Layout other components
         */
        insets.right = width - insets.right;
        insets.bottom = height - insets.bottom;

        for(int i=container.getComponentCount()-1; i>=0; --i)
        {
            final ChartComponent component = (ChartComponent)container.getComponent(i);
            if(component instanceof Plot)
                continue;
            if(!component.isVisible())
                continue;
            final Dimension dim = component.getPreferredSize();
            final int x, y, w, h;
            switch(component.getPosition())
            {
                case ChartComponent.LEFT:
                    x = insets.left;
                    y = top + (bottom-top-dim.height+1)/2;
                    w = dim.width;
                    h = dim.height;
                    insets.left += dim.width;
                    break;
                case ChartComponent.TOP:
                    x = left + (right-left-dim.width+1)/2;
                    y = insets.top;
                    w = dim.width;
                    h = dim.height;
                    insets.top += dim.height;
                    break;
                case ChartComponent.RIGHT:
                    x = insets.right-dim.width;
                    y = top + (bottom-top-dim.height+1)/2;
                    w = dim.width;
                    h = dim.height;
                    insets.right -= dim.width;
                    break;
                case ChartComponent.BOTTOM:
                    x = left + (right-left-dim.width+1)/2;
                    y = insets.bottom-dim.height;
                    w = Math.min(right-left+1,dim.width);
                    h = dim.height;
                    insets.bottom -= h;
                    break;
                default:
                    continue;
            }
            component.setBounds(x, y, w, h);
        }
    }
    @Override
    public String toString()
    {
        return getClass().getName();
    }
}
