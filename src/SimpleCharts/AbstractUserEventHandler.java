package SimpleCharts;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author frank
 */
abstract public class AbstractUserEventHandler implements UserEventHandler
{
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseMoved(MouseEvent e){}
    @Override
    public void mouseDragged(MouseEvent e){}
    @Override
    public void actionPerformed(ActionEvent event){}
}
