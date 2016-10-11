package lightsimulation.core;

import lightsimulation.content.*;

import java.util.ArrayList;

/**
 * @author Oskar StrandBerg & Pontus Prins
 * @version 0.9
 *
 * This handles the calculations of the simulation,
 * it does it in an very inefficient way that is wasting
 * lots of memory. To fix it we probably need to rewrite big chunks of our code.
 */
public class Physics {

    private Settings settings;
    private LaserPointer laserP;
    protected ArrayList<LaserBeam> laserBeams;
    private int x = 0;
    /**
     * Initializes elements used in Physics.
     *
     * @param settings is needed to be able to get all current objects.
     * @param laserP is need to create and delete laser beams.
     */
    public Physics(Settings settings, LaserPointer laserP){

        this.settings = settings;
        this.laserP = laserP;

        laserBeams = new ArrayList<LaserBeam>();
        laserBeams.add(new LaserBeam(laserP.beamStartX(), laserP.beamStartY(), 1200, 400));

    }

    /**
     * Checks if any laser beams have collided
     * with an object, if so it calls
     * <method>handleCollision</method>
     * and if there is no collision it calls
     * <method>handleNoCollision</method>.
     *
     */
    protected void updatePhysics(){

        laserBeams.get(0).newAngle(laserP.angle, laserP.beamStartX(), laserP.beamStartY());

        removeLasers();

        if(!settings.objects.isEmpty())
            hasCollided(laserBeams.get(0));
        x = 0;
    }


    private void hasCollided(LaserBeam beam){

        byte i = 0;
        while(i <= settings.objects.size() - 1 && !beam.hasCollided){
            if(settings.objects.get(i).checkCollision(beam.getXStart(), beam.getYStart(), beam.getK(), beam.getM(), beam)){
                handleCollision(beam, i);
                beam.hasCollided = true;
            }
            i++;
        }
    }

    /**
     * Removes all lasers that has been created after
     * the laser on position pos in the laserBeams ArrayList,
     * and sets the boolean hasCollided to false.
     *
     */
    private void removeLasers(){
        for(int i = laserBeams.size() - 1; i > 0; i--){
            laserBeams.remove(i);
        }
        laserBeams.get(0).hasCollided = false;
    }

    /**
     * Will calculate the new lasers angle.
     * Right now it only creates an new laser and
     * draws the collided laser to the collision.
     *
     * @param laserB the laserBeam that collided
     * @param pos the position of the object it
     *            collided with in the ArrayList.
     */
    private void handleCollision(LaserBeam laserB, int pos){

        double xCollide = settings.objects.get(pos).getXCollide();
        double yCollide = settings.objects.get(pos).getYCollide();

        //in this case it moves the ending point of the laser.
        laserB.move(xCollide, yCollide);

        double angle = calculateAngle(laserB, pos);

        if(angle > 2 * Math.PI)
            angle -= 2 * Math.PI;

        laserBeams.add(new LaserBeam(xCollide + Math.cos(angle), yCollide - Math.sin(angle), xCollide + (1000 * Math.cos(angle)), yCollide -(1000 * Math.sin(angle)) ));
        hasCollided(laserBeams.get(laserBeams.size() - 1));

    }

    private double calculateAngle(LaserBeam laserB, int pos){
        x++;
        Shape collidedShape = settings.objects.get(pos);

        /** If the collided shape is an triangle. */
        if(collidedShape.getClass() == Triangle.class) {

            double n1;
            double n2;

            double normal = 0.0;
            double alpha = 0.0;
            double lineAngle = 0.0;
            double criticalAngle = Math.PI/2;

            if(collidedShape.isClicked(laserB.getXStart(), laserB.getYStart())){
                n1 = settings.getSelectedShapeIndex();
                n2 = settings.getSelectedWindowIndex();
                criticalAngle = Math.asin(n2/n1);
            } else{
                n1 = settings.getSelectedWindowIndex();
                n2 = settings.getSelectedShapeIndex();
            }

            if(collidedShape.getCollidedLineIndex() == 1) {
                normal = collidedShape.getFirstAngle() + (Math.PI / 2);
                alpha = Math.abs(normal - laserB.getAngle());
                lineAngle = collidedShape.getFirstAngle();

            } else if (collidedShape.getCollidedLineIndex() == 2){
                normal = (Math.PI - collidedShape.getFirstAngle()) + (Math.PI / 2);
                alpha = Math.abs(normal - laserB.getAngle());
                lineAngle = (Math.PI - collidedShape.getFirstAngle());

            } else if(collidedShape.getCollidedLineIndex() == 3){
                normal = (Math.PI/2);
                alpha = Math.abs(normal - laserB.getAngle());
            }

            if(alpha == criticalAngle)
                return lineAngle;

            System.out.println("Alpha" + x +  ": " + Math.toDegrees(alpha));
            System.out.println("New Angle" + x + ": " + Math.toDegrees(Math.asin((n1 * Math.sin(alpha)) / n2)));

            if(laserB.getAngle() > normal){

                if(alpha > criticalAngle)
                    return (laserB.getAngle() - (2 * alpha));
                else
                    return ((normal + Math.PI) + Math.asin((n1 * Math.sin(alpha)) / n2));

            } else if(laserB.getAngle() < normal){
                if(alpha > criticalAngle)
                    return (laserB.getAngle() + (2 * alpha));
                else
                    return ((normal + Math.PI) - Math.asin((n1 * Math.sin(alpha)) / n2));
            }

        }
        return 0.0;
    }
}
