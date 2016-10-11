package lightsimulation.core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Pontus Prins
 * @version 0.5
 *
 * Handles mouse events.
 * Right now it checks movement in mouse
 * position and handles the movement of objects.
 *
 * Maybe function like rotate an object with
 * the mouse wheel will be implemented later on.
 *
 */
public class MouseHandler extends MouseAdapter{

    protected int x, y;
    protected int dX, dY;

    private Settings settings;

    /**
     * Creates a new MouseHandler.
     *
     * @param settings will need the class settings in order to move objects.
     */
    public MouseHandler(Settings settings){
        this.settings = settings;
    }

    /**
     * Updates the x and y when user clicks for just
     * a moment.
     *
     * @param e event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Updates the x and y position when the user
     * press down the mouse button.
     *
     * @param e event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Updates x and y when the user drags the mouse
     * and presses the mouse button.
     *
     * This also moves an object if the user have
     * clicked inside of it.
     *
     * @param e event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        dX = e.getX() - x;
        dY = e.getY() - y;

        for (int i = 0; i < settings.objects.size(); i++)
            if (settings.objects.get(i).isClicked(x, y))
                settings.objects.get(i).moveTo(dX, dY);

        x += dX;
        y += dY;

    }
}
