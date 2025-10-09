package Classwork;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Queued {

    public static void main(String[] args) {

        Queue<String> TheHulk = new LinkedList<String>();
        TheHulk.offer("Sarge");
        TheHulk.offer("Mike");
        TheHulk.offer("Bruce");

        System.out.println(TheHulk);
        System.out.println(stutter(TheHulk));
        System.out.println(mirror(TheHulk));
    }

    private static Queue<String> stutter(Queue<String> queue) {
        for (int i = 0; i < queue.size(); i++) {
            queue.offer(queue.peek());
            queue.offer(queue.poll());
        }
        return queue;
    }

    private static Queue<String> mirror(Queue<String> queue) {
        Stack<String> stack = new Stack<String>();
        for (String item : queue) {
            stack.push(item);
        }
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }
        return queue;
    }
}
