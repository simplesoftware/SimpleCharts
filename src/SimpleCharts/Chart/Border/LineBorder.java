package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class LineBorder implements Border
{
    private Color lineColor;
    private boolean roundedCorners;
    private int thickness;
    
    public LineBorder(Color color)
    {
        this(color, 1, false);
    }
    public LineBorder(Color color, int thickness)
    {
        this(color, thickness, false);
    }
    public LineBorder(Color color, int thickness, boolean roundedCorners)
    {
        this.lineColor = color;
        this.thickness = thickness;
        this.roundedCorners = roundedCorners;
    }
    public Color getLineColor()
    {
        return lineColor;
    }
    public void setLineColor(Color color)
    {
        this.lineColor = color;
    }
    public int getThickness()
    {
        return thickness;
    }
    public boolean getRoundedCorners()
    {
        return roundedCorners;
    }
    @Override
    public boolean isBorderOpaque()
    {
        return !roundedCorners;
    }
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(thickness, thickness, thickness, thickness);
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        final Color oldColor = g.getColor();
        g.setColor(lineColor);
        for(int i=0; i<thickness; ++i)
        {
            if(!roundedCorners)
                g.drawRect(x+i, y+i, width-i-i-1, height-i-i-1);
            else
                g.drawRoundRect(x+i, y+i, width-i-i-1, height-i-i-1, thickness, thickness);
        }
        g.setColor(oldColor);
    }
}
