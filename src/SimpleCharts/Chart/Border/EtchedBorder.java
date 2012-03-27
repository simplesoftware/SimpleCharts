package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class EtchedBorder implements Border
{
    public static final int RAISED = 0;
    public static final int LOWERED = 1;
    
    private int etchType;
    private Color highlight;
    private Color shadow;
    
    public EtchedBorder()
    {
        this(LOWERED);
    }
    public EtchedBorder(int etchType)
    {
        this.etchType = etchType;
    }
    public EtchedBorder(Color highlight, Color shadow)
    {
        this(LOWERED, highlight, shadow);
    }
    public EtchedBorder(int etchType, Color highlight, Color shadow)
    {
        this(etchType);
        this.highlight = highlight;
        this.shadow = shadow;
    }
    public int getEtchType()
    {
        return etchType;
    }
    public Color getHighlightColor(Component c)
    {
        if(highlight != null)
            return highlight;
        else
            return c.getBackground().brighter();
    }
    public Color getHighlightColor()
    {
        return highlight;
    }
    public Color getShadowColor(Component c)
    {
        if(shadow != null)
            return shadow;
        else
            return c.getBackground().darker();
    }
    public Color getShadowColor()
    {
        return shadow;
    }
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(2, 2, 2, 2);
    }
    @Override
    public boolean isBorderOpaque()
    {
        return ((highlight == null) || (highlight.getAlpha() == 255))
                && ((shadow==null) || (shadow.getAlpha() == 255));
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        if(etchType == RAISED)
        {
            paintEtchedBorder(g, x, y, width, height,
                    getHighlightColor(c), getShadowColor(c));
        }
        else
        {
            paintEtchedBorder(g, x, y, width, height,
                    getShadowColor(c), getHighlightColor(c));
        }
    }
    private static void paintEtchedBorder(Graphics g, int x, int y, int width, int height, Color a, Color b)
    {
        final Color oldColor;
        oldColor = g.getColor();
        g.translate(x, y);
        width = width - 1;
        height = height - 1;
        try
        {
            g.setColor(a);
            g.drawRect(0, 0, width-1, height-1);
            
            g.setColor(b);
            g.drawLine(1, 1, width-2, 1);
            g.drawLine(1, 2, 1, height-2);
            g.drawLine(0, height, width, height);
            g.drawLine(width, 0, width, height-1);
        }
        finally
        {
            g.translate(-x, -y);
            g.setColor(oldColor);
        }
    }
}
