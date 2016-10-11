package lightsimulation.content;

import java.awt.*;

/**
 * @author Oskar Strandberg
 * @version 0.5
 *
 * Creates a new Square that implements methods from the
 * Interface Shape.
 *
 * This class will later be filled with more methods
 * necessary for calculating the simulation.
 *
 */


/**
 * OSKAR IS SO FUCKING FULL OF GODNESS
 */
public class Square implements Shape {

    protected static final byte FIRST_LINE = 1, SECOND_LINE = 2, THIRD_LINE = 3, FORTH_LINE = 4;

   // private BufferedImage img;
    private double x, y, s, l;
    public double xCollision, yCollision;

    public byte getCollidedLine;

    /**
     * Create a new square.
     *
     * @param x the x location you wish the square to be drawn.
     * @param y the y location you wish the square to be drawn.
     * @param s the width(and height) of the square.
     */
    public Square(double x, double y, double s, double l){
        this.x = x;
        this.y = y;
        this.s = s;
        this.l = l;

        /*
        try {
            img = ImageIO.read(new File("resources\\haha.jpg"));
        }catch (IOException e){
            System.out.print("HAHA INGEN BILD");
        }*/
    }

    /**
     * Checks if the user have clicked inside the square, if so
     * it calls the <method> moveTo </method> which moves the square
     * by the mouse position.
     *
     * @param mX the x position the user clicked.
     * @param mY the y position the user clicked.
     */
    public boolean isClicked(double mX, double mY) {
        return (mX >= x && mX <= x + s && mY >= y && mY <= y + l);
    }

    @Override
    public boolean checkCollision(double laserX, double laserY, double k, double m, LaserBeam beam) {
        xCollision = x;
        yCollision = y + (laserY - y);


        return ((laserX == x) && (laserY >= y) && (laserY <= (y + s)) || (laserY == y )&& (laserX >= x) && (laserX <= (x + l)));

    }


    @Override
    public byte getCollidedLineIndex() {
        return 0;
    }

    @Override
    public double getXCollide() {
        return (int)xCollision;
    }

    @Override
    public double getYCollide() {
        return (int)yCollision;
    }

    @Override
    public boolean checkIndex(double currentIndex) {
        return false;
    }

    /**
     * Move the square to the new location.
     *
     * @param dX dX the amount of pixels in x it will be moved.
     * @param dY the amount of pixels in y it will be moved.
     */
    public void moveTo(double dX, double dY){
        x += dX;
        y += dY;
    }


    /**
     * Draw the square on to the window.
     *
     * @param g2 the specific graphic content
     */
    public void draw(Graphics2D g2){
        g2.drawRect((int) x, (int) y, (int) s, (int) l);
      //  g2.drawImage(img, (int) x, (int)y, null);
    }

    @Override
    public double getFirstAngle() {
        return 0;
    }
}
