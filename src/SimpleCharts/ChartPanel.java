package SimpleCharts;

import SimpleCharts.Chart.Chart;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * A Swing GUI component that can be used to hold a (@link Chart) object.
 * The <code>ChartPanel</code> registers with the chart to receive notifications of changes to the chart
 * and registers with the panel to receive mouse and menu events.
 */
public class ChartPanel extends JPanel implements Chart.ChangeListener
{
    private Chart chart;
    private JPopupMenu popup = null;
    private List<ChartPanelListener> listeners;
    private UserEventHandler user_event_handler;
    private int last_width, last_height;
    
    /**
     * Constructs a panel that can contain a chart.
     */
    public ChartPanel()
    {
        this(null);
    }    
    /**
     * Constructs a panel that displays the specified chart.
     *
     * @param chart  the chart.
     */
    public ChartPanel(Chart chart)
    {
        assert chart != null;
        this.chart = chart;
        chart.setChartPanel(this);
        this.listeners = new ArrayList<ChartPanelListener>();
        this.user_event_handler = new DefaultUserEventHandler();

        addMouseListener(user_event_handler);
        addMouseMotionListener(user_event_handler);
        setOpaque(true);
        setDoubleBuffered(true);
    }
    /**
     * Returns the chart contained in the panel.
     *
     * @return The chart (possibly <code>null</code>).
     */
    public Chart getChart()
    {
        return chart;
    }
    /**
     * Sets the chart that is displayed in the panel.
     * @param chart  the chart (<code>null</code> permitted).
     */
    public void setChart(Chart chart)
    {
        if(this.chart != null)
            this.chart.removeChangeListener(this);
        
        this.chart = chart;
        if(chart != null)
        {
            chart.addChangeListener(this);
            chart.setChartPanel(this);
        }
        repaint();
    }
    /**
     * Returns the pop-up menu.
     *
     * @return The pop-up menu.
     */
    public JPopupMenu getPopupMenu()
    {
        return popup;
    }
    /**
     * Sets the pop-up menu for the panel.
     *
     * @param popup  the pop-up menu (<code>null</code> permitted).
     */
    public void setPopupMenu(JPopupMenu menu)
    {
        this.popup = menu;
    }
    /**
     * Registers the given listener with this <code>ChartPanel</code>.
     * 
     * @param listener the <code>ChartPanelListener</code> to receive <code>ChartPanel</code> events.
     */
    public void addListener(ChartPanelListener listener)
    {
        listeners.add(listener);
    }
    /**
     * Unregisters the given listener with this chart.
     * 
     * @param listener 
     */
    public  void removeListener(ChartPanelListener listener)
    {
        listeners.remove(listener);
    }
    /**
     * Get the user event handler for this chart.
     * 
     * @return the (@link UserEventHandler) for this chart.
     */
    public UserEventHandler getUserEventHandler()
    {
        return user_event_handler;
    }
    /**
     * Set the user event handler for this chart.
     * @param handler  a (@link UserEventHandler).
     */
    public void setUserEventHandler(UserEventHandler handler)
    {
        user_event_handler = handler;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(chart == null)
            return;
        
        final Insets insets = getInsets();
        final int width = getWidth() - insets.left - insets.right;
        final int height = getHeight() - insets.top - insets.bottom;
        final Graphics g2 = g.create(insets.left, insets.top, width, height);
//        System.out.println("width="+getWidth()+", height="+getHeight());
//        System.out.println("cropping chartpanel to x="+insets.left+", y="+insets.top+", width="+width+", height="+height);
        try
        {
            chart.setBounds(0, 0, width, height);
            if(last_width != width || last_height != height)
            {
                chart.invalidate();
                last_width = width;
                last_height = height;
            }
            chart.doLayout();
            chart.paint(g2);
        }
        finally
        {
            g2.dispose();
        }
    } 
    /**
     * Receives notification of changes to the chart, and redraws the chart.
     *
     * @param event  details of the chart change event.
     */
    @Override
    public void chartChanged(Chart.ChangeEvent event)
    {
        repaint();
    }
    /**
     * Updates the UI for a LookAndFeel change.
     */
    @Override
    public void updateUI() {
        // here we need to update the UI for the popup menu, if the panel
        // has one...
        if (this.popup != null) {
            SwingUtilities.updateComponentTreeUI(this.popup);
        }
        super.updateUI();
    }
    private class DefaultUserEventHandler extends AbstractUserEventHandler
    {
        @Override
        public void mouseEntered(MouseEvent e)
        {
        }
        @Override
        public void mouseExited(MouseEvent e)
        {
        }
        @Override
        public void mousePressed(MouseEvent e)
        {
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
        }
        @Override
        public void mouseClicked(MouseEvent e)
        {
        }
        @Override
        public void mouseMoved(MouseEvent e)
        {
        }
        @Override
        public void mouseDragged(MouseEvent e)
        {
        }
        @Override
        public void actionPerformed(ActionEvent event)
        {
        }
    }
}
