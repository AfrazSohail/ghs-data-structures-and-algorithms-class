/**
 * Implementation of the Least Significant Digit (LSD) radix sort algorithm with visualization.
 * Sorts animals by weight starting from the least significant digit and progressing to
 * the most significant digit. Includes animated visualization of the sorting process.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab.LSD;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

import Classwork.Unit4.RadixSortLab.AnimalVal;
import Classwork.Unit4.RadixSortLab.AnimateMove;

public class LSDSort {
    /**
     * Entry point for LSD radix sort. Finds the maximum weight and initiates
     * the sorting process starting from the least significant digit.
     *
     * @param animals the list of animals to sort by weight
     */
    public static void lsdSort(ArrayList<AnimalVal> animals) {
        if (animals.size() == 0)
            return;

        int max = animals.get(0).getWeight();
        for (AnimalVal animal : animals) {
            int w = animal.getWeight();
            if (w > max)
                max = w;
        }

        lsdSort(animals, 1, max);
    }

    /**
     * Recursive helper method that sorts by each digit position from least to most
     * significant.
     *
     * @param animals the list of animals to sort
     * @param exp     the current digit position (power of 10: 1, 10, 100, etc.)
     * @param max     the maximum weight value in the array
     */
    private static void lsdSort(ArrayList<AnimalVal> animals, int exp, int max) {
        if (max / exp == 0) {
            RunLSD.pointer.setVisible(false);
            RunLSD.numberLine.setVisible(false);
            RunLSD.canvas.repaint();
            return;
        }

        countSort(animals, exp);
        lsdSort(animals, exp * 10, max);
    }

    /**
     * Performs counting sort on the animals based on the digit at the current
     * position (exp).
     * Includes animated visualization of the sorting process with pointer movement
     * and line drawing.
     *
     * @param animals the list of animals to sort
     * @param exp     the current digit position (power of 10)
     */
    private static void countSort(ArrayList<AnimalVal> animals, int exp) {
        AnimalVal[] output = new AnimalVal[animals.size()];
        int[] count = new int[10];

        int animCount = 0;
        ArrayList<JLabel> lines = new ArrayList<JLabel>();
        for (AnimalVal animal : animals) {
            count[(animal.getWeight() / exp) % 10]++;

            RunLSD.pointer.setVisible(true);
            int targetX = ((javax.swing.JComponent) RunLSD.imgs.get(animCount).getParent()).getX();
            AnimateMove.animatedMove(RunLSD.pointer, targetX + 6, 48, 320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            int numLinePos = (animal.getWeight() / exp) % 10;
            numLinePos = numLinePos * 36 * 3 + 50;
            AnimateMove.animatedMove(RunLSD.pointer, numLinePos, 200, 320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            int curCount = count[(animal.getWeight() / exp) % 10];
            JLabel line = RunLSD.makeLine();
            RunLSD.canvas.add(line);
            line.setBounds(numLinePos + 18 * 3 - 53, 161 - curCount * 6, 36 * 3, 2 * 3);
            line.setVisible(true);
            lines.add(line);
            RunLSD.canvas.repaint();

            animCount++;
        }

        for (int i = 1; i < 10; i++) {
            int delta = count[i];
            count[i] += count[i - 1];
            delta = count[i] - delta;

            int numLinePos = i;
            numLinePos = numLinePos * 36 * 3 + 50;

            AnimateMove.animatedMove(RunLSD.pointer, numLinePos, 200, 320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            for (int j = count[i] - delta; j < count[i]; j++) {
                JLabel line = RunLSD.makeLine();
                RunLSD.canvas.add(line);
                line.setBounds(numLinePos + 18 * 3 - 53, 161 - (j + 1) * 6, 36 * 3, 2 * 3);
                line.setVisible(true);
                lines.add(line);
                RunLSD.canvas.repaint();
            }
        }

        int animPosIndex[] = new int[animals.size()];
        for (int i = animals.size() - 1; i >= 0; i--) {
            int d = (animals.get(i).getWeight() / exp) % 10;
            int pos = --count[d];
            output[pos] = animals.get(i);
            animPosIndex[pos] = i;

            int targetX = ((JComponent) RunLSD.imgs.get(i).getParent()).getX();
            AnimateMove.animatedMove(RunLSD.pointer, targetX, 48, 320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            int numberLinePos = d;
            numberLinePos = numberLinePos * 36 * 3 + 50;
            JComponent animalPanel = (JComponent) RunLSD.imgs.get(i).getParent();
            AnimateMove.animatedMoveMultiple(new JComponent[] { RunLSD.pointer, animalPanel },
                    numberLinePos, 150,
                    320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            int newTargetX = pos;
            newTargetX = newTargetX * (48 + 8);
            AnimateMove.animatedMoveMultiple(new JComponent[] { RunLSD.pointer, animalPanel },
                    newTargetX + 6, 210,
                    320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }
        }
        for (int i = 0; i < animals.size(); i++) {
            animals.set(i, output[i]);

            int targetX = i * (48 + 8);
            AnimateMove.animatedMove(RunLSD.pointer, targetX, 210, 320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }

            int newTargetX = i * (48 + 8);
            JComponent animalPanel = (JComponent) RunLSD.imgs.get(animPosIndex[i]).getParent();
            AnimateMove.animatedMoveMultiple(new JComponent[] { RunLSD.pointer,
                    animalPanel },
                    newTargetX, 0,
                    320 / 5 * RunLSD.getSpeed(), RunLSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunLSD.getSpeed());
            } catch (InterruptedException e) {
            }
        }

        for (JLabel line : lines)
            RunLSD.canvas.remove(line);

        javax.swing.SwingUtilities.invokeLater(() -> {
            RunLSD.clearImages();
            RunLSD.makeImgs(animals);
            RunLSD.canvas.revalidate();
            RunLSD.canvas.repaint();
        });
    }
}
