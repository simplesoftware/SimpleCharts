package SimpleCharts.Chart.Layout;

import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Container;
import java.awt.Dimension;

/**
 *
 * @author frank
 */
public interface LayoutManager
{
    public void layoutContainer(Container c);
    public void addLayoutComponent(String name, Component c);
    public void removeLayoutComponent(Component c);
    public Dimension preferredLayoutSize(Container c);
    public Dimension minimumLayoutSize(Container container);
}
