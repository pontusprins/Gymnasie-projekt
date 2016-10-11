package lightsimulation.core;

import lightsimulation.content.*;
import lightsimulation.content.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 *  @author Pontus Prins & Oskar Strandberg
 *  @version 0.9 28/12/2014
 *
 *  This class handles all settings, with this you will be able to change
 *  values and other things for simulating different scenarios.
 *
 *  This class will also handle the user interface for the setting in
 *  a simple style with buttons.
 *
 */

public class Settings extends JFrame{

    protected double selectedWindowIndex;
    protected double selectedShapeIndex;
    protected double laserPointerAngle;

    protected ArrayList<lightsimulation.content.Shape> objects;

    /** Initializes the JFrame for the Settings. */
    protected Settings(){

        new JFrame("Settings");
        setResizable(false);
        setPreferredSize(new Dimension(200, 300));
        setLayout(new GridLayout(6, 2));
        initComponents();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        selectedWindowIndex = 1.000;
        selectedShapeIndex = 1.466;
        laserPointerAngle = 180;
        objects = new ArrayList<Shape>();

    }

    /** @return the current window index */
    public double getSelectedWindowIndex(){
        return selectedWindowIndex;
    }

    /** @return the current shape index */
    public double getSelectedShapeIndex(){
        return selectedShapeIndex;
    }

    /**
     * Initializes all UI components.
     *
     * Also adds new object when the user clicks on it's
     * respectively button
     */
    private void initComponents(){

        String[] refractionOptions = {"AIR", "GLASS", "WATER", "PLASTIC"};

        final JComboBox<String> refractions, refractionsShape;
        final JLabel indexLabel, indexLabelShape, angleSliderLabel;

        JLabel indexWindowHeadline;
        JLabel indexShapeHeadline;
        JLabel emptySpace1;
        JLabel emptySpace2;
        final JSlider laserAngleSlider;

        JButton squareButton, triangleButton;

        add(squareButton = new JButton("Square"));
        add(triangleButton = new JButton("Triangle 1"));

        add(indexWindowHeadline = new JLabel("Window Index:"));
        add(emptySpace1 = new JLabel(""));
        add(refractions = new JComboBox<String>(refractionOptions));
        add(indexLabel = new JLabel("Index: 1.0"));

        add(indexShapeHeadline = new JLabel("Shape Index:")).setForeground(Color.WHITE);
        add(emptySpace2 = new JLabel(""));
        add(refractionsShape = new JComboBox<String>(refractionOptions));
        add(indexLabelShape = new JLabel("Index: 1.466"));
        refractionsShape.setSelectedIndex(3);

        add(laserAngleSlider = new JSlider(JSlider.HORIZONTAL, 90, 180, 180));
        add(angleSliderLabel = new JLabel(180 + ": degrees"));
        indexWindowHeadline.setOpaque(true);
        indexWindowHeadline.setBackground(Color.gray);
        indexWindowHeadline.setForeground(Color.WHITE);
        indexShapeHeadline.setOpaque(true);
        indexShapeHeadline.setBackground(Color.gray);

        emptySpace1.setOpaque(true);
        emptySpace1.setBackground(Color.gray);
        emptySpace2.setOpaque(true);
        emptySpace2.setBackground(Color.gray);

        refractions.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRefraction = refractions.getSelectedIndex();

                if (selectedRefraction == 0) {
                    selectedWindowIndex = 1.000;
                    indexLabel.setText("Index: " + selectedWindowIndex);
                }
                if (selectedRefraction == 1) {
                    selectedWindowIndex = 1.510;
                    indexLabel.setText("Index: " + selectedWindowIndex);
                }
                if (selectedRefraction == 2) {
                    selectedWindowIndex = 1.329;
                    indexLabel.setText("Index: " + selectedWindowIndex);
                }
                if (selectedRefraction == 3) {
                    selectedWindowIndex = 1.466;
                    indexLabel.setText("Index: " + selectedWindowIndex);
                }
            }
        });

        refractionsShape.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRefractionShape = refractionsShape.getSelectedIndex();

                if(selectedRefractionShape == 0){
                    selectedShapeIndex = 1.000;
                    indexLabelShape.setText("Index: " + selectedShapeIndex);
                }
                if(selectedRefractionShape == 1){
                    selectedShapeIndex = 1.510;
                    indexLabelShape.setText("Index: " + selectedShapeIndex);
                }
                if(selectedRefractionShape == 2){
                    selectedShapeIndex = 1.329;
                    indexLabelShape.setText("Index: " + selectedShapeIndex);
                }
                if(selectedRefractionShape == 3){
                    selectedShapeIndex = 1.466;
                    indexLabelShape.setText("Index: " + selectedShapeIndex);
                }
            }
        });

        //adds a new square if clicked.
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.add(new Square((int)(Math.random()*1100), (int)(Math.random()*700), 150, 100));
            }
        });

        //add a new triangle if clicked.
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objects.add(new Triangle((int)(Math.random()*1100 + 50), (int)(Math.random()*700), 55.4, 100));
            }
        });

        laserAngleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                laserPointerAngle = laserAngleSlider.getValue();
                angleSliderLabel.setText(laserPointerAngle + ": degrees");
            }
        });
    }
}
