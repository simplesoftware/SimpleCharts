package SimpleCharts.Chart;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.font.LineMetrics;

/**
 *
 */
public class ChartTitle extends AbstractComponent implements ChartComponent
{
    private String title;
    private int vgap, hgap;
    private int position;
    private int orientation;
    
    public ChartTitle(String title)
    {
        this(title, TOP, HORIZONTAL, 2, 2);
    }
    public ChartTitle(String title, int position)
    {
        this(title, position, HORIZONTAL, 2, 2);
    }
    public ChartTitle(String title, int position, int orientation)
    {
        this(title, position, orientation, 2, 2);
    }
    public ChartTitle(String title, int position, int orientation, int vgap, int hgap)
    {
        super(0.5f, 0.5f);
        this.title = title;
        this.vgap = vgap;
        this.hgap = hgap;
    }
    @Override
    public Dimension getPreferredSize()
    {
        if(isPreferredSizeSet())
            return super.getPreferredSize();
        final Graphics g = getGraphics();
        final FontMetrics fm = g.getFontMetrics();
        final Dimension d = fm.getStringBounds(title, g).getBounds().getSize();
        final Insets insets = getInsets();
        return new Dimension(d.width+insets.left+insets.right+hgap*2,
                d.height+insets.top+insets.bottom+vgap*2);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g = g.create();
        if(getFont() != g.getFont())
            g.setFont(getFont());
        final Insets insets = getInsets();
        final FontMetrics fm = g.getFontMetrics();
        final LineMetrics lm = fm.getLineMetrics(title, g);
        g.setColor(getForeground());
        g.drawString(title, insets.left+hgap, (int)(lm.getLeading()+lm.getAscent()+insets.top+vgap));
        g.dispose();
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
}
