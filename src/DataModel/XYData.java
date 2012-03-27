package DataModel;

/**
 * A data point within an (x,y) coordinate plane used with two dimensional plots.
 * 
 */
public interface XYData
{
    /**
     * Returns the X coordinate of the data item
     * @return the x coordinate
     */
    public double getX();
    
    /**
     * Returns the Y coordinate of the data item
     * @return the y coordinate
     */
    public double getY();
}
