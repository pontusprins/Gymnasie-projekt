package lightsimulation.content;

import java.awt.*;

/**
 * @author Pontus Prins
 * @version 0.8
 *
 * This should be implemented whenever you
 * wish to create an object that you can draw onto
 * an window, and move to new locations in the window.
 *
 */

public interface Shape {

    /**
     * Call this when you want to draw the object
     * in a window.
     *
     * @param g2 the Graphics2D you want to use for drawing
     */
    void draw(Graphics2D g2);

    /**
     * Use this when you want to move the object to a new location.
     *
     * @param dX the amount of pixels in x it will be moved.
     * @param dY the amount of pixels in y it will be moved.
     */
    void moveTo(double dX, double dY);

    /**
     * Checks if the user have clicked inside the object.
     *
     * @param mY mouse x location.
     * @param mX mouse y location.
     */
    boolean isClicked(double mX, double mY);

    /**
     *
     *
     * @return the x position of latest collision
     */
    double getXCollide();

    /**
     *
     * @return the y position of the latest collision
     */
    double getYCollide();

    boolean checkCollision(double x, double y, double k, double m, LaserBeam beam);

    byte getCollidedLineIndex();

    boolean checkIndex(double currentIndex);
    double getFirstAngle();
}
