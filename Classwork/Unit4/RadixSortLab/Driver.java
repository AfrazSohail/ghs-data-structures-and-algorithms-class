/**
 * Main driver class for the Radix Sort Lab visualization application.
 * Provides a GUI interface for selecting parameters and choosing between
 * LSD (Least Significant Digit) and MSD (Most Significant Digit) radix sorting algorithms.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab;

import javax.swing.*;

import Classwork.Unit4.RadixSortLab.LSD.RunLSD;
import Classwork.Unit4.RadixSortLab.MSD.RunMSD;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Driver {
    /**
     * Main entry point for the Radix Sort Lab application.
     * Sets the UI look and feel and starts the initial GUI.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); // Motif
        } catch (Exception ignored) {
        }
        start();
    }

    /**
     * Displays the initial start screen with a button to begin the application.
     */
    private static void start() {
        JFrame frame = new JFrame("");
        frame.setSize(200, 80 + 10 + 35);
        frame.setLocationRelativeTo(null);

        JButton startBtn = new JButton("Start?!");
        startBtn.setPreferredSize(new Dimension(200, 80));

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                generateVal();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(startBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Displays the weight range selection screen where users can specify
     * minimum and maximum weight values for generating animal data.
     */
    private static void generateVal() {
        JFrame frame = new JFrame("");
        frame.setSize(300, 30 + 10 + 35);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JLabel rangeLabel = new JLabel("Range: ");
        JSpinner minSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 50));
        JLabel dashLabel = new JLabel("-");
        JSpinner maxSpinner = new JSpinner(new SpinnerNumberModel(1000, 0, 1000, 50));

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(e -> {
            frame.dispose();

            int min = (int) minSpinner.getValue();
            int max = (int) maxSpinner.getValue();
            if (max > 999)
                max = 999;

            try {
                AnimalVal[] vals = GenerateVal.generateVals((min < max) ? min : max, (min < max) ? max : min, 20);
                chooseRadixSort(vals);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error: File not found - " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(rangeLabel);
        frame.add(minSpinner);
        frame.add(dashLabel);
        frame.add(maxSpinner);
        frame.add(confirmBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Displays the sorting algorithm selection screen where users can choose
     * between LSD (Least Significant Digit) and MSD (Most Significant Digit) radix
     * sort.
     *
     * @param vals the array of AnimalVal objects to be sorted
     */
    private static void chooseRadixSort(AnimalVal[] vals) {
        JFrame frame = new JFrame("");
        frame.setSize(200, 130 + 10 + 35);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        JRadioButton lsdBtn = new JRadioButton("LSD Sort");
        JRadioButton msdBtn = new JRadioButton("MSD Sort");

        ButtonGroup group = new ButtonGroup();
        group.add(lsdBtn);
        group.add(msdBtn);

        lsdBtn.setSelected(true);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setPreferredSize(new Dimension(200, 100));

        confirmBtn.addActionListener(e -> {
            frame.dispose();
            if (lsdBtn.isSelected())
                RunLSD.runLSDSort(vals);
            else
                RunMSD.runMSDSort(vals);
        });

        frame.add(lsdBtn);
        frame.add(msdBtn);
        frame.add(confirmBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
