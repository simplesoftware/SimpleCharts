package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public interface Border
{
    public Insets getBorderInsets(Component c);
    public boolean isBorderOpaque();
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height);
}
