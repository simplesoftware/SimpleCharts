package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class EmptyBorder implements Border
{
    private int left, right, top, bottom;
    
    public EmptyBorder(int top, int left, int bottom, int right)
    {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }
    public EmptyBorder(Insets insets)
    {
        this.bottom = insets.bottom;
        this.top = insets.top;
        this.left = insets.left;
        this.right = insets.right;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
    }
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(top, left, bottom, right);
    }
    @Override
    public boolean isBorderOpaque()
    {
        return false;
    }
}
