package SimpleCharts.Axis;

/**
 * Class representing a tick mark on an <code>Axis</code>
 * @author frank
 */
public class ValueTick
{
    private final double value;
    private final String label;
   
    /**
     * Construct a <code>ValueTick</code> with the given <code>label</code> and <code>value</code>
     * @param label the label for the tick
     * @param value the value for the tick
     */
    public ValueTick(String label, double value)
    {
        this.value = value;
        this.label = label;
    }
    /**
     * Get this <code>ValueTick</code>'s value
     * @return 
     */
    public double getValue()
    {
        return value;
    }
    /**
     * Get this <code>ValueTick</code>'s label
     * @return 
     */
    public String getLabel()
    {
        return label;
    }
}
