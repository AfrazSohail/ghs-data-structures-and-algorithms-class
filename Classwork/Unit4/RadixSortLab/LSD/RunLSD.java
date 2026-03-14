/**
 * Manages the GUI and visualization for the LSD (Least Significant Digit) radix sort.
 * Creates the canvas, initializes visual elements (pointer, number line, animal images),
 * and provides utility methods for animation and rendering.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab.LSD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import Classwork.Unit4.RadixSortLab.AnimalVal;

public class RunLSD {

    /**
     * Clears all animal image panels from the canvas.
     * Removes the parent containers of each image label to ensure complete cleanup.
     */
    public static void clearImages() {
        for (JLabel img : imgs) {
            if (img.getParent() != null) {
                canvas.remove(img.getParent());
            }
        }
        imgs.clear();
        canvas.repaint();
    }

    /** List of animal image JLabels currently displayed on the canvas */
    public static ArrayList<JLabel> imgs = new ArrayList<JLabel>();
    /** JLabel displaying the number line (0-9) for digit visualization */
    public static JLabel numberLine = new JLabel();
    /** JLabel displaying the pointer that indicates current sorting position */
    public static JLabel pointer = new JLabel();
    /** The main canvas panel where all visual elements are rendered */
    public static JPanel canvas;
    /** Slider for controlling animation speed (1 = fastest, 5 = slowest) */
    public static JSlider speedSlider = new JSlider(1, 5, 1);
    /** List of AnimalVal objects being sorted */
    public static ArrayList<AnimalVal> animVals = new ArrayList<AnimalVal>();

    /**
     * Initializes and runs the LSD sort visualization.
     * Creates the GUI window, sets up the canvas, initializes visual elements,
     * and starts the sorting algorithm in a separate thread.
     *
     * @param vals array of AnimalVal objects to sort
     */
    public static void runLSDSort(AnimalVal[] vals) {
        JFrame frame = new JFrame("");
        frame.setSize(1200, 350 + 10 + 35);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        canvas = new JPanel(null);
        canvas.setBackground(new Color(103, 102, 51));
        frame.setContentPane(canvas);

        animVals = new ArrayList<AnimalVal>();
        for (AnimalVal val : vals)
            animVals.add(val);
        makeImgs(animVals);
        initHelpers();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(() -> {
            LSDSort.lsdSort(animVals);
        }).start();
    }

    /**
     * Initializes helper visual elements including the pointer sprite,
     * the number line, and the speed control slider.
     */
    private static void initHelpers() {
        int imgW = 48;
        int imgH = 48;

        String pointerPath = "Classwork\\Unit4\\RadixSortLab\\Data\\Helper\\pointer.png";
        String numberLinePath = "Classwork\\Unit4\\RadixSortLab\\Data\\Helper\\numberLine.png";

        ImageIcon pointerIcon = new ImageIcon(pointerPath);
        Image scaledPointer = pointerIcon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
        pointer.setIcon(new ImageIcon(scaledPointer));
        pointer.setBounds(0, 150, imgW, imgH);
        pointer.setVisible(false);
        canvas.add(pointer);

        ImageIcon numberLineIcon = new ImageIcon(numberLinePath);
        Image scaledNumberLine = numberLineIcon.getImage().getScaledInstance(360 * 3, 28 * 3, Image.SCALE_SMOOTH);
        numberLine.setIcon(new ImageIcon(scaledNumberLine));
        numberLine.setBounds(50, 100, 360 * 3, 28 * 3);
        canvas.add(numberLine);

        speedSlider.setBounds(50, 300, 360 * 3, 10);
        canvas.add(speedSlider);
    }

    /**
     * Creates and returns a new line JLabel for visualizing count values.
     * The line is added to the canvas but initially set to invisible.
     *
     * @return a new JLabel representing a counting line
     */
    public static JLabel makeLine() {
        int imgW = 36 * 3;
        int imgH = 2 * 3;
        String linePath = "Classwork\\Unit4\\RadixSortLab\\Data\\Helper\\line.png";

        JLabel line = new JLabel();

        ImageIcon lineIcon = new ImageIcon(linePath);
        Image scaledLine = lineIcon.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
        line.setIcon(new ImageIcon(scaledLine));
        line.setBounds(0, 200, imgW, imgH);
        line.setVisible(false);
        canvas.add(line);

        return line;
    }

    /**
     * Gets the current speed multiplier based on the slider value.
     * Lower slider values result in faster animations.
     *
     * @return speed multiplier (1-5, where 5 is slowest)
     */
    public static int getSpeed() {
        return 6 - speedSlider.getValue();
    }

    /**
     * Creates and displays animal image panels on the canvas.
     * Each panel contains an animal image and its weight label.
     *
     * @param vals list of AnimalVal objects to display
     */
    public static void makeImgs(ArrayList<AnimalVal> vals) {
        int itemW = 48; // container width (image + label)
        int itemH = 60; // container height
        int imgW = 48; // scaled image width
        int imgH = 48; // scaled image height
        int gap = 8; // horizontal gap between items

        ImageIcon[] icons = new ImageIcon[vals.size()];
        for (int i = 0; i < vals.size(); i++) {
            String path = "Classwork\\Unit4\\RadixSortLab\\Data\\Animals\\" + vals.get(i).getName() + ".png";
            ImageIcon original = new ImageIcon(path);
            Image scaled = original.getImage().getScaledInstance(imgW, imgH, Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(scaled);

            JLabel imgLabel = new JLabel(icons[i], SwingConstants.CENTER);
            imgLabel.setPreferredSize(new Dimension(imgW, imgH));
            imgs.add(imgLabel);

            JLabel textLabel = new JLabel("wt: " + vals.get(i).getWeight(), SwingConstants.CENTER);

            JPanel item = new JPanel(new BorderLayout());
            item.setOpaque(false);
            item.add(imgLabel, BorderLayout.CENTER);
            item.add(textLabel, BorderLayout.SOUTH);

            int x = i * (itemW + gap);
            int y = 0;
            item.setBounds(x, y, itemW, itemH);

            canvas.add(item);
        }
    }
}
