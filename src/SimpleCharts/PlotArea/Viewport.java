package SimpleCharts.PlotArea;

/**
 *
 * @author frank
 */
public class Viewport
{
    private double x, y, width, height;
    
    public Viewport(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }
}
