package SimpleCharts.Chart.Layout;

import SimpleCharts.Chart.Component;
import SimpleCharts.Chart.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 *
 * @author frank
 */
public class SequentialLayout implements LayoutManager
{
    /**
     * Alignment constants
     */
    public static final int TOP_LEFT_ALIGN = 0;
    public static final int CENTER_ALIGN = 1;
    public static final int BOTTOM_RIGHT_ALIGN = 2;
    
    /**
     * Orientation constants
     */
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int alignment;
    private int hgap, vgap;
    private int orientation;
    
    public SequentialLayout()
    {
        this(CENTER_ALIGN, HORIZONTAL, 2, 2);
    }
    public SequentialLayout(int alignment, int orientation)
    {
        this(alignment, orientation, 2, 2);
    }
    public SequentialLayout(int alignment, int orientation, int hgap, int vgap)
    {
        this.alignment = alignment;
        this.orientation = orientation;
        this.hgap = hgap;
        this.vgap = vgap;
    }
    public int getAlignment()
    {
        return alignment;
    }
    public int getOrientation()
    {
        return orientation;
    }
    public int getHgap()
    {
        return hgap;
    }
    public int getVgap()
    {
        return vgap;
    }
    public void setHgap(int gap)
    {
        this.hgap = gap;
    }
    public void setVgap(int gap)
    {
        this.vgap = gap;
    }
    @Override
    public void layoutContainer(Container container)
    {
        final int ncomps = container.getComponentCount();
        final Insets insets = container.getInsets();

        if(orientation == HORIZONTAL)
        {
            if(alignment == TOP_LEFT_ALIGN)
            {
                int x = insets.left + hgap;
                int y = insets.top + vgap;
                for(int i=0; i<ncomps; ++i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    c.setBounds(x, y, d.width, d.height);
                    x += (d.width + hgap);
                }
            }
            else if(alignment == BOTTOM_RIGHT_ALIGN)
            {
                int x = insets.right - hgap;
                int y = insets.top + vgap;
                for(int i=ncomps-1; i>=0; --i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    x -= (d.width + hgap);
                    c.setBounds(x, y, d.width, d.height);
                }
            }
            else // alignment == CENTER_ALIGN
            {
                final Dimension tot_dim = preferredLayoutSize(container);
                final int width = container.getWidth();
                int x = Math.max((width-tot_dim.width)/2, 0) + insets.left + hgap;
                int y = insets.top + vgap;
                for(int i=0; i<ncomps; ++i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    c.setBounds(x, y, d.width, d.height);
                    x += (d.width + hgap);
                }
            }
        }
        else // VERTICAL
        {
            if(alignment == TOP_LEFT_ALIGN)
            {
                int x = insets.left + hgap;
                int y = insets.top + vgap;
                for(int i=0; i<ncomps; ++i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    c.setBounds(x, y, d.width, d.height);
                    y += (d.height + vgap);
                }
            }
            else if(alignment == BOTTOM_RIGHT_ALIGN)
            {
                int x = insets.left - hgap;
                int y = insets.bottom + vgap;
                for(int i=ncomps-1; i>=0; --i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    y -= (d.height + vgap);
                    c.setBounds(x, y, d.width, d.height);
                }
            }
            else // alignment == CENTER_ALIGN
            {
                final Dimension tot_dim = preferredLayoutSize(container);
                final int height = container.getHeight();
                int x = insets.left + hgap;
                int y = (height-tot_dim.height)/2 + insets.top + vgap;
                for(int i=0; i<ncomps; ++i)
                {
                    final Component c = container.getComponent(i);
                    if(!c.isVisible())
                        continue;
                    final Dimension d = c.getPreferredSize();
                    c.setBounds(x, y, d.width, d.height);
                    y += (d.height + vgap);
                }
            }
        }
    }
    @Override
    public void addLayoutComponent(String name, Component c)
    {
    }
    @Override
    public void removeLayoutComponent(Component c)
    {
    }
    @Override
    public Dimension preferredLayoutSize(Container container)
    {
        final int ncomps = container.getComponentCount();
        final Insets insets = container.getInsets();
        final Dimension dim = new Dimension(0,0);

        int gap = 0;
        if(orientation == HORIZONTAL)
        {
            for(int i=0; i<ncomps; ++i)
            {
                final Component c = container.getComponent(i);
                if(!c.isVisible())
                    continue;
                final Dimension d = c.getPreferredSize();
                dim.height = Math.max(dim.height, d.height);
                dim.width += (d.width + gap);
                gap = hgap;
            }
        }
        else
        {
            for(int i=0; i<ncomps; ++i)
            {
                final Component c = container.getComponent(i);
                if(!c.isVisible())
                    continue;
                final Dimension d = c.getPreferredSize();
                dim.width = Math.max(dim.width, d.width);
                dim.height += (d.height + gap);
                gap = vgap;
            }
        }
        dim.height += (insets.top + insets.bottom + vgap*2);
        dim.width += (insets.left + insets.right + hgap*2);
        return dim;
    }
    @Override
    public Dimension minimumLayoutSize(Container container)
    {
        final int ncomps = container.getComponentCount();
        final Insets insets = container.getInsets();
        final Dimension dim = new Dimension(0,0);

        int gap = 0;
        if(orientation == HORIZONTAL)
        {
            for(int i=0; i<ncomps; ++i)
            {
                final Component c = container.getComponent(i);
                if(!c.isVisible())
                    continue;
                final Dimension d = c.getMinimumSize();
                dim.height = Math.max(dim.height, d.height);
                dim.width += (d.width + gap);
                gap = hgap;
            }
        }
        else
        {
            for(int i=0; i<ncomps; ++i)
            {
                final Component c = container.getComponent(i);
                if(!c.isVisible())
                    continue;
                final Dimension d = c.getMinimumSize();
                dim.width = Math.max(dim.width, d.width);
                dim.height += (d.height + gap);
                gap = vgap;
            }
        }
        dim.height += (insets.top + insets.bottom + vgap*2);
        dim.width += (insets.left + insets.right + hgap*2);
        return dim;
    }
}
