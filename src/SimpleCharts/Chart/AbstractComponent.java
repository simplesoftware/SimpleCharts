package SimpleCharts.Chart;

import SimpleCharts.Chart.Border.Border;
import java.awt.*;
import javax.swing.JPopupMenu;
import javax.swing.JToolTip;

/**
 *
 * @author frank
 */
abstract public class AbstractComponent implements Component
{
    private Container parent;
    private int x, y;
    private int width, height;
    private float alignmentX, alignmentY;
    private boolean is_opaque, is_enabled, is_visible, needsLayout;
    private Font font;
    private JToolTip tool_tip;
    private Dimension preferred_size;
    private boolean is_preferredsize_set;
    private Border border;
    private Color foreground, background;

    protected AbstractComponent()
    {
        this(0f, 0f);
    }
    protected AbstractComponent(float alignmentX, float alignmentY)
    {
        this.parent = null;
        this.alignmentX = alignmentX;
        this.alignmentY = alignmentY;
        this.x = this.y = 0;
        this.is_opaque = true;
        this.is_enabled = true;
        this.is_visible = true;
        this.is_preferredsize_set = false;
        this.needsLayout = true;
        this.font = null;
        this.tool_tip = null;
        this.preferred_size = null;
        this.border = null;
        this.foreground = null;
        this.background = null;
    }
    @Override
    public Container getParent()
    {
        return parent;
    }
    @Override
    public void setParent(Container parent)
    {
        this.parent = parent;
    }

    @Override
    public float getAlignmentX()
    {
        return alignmentX;
    }
    @Override
    public float getAlignmentY()
    {
        return alignmentY;
    }
    @Override
    public Dimension getPreferredSize()
    {
        if(preferred_size != null)
            return preferred_size;
        return null;
    }
    @Override
    public Dimension getMinimumSize()
    {
        return null;
    }
    @Override
    public Dimension getMaximumSize()
    {
        return null;
    }
    @Override
    public int getWidth()
    {
        return width;
    }
    @Override
    public int getHeight()
    {
        return height;
    }
    @Override
    public Rectangle getBounds(Rectangle r)
    {
        if(r == null)
            r = new Rectangle(x, y, width, height);
        else
        {
            r.x = x;
            r.y = y;
            r.width = width;
            r.height = height;
        }
        return r;
    }
    @Override
    public Rectangle getBounds()
    {
        return getBounds(null);
    }
    @Override
    public Point getLocation(Point p)    
    {
        return new Point(x, y);
    }
    @Override
    public int getX()
    {
        return x;
    }
    @Override
    public int getY()
    {
        return y;
    }
    @Override
    public Border getBorder()
    {
        return border;
    }
    @Override
    public Color getForeground()
    {
        if(foreground != null || parent == null)
            return foreground;
        return parent.getForeground();
    }
    @Override
    public Color getBackground()
    {
        if(background != null || parent == null)
            return background;
        return parent.getBackground();
    }
    @Override
    public void setForeground(Color color)
    {
        final Color fg = getForeground();
        this.foreground = color;
        if(fg != color)
            repaint();
    }
    @Override
    public void setBackground(Color color)
    {
        final Color bg = getBackground();
        this.background = color;
        if(bg != color)
            repaint();
    }
    @Override
    public Insets getInsets()
    {
        if(border != null)
            return border.getBorderInsets(this);
        return new Insets(0, 0, 0, 0);
    }
    @Override
    public boolean isOpaque()
    {
        return is_opaque;
    }
    @Override
    public boolean isEnabled()
    {
        return is_enabled;
    }
    @Override
    public boolean isVisible()
    {
        return is_visible;
    }
    @Override
    public JPopupMenu getComponentPopupMenu()
    {
        return parent.getComponentPopupMenu();
    }
    @Override
    public Graphics getGraphics()
    {
        return parent.getGraphics();
    }
    @Override
    public FontMetrics getFontMetrics(Font font)
    {
        return getGraphics().getFontMetrics();
    }
    @Override
    public boolean contains(int x, int y)
    {
        return getBounds(null).contains(x, y);
    }
    @Override
    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    @Override
    public void setBounds(int x, int y, int width, int height)
    {
        setLocation(x, y);
        setSize(width, height);
    }
    @Override
    public void setBorder(Border border)
    {
        this.border = border;
    }
    @Override
    public Cursor getCursor()
    {
        return Cursor.getDefaultCursor();
    }
    @Override
    public Font getFont()
    {
        if(font == null)
            return parent.getFont();
        else
            return font;
    }
    @Override
    public void setFont(Font font)
    {
        this.font = font;
    }
    @Override
    public void repaint(int x, int y, int width, int height)
    {
        System.out.println("restricted repaint called");
        if(parent != null)
            parent.repaint(x, y, width, height);
    }
    @Override
    public void repaint()
    {
        if(parent != null)
            parent.repaint();
    }
    @Override
    public void revalidate()
    {
        if(needsLayout == false && parent != null)
            parent.revalidate();
        needsLayout = true;
    }
    @Override
    public boolean isValid()
    {
        return !needsLayout;
    }
    @Override
    public void validate()
    {
        needsLayout = false;
    }
    @Override
    public void invalidate()
    {
        needsLayout = true;
    }
    @Override
    public JToolTip createToolTip()
    {
        if(tool_tip == null)
            tool_tip = new JToolTip();
        return tool_tip;
    }
    @Override
    public void setVisible(boolean flag)
    {
        if(flag != isVisible())
        {
            if(parent != null)
            {
                final Rectangle r = getBounds();
                parent.repaint(r.x, r.y, r.width, r.height);
            }
            revalidate();
        }
        this.is_visible = flag;
    }
    @Override
    public void setEnabled(boolean flag)
    {
        final boolean oldFlag = isEnabled();
        this.is_enabled = flag;
        if(oldFlag != flag)
            repaint();
    }
    @Override
    public void scrollRectToVisible(Rectangle rectangle)
    {
        if(parent != null)
            parent.scrollRectToVisible(rectangle);
    }
    @Override
    public void setAlignmentX(float alignmentX)
    {
        this.alignmentX = alignmentX;
    }
    @Override
    public void setAlignmentY(float alignmentY)
    {
        this.alignmentY = alignmentY;
    }
    @Override
    public void setComponentPopupMenu(JPopupMenu menu)
    {
    }
    @Override
    public void setMinimumSize(Dimension minimumSize)
    {
    }
    @Override
    public void setMaximumSize(Dimension maximumSize)
    {
    }
    @Override
    public void setPreferredSize(Dimension preferredSize)
    {
        this.preferred_size = preferredSize;
        is_preferredsize_set = true;
    }
    @Override
    public boolean isPreferredSizeSet()
    {
        return is_preferredsize_set;
    }
    @Override
    public void setOpaque(boolean flag)
    {
        this.is_opaque = flag;
        repaint();
    }
    @Override
    public void paint(Graphics g)
    {
        if(getWidth() <= 0 || getHeight() <= 0)
            return;
        final Graphics g2 = g.create(getX(), getY(), getWidth(), getHeight());
//        System.out.println("Cropping component to x="+getX()+", y="+getY()+", width="+getWidth()+", height="+getHeight());
        try
        {
            paintComponent(g2);
            paintBorder(g2);
        }
        finally
        {
            g2.dispose();
        }
    }
    protected void paintBorder(Graphics g)
    {
        final Border b = getBorder();
        if(b != null)
            b.paintBorder(this, g, 0, 0, getWidth(), getHeight());
    }
    protected void paintComponent(Graphics g)
    {
    }
}
