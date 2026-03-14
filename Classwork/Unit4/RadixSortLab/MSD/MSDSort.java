/**
 * Implementation of the Most Significant Digit (MSD) radix sort algorithm with visualization.
 * Sorts animals by weight starting from the most significant digit and recursively
 * sorting subarrays for each digit bucket. Includes animated visualization of the sorting process.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab.MSD;

import java.util.ArrayList;
import javax.swing.JLabel;

import Classwork.Unit4.RadixSortLab.AnimalVal;
import Classwork.Unit4.RadixSortLab.AnimateMove;

public class MSDSort {
    /**
     * Entry point for MSD radix sort. Finds the maximum weight and determines
     * the most significant digit position to begin sorting.
     *
     * @param animals the list of animals to sort by weight
     */
    public static void msdSort(ArrayList<AnimalVal> animals) {
        if (animals.size() == 0)
            return;

        int max = animals.get(0).getWeight();
        for (AnimalVal animal : animals)
            if (animal.getWeight() > max)
                max = animal.getWeight();

        int exp = 1;
        while (max / exp >= 10)
            exp *= 10;

        msdSort(animals, 0, animals.size() - 1, exp);
    }

    /**
     * Recursive MSD sort that sorts a subarray from index lo to hi based on the
     * digit at position exp.
     * After distributing elements into digit buckets, recursively sorts each bucket
     * with the next
     * less significant digit.
     *
     * @param animals the list of animals to sort
     * @param lo      the starting index of the subarray (inclusive)
     * @param hi      the ending index of the subarray (inclusive)
     * @param exp     the current digit position (power of 10: 100, 10, 1, etc.)
     */
    private static void msdSort(ArrayList<AnimalVal> animals, int lo, int hi, int exp) {
        if (lo >= hi || exp == 0) {
            RunMSD.pointer.setVisible(false);
            RunMSD.numberLine.setVisible(false);
            RunMSD.bucketLeft.setVisible(false);
            RunMSD.bucketRight.setVisible(false);
            RunMSD.canvas.repaint();
            return;
        }

        RunMSD.bucketLeft.setVisible(true);
        RunMSD.bucketLeft.setBounds(lo * 56, 0, 2 * 3, 16 * 3);

        RunMSD.bucketRight.setVisible(true);
        RunMSD.bucketRight.setBounds((hi) * 56 + 48, 0, 2 * 3, 16 * 3);

        int[] count = new int[11];
        AnimalVal[] auxilary = new AnimalVal[hi - lo + 1];
        ArrayList<JLabel> lines = new ArrayList<JLabel>();

        for (int i = lo; i <= hi; i++) {
            int d = (animals.get(i).getWeight() / exp) % 10;
            count[d + 1]++;

            if (RunMSD.imgs.size() > i) {
                RunMSD.pointer.setVisible(true);
                int targetX = ((javax.swing.JComponent) RunMSD.imgs.get(i).getParent()).getX();
                AnimateMove.animatedMove(RunMSD.pointer, targetX + 6, 48, 320 / 5 * RunMSD.getSpeed(), RunMSD.canvas);
                try {
                    Thread.sleep(500 / 5 * RunMSD.getSpeed());
                } catch (InterruptedException e) {
                }

                int numLinePos = d + 1;
                numLinePos = numLinePos * 36 * 3 + 50;
                AnimateMove.animatedMove(RunMSD.pointer, numLinePos, 200, 320 / 5 * RunMSD.getSpeed(), RunMSD.canvas);
                try {
                    Thread.sleep(500 / 5 * RunMSD.getSpeed());
                } catch (InterruptedException e) {
                }

                int curCount = count[d + 1];
                JLabel line = RunMSD.makeLine();
                RunMSD.canvas.add(line);
                line.setBounds(numLinePos + 18 * 3 - 53, 161 - curCount * 6, 36 * 3, 2 * 3);
                line.setVisible(true);
                lines.add(line);
                RunMSD.canvas.repaint();
            }
        }

        for (int i = 0; i < 10; i++) {
            int delta = count[i + 1];
            count[i + 1] += count[i];
            delta = count[i + 1] - delta;

            int numLinePos = i + 1;
            numLinePos = numLinePos * 36 * 3 + 50;

            AnimateMove.animatedMove(RunMSD.pointer, numLinePos, 150, 320 / 5 * RunMSD.getSpeed(), RunMSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunMSD.getSpeed());
            } catch (InterruptedException e) {
            }

            for (int j = count[i + 1] - delta; j < count[i + 1]; j++) {
                JLabel line = RunMSD.makeLine();
                RunMSD.canvas.add(line);
                line.setBounds(numLinePos + 18 * 3 - 53, 161 - (j + 1) * 6, 36 * 3, 2 * 3);
                line.setVisible(true);
                lines.add(line);
                RunMSD.canvas.repaint();
            }
        }

        for (int i = lo; i <= hi; i++) {
            int d = (animals.get(i).getWeight() / exp) % 10;
            auxilary[count[d]++] = animals.get(i);

            int targetX = ((javax.swing.JComponent) RunMSD.imgs.get(i).getParent()).getX();
            AnimateMove.animatedMove(RunMSD.pointer, targetX, 48, 320 / 5 * RunMSD.getSpeed(), RunMSD.canvas);
            try {
                Thread.sleep(500 / 5 * RunMSD.getSpeed());
            } catch (InterruptedException e) {
            }
        }

        for (int i = lo; i <= hi; i++) {
            animals.set(i, auxilary[i - lo]);
        }

        for (int i = 0; i < 10; i++) {
            int start = lo + count[i];
            int end = lo + count[i + 1] - 1;

            for (JLabel line : lines)
                RunMSD.canvas.remove(line);
            javax.swing.SwingUtilities.invokeLater(() -> {
                RunMSD.clearImages();
                RunMSD.makeImgs(animals);
                RunMSD.canvas.revalidate();
                RunMSD.canvas.repaint();
            });

            if (start < end)
                msdSort(animals, start, end, exp / 10);
        }
    }
}
