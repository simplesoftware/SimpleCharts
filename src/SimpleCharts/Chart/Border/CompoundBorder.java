package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class CompoundBorder implements Border
{
    private Border outside, inside;
    
    public CompoundBorder()
    {
        outside = inside = null;
    }
    public CompoundBorder(Border outside, Border inside)
    {
        this.inside = inside;
        this.outside = outside;
    }
    @Override
    public boolean isBorderOpaque()
    {
        return (outside == null || outside.isBorderOpaque())
                && (inside == null || inside.isBorderOpaque());
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        outside.paintBorder(c, g, x, y, width, height);
        final Insets outside_insets = outside.getBorderInsets(c);
        x += outside_insets.left;
        y += outside_insets.top;
        width -= (outside_insets.right + outside_insets.left);
        height -= (outside_insets.top + outside_insets.bottom);
        inside.paintBorder(c, g, x, y, width, height);
    }
    @Override
    public Insets getBorderInsets(Component c)
    {
        final Insets outside_insets = outside.getBorderInsets(c);
        final Insets inside_insets = inside.getBorderInsets(c);
        outside_insets.top += inside_insets.top;
        outside_insets.left += inside_insets.left;
        outside_insets.right += inside_insets.right;
        outside_insets.bottom += inside_insets.bottom;
        return outside_insets;
    }
}
