import DataModel.SimpleXYDataModel;
import DataModel.XYData;
import SimpleCharts.Axis.DateAxis;
import SimpleCharts.Chart.ChartComponent;
import SimpleCharts.Chart.ChartTitle;
import SimpleCharts.Chart.SimpleChart;
import SimpleCharts.ChartPanel;
import SimpleCharts.Plot.LinePlot;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author frank
 */
public class Test extends JFrame
{
    public Test()
    {
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ChartTitle title = new ChartTitle("Test");
        final LinePlot line_plot = new LinePlot("Price");
        line_plot.setPointColor(Color.green);
        line_plot.setDomainAxis(new DateAxis(ChartComponent.BOTTOM));
        line_plot.getRangeAxis().setMinimumTickSize(0.25);
        final SimpleXYDataModel data = getDataModel();
        line_plot.setDataModel(data);
        final SimpleChart chart = new SimpleChart(title, line_plot);
        final ChartPanel panel = new ChartPanel(chart);
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.setPreferredSize(new Dimension(1000,700));
        setContentPane(panel);
        pack();
        setVisible(true);
    }
    private SimpleXYDataModel getDataModel()
    {
        final Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        final List<MarketData> data = new ArrayList<MarketData>();
        data.add(new MarketData(cal.getTimeInMillis(), 1000.0));
        cal.add(Calendar.HOUR, 1);
        data.add(new MarketData(cal.getTimeInMillis(), 1000.25));
        cal.add(Calendar.MINUTE, 20);
        data.add(new MarketData(cal.getTimeInMillis(), 1001.5));
        cal.add(Calendar.MINUTE, 45);
        data.add(new MarketData(cal.getTimeInMillis(), 999.75));
        return new SimpleXYDataModel(data);
    }
    private class MarketData implements XYData
    {
        private double time, price;
        
        MarketData(double time, double price)
        {
            this.time = time;
            this.price = price;
        }
        @Override
        public double getX()
        {
            return time;
        }

        @Override
        public double getY()
        {
            return price;
        }

    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                final Test app = new Test();
            }
        });
    }
}
