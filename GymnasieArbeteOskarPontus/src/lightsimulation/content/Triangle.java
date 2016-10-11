package lightsimulation.content;

import java.awt.*;

/**
 * @author Pontus Prins
 * @version 0.5
 *
 * Creates a new Triangle that implements methods from the
 * Interface Shape.
 *
 * This class will later be filled with more methods
 * necessary for calculating the simulation.
 *
 */
public class Triangle implements Shape {

    protected final static byte FIRST_LINE = 1, SECOND_LINE = 2, THIRD_LINE = 3;

    public byte collidedLineIndex;

    private double x, y, b, h;
    private double k, m1, m2;
    private double angle, length;
    public double xCollision, yCollision;

    /**
     * Create a new triangle.
     *
     * @param x the x location you wish the square to be drawn.
     * @param y the y location you wish the square to be drawn.
     * @param b the base width of the triangle.
     * @param h the height of the triangle.
     */
    public Triangle(double x, double y, double b, double h){
        this.x = x;
        this.y = y;
        this.b = b;
        this.h = h;

        length = Math.sqrt(Math.pow(h, 2) + Math.pow(b, 2));
        angle = Math.acos(b/length);
        k = (y - (y+h) )/(x - (x - b));
        m1 = y -(k * x);
        m2 = y -(Math.abs(k) * x);
    }

    /**
     * Checks if the user has clicked
     * inside of the triangle.
     *
     * @param mX mouse y location.
     * @param mY mouse x location.
     */
    public boolean isClicked(double mX, double mY) {

        double leftLine = x - (mY * b)/(y + h);
        double rightLine = x + (mY * b)/(y + h);
        double bothLines = y + ((Math.abs(x - mX) * h) / b);

        return (mX >= leftLine && mX <= rightLine && mY >= bothLines && mY <= y + h);
    }

    public boolean checkCollision(double laserX, double laserY, double laserK, double laserM, LaserBeam beam){

        double alphaXDistance = (laserX + 500 * Math.cos(beam.getAngle())) - laserX;
        double alphaYDistance = (laserY + 500 * -Math.sin(beam.getAngle())) - laserY;
        boolean shouldCollide = false;
        xCollision = (laserM - m1)/(k - laserK);
        yCollision = laserK * xCollision + laserM;

        if(alphaXDistance < 0)
            shouldCollide = (laserX < xCollision);
        else if(alphaXDistance > 0)
            shouldCollide = (laserX > xCollision);

        if(xCollision > (x-b) && xCollision < x && shouldCollide){
            collidedLineIndex = FIRST_LINE;
            return true;
        }

        xCollision = (laserM - m2)/(Math.abs(k) - laserK);
        yCollision = laserK * xCollision + laserM;

        if(alphaXDistance < 0)
            shouldCollide = (laserX < xCollision);
        else if(alphaXDistance > 0)
            shouldCollide = (laserX > xCollision);

        if(xCollision < (x+b) && xCollision > x && shouldCollide){
            collidedLineIndex = SECOND_LINE;
            return true;
        }

        yCollision = y + h;
        xCollision = (yCollision - laserM)/laserK;

        if(alphaYDistance < 0)
            shouldCollide = (laserY < yCollision);
        else if(alphaYDistance > 0)
            shouldCollide = (laserY > yCollision);

        if(xCollision > (x - b) && xCollision < (x + b) && shouldCollide){
            collidedLineIndex = THIRD_LINE;
            return true;
        }

        return false;
    }

    public boolean checkIndex(double currentIndex){
        return false;
    }

    /**
     * Moves the triangle
     *
     * @param dX the amount of pixels in x it will be moved.
     * @param dY the amount of pixels in y it will be moved.
     */
    public void moveTo(double dX, double dY) {
        x += dX;
        y += dY;

        m1 = y - (k * x);
        m2 = y - (Math.abs(k) * x);
    }

    public byte getCollidedLineIndex(){
        return collidedLineIndex;
    }

    /**
     * Draw an triangle.
     *
     * @param g2 the Graphics2D you want to use for drawing
     */
    public void draw(Graphics2D g2) {
        int leftXPoint = (int) (x + b);
        int rightXPoint = (int) (x - b);
        int height = (int) (y + h);
        g2.drawLine((int) x, (int) y, leftXPoint, height);
        g2.drawLine(leftXPoint,  height, rightXPoint, height);
        g2.drawLine(rightXPoint, height, (int) x, (int) y);
    }


    public double getXCollide(){
        return xCollision;
    }

    public double getYCollide(){
        return yCollision;
    }

    @Override
    public double getFirstAngle() {
        return angle;
    }
}
