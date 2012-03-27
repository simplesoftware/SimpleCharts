package SimpleCharts.Chart;

import SimpleCharts.Chart.Border.BevelBorder;
import SimpleCharts.Chart.Border.CompoundBorder;
import SimpleCharts.Chart.Border.EmptyBorder;
import SimpleCharts.Chart.Layout.SequentialLayout;
import java.awt.Graphics;

/**
 *
 * @author frank
 */
public class Legend extends AbstractContainer implements ChartComponent
{
    private int position;
    private int orientation;
    
    public Legend()
    {
        this(ChartComponent.BOTTOM, ChartComponent.HORIZONTAL);
    }
    public Legend(int position)
    {
        this(position, ChartComponent.HORIZONTAL);
    }
    public Legend(int position, int orientation)
    {
        setBorder(new CompoundBorder(
                new BevelBorder(BevelBorder.RAISED),
                new EmptyBorder(5,5,5,5)));
        this.position = position;
        this.orientation = orientation;
        if(orientation == VERTICAL)
            setLayout(new SequentialLayout(
                    SequentialLayout.CENTER_ALIGN,
                    SequentialLayout.VERTICAL));
        else
            setLayout(new SequentialLayout(
                    SequentialLayout.CENTER_ALIGN,
                    SequentialLayout.HORIZONTAL));
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
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
