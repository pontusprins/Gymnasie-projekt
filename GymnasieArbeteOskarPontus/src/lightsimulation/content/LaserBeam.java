package lightsimulation.content;

import java.awt.*;

/**
 * @author Pontus Prins
 * @version 1.0
 *
 * Contains and handles properties
 * of a laser beams used in the simulation.
 *
 * The laser beam is treated with an linear equation
 * (y = kx + m) so must therefore calculate k and m
 * values.
 */
public class LaserBeam {

    private double xStart, yStart;
    private double xEnd, yEnd;

    private double angle;
    private double length;

    public double k, m;
    public boolean hasCollided = false;

    /**
     * Initializes a new laser beam based on
     * the given start values.
     *
     * The laser beam is treated as an linear equation,
     * so the constructor calculates the k and m value
     * in the equation y = kx + m.
     *
     * It also calculates the angle of the laser beam with
     * trigonometric methods.
     *
     * @param xStart , x start value
     * @param yStart , y start value
     * @param xEnd , x end value
     * @param yEnd , y end value
     */
    public LaserBeam(double xStart, double yStart, double xEnd, double yEnd){

        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;

        k = (yEnd - yStart)/(xEnd - xStart);
        m = yStart - (k * xStart);

        length = Math.sqrt(Math.pow(Math.abs(xEnd - xStart), 2) + Math.pow(Math.abs(yEnd - yStart), 2));
        angle = Math.acos((xStart - xEnd)/length);
    }

    /**
     *
     * @param g2 the specific graphic content.
     */
    public void draw(Graphics2D g2){

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.GREEN);
        g2.drawLine((int) xStart, (int) yStart, (int) xEnd, (int) yEnd);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));

    }

    /**
     * Give the laser beam a new angle and
     * recalculate k, and m values.
     *
     * @param newAngle the new angle
     * @param x start value in x
     * @param y start value in y
     */
    public void newAngle(double newAngle, double x, double y){

        angle = newAngle;

        xStart = x;
        yStart = y;

        xEnd = 600;
        yEnd = 400;

        k = (yEnd - yStart)/(xEnd - xStart);
        m = yStart - (k * xStart);
    }

    /**
     * Give the laser beam new end
     * position
     *
     * @param xEnd , position in x
     * @param yEnd , position in y
     */
    public void move(double xEnd, double yEnd){
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public double getAngle(){
        return angle;
    }

    public double getLength(){
        return length;
    }

    public double getK(){
        return k;
    }

    public double getM(){
        return m;
    }

    public double getXStart(){
        return xStart;
    }

    public double getYStart(){
        return yStart;
    }
}
