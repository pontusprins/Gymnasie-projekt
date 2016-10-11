package lightsimulation.core;

import lightsimulation.content.LaserPointer;
import lightsimulation.content.Triangle;

import javax.swing.*;
import java.awt.*;

/**
 *  @author Pontus Prins & Oskar Strandberg
 *  @version 0.5 3/2/2014
 *
 *  This class handles the basic things of the program, it load up the simulation,
 *  start everything, keep it running and stops the program.
 *
 *  It will also initialize important values like finals if needed.
 *
 */

public class Core extends JComponent implements Runnable {

    private JFrame frame;
    private Thread thread;
    private Settings settings;
    private MouseHandler mHandler;
    private LaserPointer laserP;
    private Physics phys;

    private boolean running = false;

    /**
     * Initializes Core and it's JFrame,
     * also starts the simulation.
     *
     */
    public static void main(String[] args){

        Core core = new Core();

        core.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        core.frame.setResizable(false);
        core.frame.add(core);
        core.frame.setPreferredSize(new Dimension(1200, 800));
        core.frame.pack();
        core.frame.setLocationRelativeTo(null);
        core.frame.setVisible(true);
        core.start();
    }

    /** Initializes all core elements of the simulation. */
    public Core(){

        laserP = new LaserPointer(300, 375);
        frame = new JFrame("Light Simulation 2000");
        thread = new Thread(this, "Main");
        settings = new Settings();
        mHandler = new MouseHandler(settings);
        phys = new Physics(settings, laserP);
        addMouseListener(mHandler);
        addMouseMotionListener(mHandler);
    }

    /** Start the simulation */
    private void start(){
        thread.start();
        running = true;
    }

    /**
     * Paint the window with objects used in
     * the simulation.
     *
     * It loops through the ArrayList objects
     * from the class Settings that contains
     * all objects that exists in the simulation.
     *
     * @param g the specific graphic content.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        laserP.draw(g2);

        for(int i = 0; i < phys.laserBeams.size(); i++)
            phys.laserBeams.get(i).draw(g2);

        for(int i = 0; i < settings.objects.size(); i++)
            settings.objects.get(i).draw(g2);
    }

    /** Updates all calculations */
    public void updatePhys(){
        laserP.angle = Math.toRadians(settings.laserPointerAngle);

        phys.updatePhysics();
    }

    /**
     * Updates the simulation 60 times / second to ensure
     * a smooth experience when using the simulation.
     *
     */
    @Override
    public void run(){

        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        requestFocus();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                updatePhys();
                repaint();
                delta--;
            }

        }
    }
}


















