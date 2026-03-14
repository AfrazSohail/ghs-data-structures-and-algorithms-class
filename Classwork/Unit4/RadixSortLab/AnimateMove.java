/**
 * Provides smooth animation utilities for moving Swing components across a canvas.
 * Animations run on separate threads to prevent blocking the EDT.
 *
 * @author Afraz Sohail
 * @version 1.0
 * Written by AI
 */
package Classwork.Unit4.RadixSortLab;

import javax.swing.*;

public class AnimateMove {
    /**
     * Animates a single JLabel from its current position to a target position.
     *
     * @param sprite   the JLabel to animate
     * @param targetX  the target X coordinate
     * @param targetY  the target Y coordinate
     * @param duration the animation duration in milliseconds
     * @param canvas   the parent canvas to repaint during animation
     */
    public static void animatedMove(JLabel sprite, int targetX, int targetY, int duration, JPanel canvas) {
        new Thread(() -> {
            int startX = sprite.getX();
            int startY = sprite.getY();
            int steps = 20;

            for (int i = 0; i <= steps; i++) {
                int newX = startX + (targetX - startX) * i / steps;
                int newY = startY + (targetY - startY) * i / steps;

                SwingUtilities.invokeLater(() -> {
                    sprite.setLocation(newX, newY);
                    canvas.repaint();
                });
                try {
                    Thread.sleep(duration / steps);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    /**
     * Animates multiple JLabels simultaneously to the same target position.
     *
     * @param sprites  array of JLabels to animate
     * @param targetX  the target X coordinate
     * @param targetY  the target Y coordinate
     * @param duration the animation duration in milliseconds
     * @param canvas   the parent canvas to repaint during animation
     */
    public static void animatedMoveMultiple(JLabel[] sprites, int targetX, int targetY, int duration, JPanel canvas) {
        new Thread(() -> {
            int[] startX = new int[sprites.length];
            int[] startY = new int[sprites.length];

            for (int i = 0; i < sprites.length; i++) {
                startX[i] = sprites[i].getX();
                startY[i] = sprites[i].getY();
            }

            int steps = 20;
            for (int i = 0; i <= steps; i++) {
                final int step = i;
                SwingUtilities.invokeLater(() -> {
                    for (int j = 0; j < sprites.length; j++) {
                        int newX = startX[j] + (targetX - startX[j]) * step / steps;
                        int newY = startY[j] + (targetY - startY[j]) * step / steps;
                        sprites[j].setLocation(newX, newY);
                    }
                    canvas.repaint();
                });
                try {
                    Thread.sleep(duration / steps);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    /**
     * Animates multiple JComponents simultaneously to the same target position.
     * Supports any Swing component, not just JLabel.
     *
     * @param components array of JComponents to animate
     * @param targetX    the target X coordinate
     * @param targetY    the target Y coordinate
     * @param duration   the animation duration in milliseconds
     * @param canvas     the parent canvas to repaint during animation
     */
    public static void animatedMoveMultiple(JComponent[] components, int targetX, int targetY, int duration,
            JPanel canvas) {
        new Thread(() -> {
            int[] startX = new int[components.length];
            int[] startY = new int[components.length];

            for (int i = 0; i < components.length; i++) {
                startX[i] = components[i].getX();
                startY[i] = components[i].getY();
            }

            int steps = 20;
            for (int i = 0; i <= steps; i++) {
                final int step = i;
                SwingUtilities.invokeLater(() -> {
                    for (int j = 0; j < components.length; j++) {
                        int newX = startX[j] + (targetX - startX[j]) * step / steps;
                        int newY = startY[j] + (targetY - startY[j]) * step / steps;
                        components[j].setLocation(newX, newY);
                    }
                    canvas.repaint();
                });
                try {
                    Thread.sleep(duration / steps);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }
}
