package SimpleCharts.Chart;

import SimpleCharts.Chart.Layout.LayoutManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
abstract public class AbstractContainer extends AbstractComponent implements Container
{
    private final List<Component> components;
    private LayoutManager layoutMgr;
    
    protected AbstractContainer()
    {
        super(0.5f, 0.5f);
        components = new ArrayList<Component>();
    }
    
    @Override
    public Component add(Component c)
    {
        checkAddToSelf(c);
        if(c.getParent() != null)
            c.getParent().remove(c);
        components.add(c);
        c.setParent(this);
        return c;
    }

    @Override
    public Component add(Component c, int index)
    {
        checkAddToSelf(c);
        if(c.getParent() != null)
            c.getParent().remove(c);
        components.add(index, c);
        c.setParent(this);
        return c;
    }
    @Override
    public Component add(Component c, String constraint)
    {
        checkAddToSelf(c);
        if(c.getParent() != null)
            c.getParent().remove(c);
        components.add(c);
        c.setParent(this);
        if(layoutMgr != null)
            layoutMgr.addLayoutComponent(constraint, c);
        return c;
    }
    private void checkAddToSelf(Component c)
    {
        if(c instanceof Container)
            for(Container cn = this; cn != null; cn = cn.getParent())
                if(cn == c)
                    throw new IllegalArgumentException("Adding container's parent to itself");
    }
    @Override
    public void doLayout()
    {
        if(isValid() == false && layoutMgr != null)
        {
            layoutMgr.layoutContainer(this);
            validate();
        }
        for(int i=0; i<getComponentCount(); ++i)
        {
            final Component c = getComponent(i);
            if(c instanceof Container)
                ((Container)c).doLayout();
        }
    }
    @Override
    public void invalidate()
    {
        super.invalidate();
        for(int i=0; i<components.size(); ++i)
            components.get(i).invalidate();
    }
    @Override
    public Component findComponentAt(int x, int y)
    {
        if(contains(x, y)==false || isVisible()==false)
            return null;
        
        for(int i=components.size()-1; i>=0; --i)
        {
            final Component comp = components.get(i);
            if(comp.isVisible() && comp.contains(x, y))
            {
                if(comp instanceof Container)
                    return ((Container)comp).findComponentAt(x, y);
                return comp;
            }
        }
        return this;
    }

    @Override
    public Component findComponentAt(Point p)
    {
        return findComponentAt(p.x, p.y);
    }

    @Override
    public Component getComponent(int n)
    {
        return components.get(n);
    }

    @Override
    public Component getComponentAt(int x, int y)
    {
        if(!contains(x, y))
            return null;
        
        for(int i=components.size()-1; i>=0; --i)
        {
            final Component comp = components.get(i);
            if(comp.contains(x, y))
            {
                if(comp instanceof Container)
                    return ((Container)comp).getComponentAt(x, y);
                return comp;
            }
        }
        return this;
    }
    @Override
    public Dimension getPreferredSize()
    {
        if(isPreferredSizeSet())
            return super.getPreferredSize();
        if(layoutMgr != null)
            return layoutMgr.preferredLayoutSize(this);
        return null;
    }
    @Override
    public Component getComponentAt(Point p)
    {
        return getComponentAt(p.x, p.y);
    }

    @Override
    public int getComponentCount()
    {
        return components.size();
    }

    @Override
    public LayoutManager getLayout()
    {
        return layoutMgr;
    }

    @Override
    public boolean isAncestorOf(Component c)
    {
        Container p = c.getParent();
        while(p != null)
        {
            if(p == this)
                return true;
            p = p.getParent();
        }
        return false;
    }

    @Override
    public void remove(int index)
    {
        final Component c = components.get(index);
        if(layoutMgr != null)
            layoutMgr.removeLayoutComponent(c);
        c.setParent(null);
        components.remove(c);
    }

    @Override
    public void remove(Component c)
    {
        if(c.getParent() == this)
        {
            final int index = components.indexOf(c);
            if(index >= 0)
            {
                remove(index);
            }
        }
    }

    @Override
    public void removeAll()
    {
        while(!components.isEmpty())
            remove(components.get(0));
    }

    @Override
    public void setLayout(LayoutManager mgr)
    {
        layoutMgr = mgr;
    }

    @Override
    public void paint(Graphics g)
    {
        if(getWidth() <= 0 || getHeight() <= 0)
            return;
        final Graphics g2 = g.create(getX(), getY(), getWidth(), getHeight());
//        System.out.println("cropping container to x="+getX()+", y="+getY()+", width="+getWidth()+", height="+getHeight());
        try
        {
            paintComponent(g2);
            paintBorder(g2);
            paintChildren(g2);
        }
        finally
        {
            g2.dispose();
        }
    }
    @Override
    public void paintChildren(Graphics g)
    {
        final int ncomps = getComponentCount();
        for(int i=0; i<ncomps; ++i)
        {
            final Component c = getComponent(i);
            c.paint(g);
        }
    }
}
