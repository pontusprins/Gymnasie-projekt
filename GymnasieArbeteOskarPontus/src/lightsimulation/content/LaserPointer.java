package lightsimulation.content;

import java.awt.*;

/**
 *
 */
public class LaserPointer{

    private double x, y;
    private double xStart, yStart;
    public double angle;

    public LaserPointer(double x, double y){
        this.x = x;
        this.y = y;

        angle = Math.PI;

        xStart = x + 100;
        yStart = y + 25;
    }

    public double beamStartX(){
        return xStart;
    }

    public double beamStartY() {
        return yStart;
    }

    public void draw(Graphics2D g2) {
        x = (500 + (300 * Math.cos(angle)));
        y = (375 + (300 * -Math.sin(angle)));
        xStart = x + 100;
        yStart = y + 25;

        g2.fillRect((int) x, (int) y, 100, 50);

    }

}
