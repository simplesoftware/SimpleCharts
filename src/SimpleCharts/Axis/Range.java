package SimpleCharts.Axis;

/**
 * Represents an immutable range of values.
 */
public class Range
{
    private final double lower, upper;
    
    /**
     * Creates a new range.
     *
     * @param lower  the l bound (must be <= u bound).
     * @param upper  the u bound (must be >= l bound).
     */
    public Range(double lower, double upper)
    {
        if(lower > upper)
            throw new IllegalArgumentException("Range must have lower <= upper");
        this.lower = lower;
        this.upper = upper;
    }
    /**
     * Returns the lower bound for the range.
     *
     * @return The lower bound.
     */
    public double getLowerBound()
    {
        return lower;
    }
    /**
     * Returns the upper bound for the range.
     *
     * @return The upper bound.
     */
    public double getUpperBound()
    {
        return upper;
    }
    /**
     * Get the extent of this range, defined as <code>getUpper()-getLower()</code>.
     * @return the extent of this range.
     */
    public double getExtent()
    {
        return getUpperBound() - getLowerBound();
    }
    /**
     * Returns the center value for the range.
     *
     * @return The center value.
     */
    public double getCenterValue()
    {
        return this.lower / 2.0 + this.upper / 2.0;
    }
    /**
     * Returns <code>true</code> if the range contains the specified value and
     * <code>false</code> otherwise.
     *
     * @param value  the value to lookup.
     *
     * @return <code>true</code> if the range contains the specified value.
     */
    public boolean contains(double value)
    {
        return (value >= this.lower && value <= this.upper);
    }
    /**
     * Returns a range that includes all the values in this range
     * AND the specified <code>value</code>.
     *
     * @param value  the value that must be included.
     *
     * @return A range.
     */
    public Range expandToInclude(double value)
    {
        if (value < getLowerBound())
            return new Range(value, getUpperBound());
        else if (value > getUpperBound())
            return new Range(getLowerBound(), value);
        else
            return this;
    }
    /**
     * Creates a new range by adding margins to this range.
     *
     * @param lowerMargin  the lower margin (expressed as a percentage of the
     *                     range extent).
     * @param upperMargin  the upper margin (expressed as a percentage of the
     *                     range extent).
     *
     * @return The expanded range.
     */
    public Range addMargins(double lowerMargin, double upperMargin)
    {
        double extent = getExtent();
        double l = getLowerBound() - extent * lowerMargin;
        double u = getUpperBound() + extent * upperMargin;
        if (l > u)
        {
            l = l / 2.0 + u / 2.0;
            u = l;
        }
        return new Range(l, u);
    }
    /**
     * Shifts range by the specified amount.
     *
     * @param delta  the shift amount.
     * @param allowZeroCrossing  a flag that determines whether or not the
     *                           bounds of the range are allowed to cross
     *                           zero after adjustment.
     *
     * @return A new range.
     */
    public Range shift(double delta, boolean allowZeroCrossing)
    {
        if (allowZeroCrossing)
            return new Range(getLowerBound() + delta,
                    getUpperBound() + delta);
        else
            return new Range(shiftWithNoZeroCrossing(getLowerBound(),
                    delta), shiftWithNoZeroCrossing(getUpperBound(),
                    delta));
    }
    /**
     * Returns the given <code>value</code> adjusted by <code>delta</code> but
     * with a check to prevent the result from crossing <code>0.0</code>.
     *
     * @param value  the value.
     * @param delta  the adjustment.
     *
     * @return The adjusted value.
     */
    private static double shiftWithNoZeroCrossing(double value, double delta)
    {
        if (value > 0.0) {
            return Math.max(value + delta, 0.0);
        }
        else if (value < 0.0) {
            return Math.min(value + delta, 0.0);
        }
        else {
            return value + delta;
        }
    }
    /**
     * Scales the range by the specified factor.
     *
     * @param factor the scaling factor (must be non-negative).
     *
     * @return A new range.
     */
    public Range scale(double factor)
    {
        if (factor < 0)
            throw new IllegalArgumentException("Negative 'factor' argument.");
        return new Range(getLowerBound() * factor, getUpperBound() * factor);
    }
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Range))
            return false;
        Range range = (Range) obj;
        if (!(this.lower == range.lower))
            return false;
        if (!(this.upper == range.upper))
            return false;
        return true;
    }
    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.lower);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.upper);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a string representation of this Range.
     *
     * @return A String "Range[lower,upper]" where lower=lower range and
     *         upper=upper range.
     */
    @Override
    public String toString()
    {
        return ("Range[" + this.lower + "," + this.upper + "]");
    }
}
