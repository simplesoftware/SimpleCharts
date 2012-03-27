
package SimpleCharts.Chart;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author frank
 */
public class Utilities
{
    private final static DateFormat date_format = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
    public static Color transparent(Color c, int alpha)
    {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }
    public static String formatDate(double time)
    {
        return date_format.format(new Date((long)time));
    }
}
