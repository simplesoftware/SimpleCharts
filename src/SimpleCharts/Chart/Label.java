package SimpleCharts.Chart;

import java.awt.*;
import java.awt.font.LineMetrics;
/**
 *
 * @author frank
 */
public class Label extends AbstractComponent
{
    private String text;
    
    public Label(String text)
    {
        this.text = text;
    }

    @Override
    public Dimension getPreferredSize()
    {
        if(isPreferredSizeSet())
            return super.getPreferredSize();
        final Graphics g = getGraphics();
        final FontMetrics fm = g.getFontMetrics();
        final Dimension d = fm.getStringBounds(text, g).getBounds().getSize();
        final Insets insets = getInsets();
        return new Dimension(d.width+insets.left+insets.right,
                d.height+insets.top+insets.bottom);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g = g.create();
        if(getFont() != g.getFont())
            g.setFont(getFont());
        final Insets insets = getInsets();
        final FontMetrics fm = g.getFontMetrics();
        final LineMetrics lm = fm.getLineMetrics(text, g);
        g.setColor(Color.red/*getForeground()*/);
        g.drawString(text, insets.left, (int)(lm.getLeading()+lm.getAscent()+insets.top));
        g.dispose();
    }
    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }
}
