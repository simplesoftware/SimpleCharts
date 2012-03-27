package SimpleCharts.Chart;

/**
 *
 * @author frank
 */
public interface ChartComponent extends Component
{
    public static final int TOP=0, LEFT=1, BOTTOM=2, RIGHT=3, CENTER=4;
    public static int HORIZONTAL=0, VERTICAL=1;
    
    public int getPosition();
    public void setPosition(int position);
    public int getOrientation();
    public void setOrientation(int orientation);
}
