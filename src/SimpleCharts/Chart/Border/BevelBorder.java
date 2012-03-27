package SimpleCharts.Chart.Border;

import SimpleCharts.Chart.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class BevelBorder implements Border
{
    public static final int RAISED = 0;
    public static final int LOWERED = 1;
    
    private int bevelType;
    private Color highlightOuter;
    private Color highlightInner;
    private Color shadowOuter;
    private Color shadowInner;
    
    public BevelBorder(int bevelType)
    {
        this.bevelType = bevelType;
    }
    public BevelBorder(int bevelType, Color highlight, Color shadow)
    {
        this(bevelType, highlight.brighter(), highlight,
                shadow, shadow.brighter());
    }
    public BevelBorder(int bevelType, Color highlightOuter, Color highlightInner,
            Color shadowOuter, Color shadowInner)
    {
        this(bevelType);
        this.highlightInner = highlightInner;
        this.highlightOuter = highlightOuter;
        this.shadowInner = shadowInner;
        this.shadowOuter = shadowOuter;
    }
    public Color getHighlightOuterColor(Component c)
    {
        if(highlightOuter != null)
            return highlightOuter;
        return getHighlightInnerColor(c).brighter();
    }
    public Color getHighlightInnerColor(Component c)
    {
        if(highlightInner != null)
            return highlightInner;
        return c.getBackground().brighter();
    }
    public Color getShadowInnerColor(Component c)
    {
        if(shadowInner != null)
            return shadowInner;
        return c.getBackground().darker();
    }
    public Color getShadowOuterColor(Component c)
    {
        if(shadowOuter != null)
            return shadowOuter;
        return getShadowInnerColor(c).darker();
    }
    public Color getHighlightInnerColor()
    {
        return highlightInner;
    }
    public Color getShadowInnerColor()
    {
        return shadowInner;
    }
    public Color getShadowOuterColor()
    {
        return shadowOuter;
    }
    public int getBevelType()
    {
        return bevelType;
    }
    
    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(2, 2, 2, 2);
    }
    @Override
    public boolean isBorderOpaque()
    {
        return 
            ((highlightOuter == null) || (highlightOuter.getAlpha() == 255))
            && ((highlightInner == null) || (highlightInner.getAlpha() == 255))
            && ((shadowInner == null) || (shadowInner.getAlpha() == 255))
            && ((shadowOuter == null) || (shadowOuter.getAlpha() == 255));
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        if(bevelType == RAISED)
            paintRaisedBevel(c, g, x, y, width, height);
        else
            paintLoweredBevel(c, g, x, y, width, height);
    }
    private void paintRaisedBevel(Component c, Graphics g, int x, int y, int width, int height)
    {
        paintBevel(g, x, y, width, height,
                getHighlightOuterColor(c), getHighlightInnerColor(c),
                getShadowInnerColor(c), getShadowOuterColor(c));
    }
    private void paintLoweredBevel(Component c, Graphics g, int x, int y, int width, int height)
    {
        paintBevel(g, x, y, width, height,
                getShadowInnerColor(c), getShadowOuterColor(c),
                getHighlightInnerColor(c), getHighlightOuterColor(c));

    }
    private void paintBevel(Graphics g, int x, int y, int width, int height,
            Color a, Color b, Color c, Color d)
    {
        final Color oldColor = g.getColor();
        g.translate(x, y);
        width = width - 1;
        height = height - 1;
        try
        {
            g.setColor(a);
            g.drawLine(0, 0, width, 0);
            g.drawLine(0, 1, 0, height);
            
            g.setColor(b);
            g.drawLine(1, 1, width -1, 1);
            g.drawLine(1, 2, 1, height-1);
            
            g.setColor(c);
            g.drawLine(2, height-1, width-1, height-1);
            g.drawLine(width-1, 2, width-1, height-2);
            
            g.setColor(d);
            g.drawLine(1, height, width, height);
            g.drawLine(width, 1, width, height-1);
        }
        finally
        {
            g.translate(-x, -y);
            g.setColor(oldColor);
        }
    }
}
