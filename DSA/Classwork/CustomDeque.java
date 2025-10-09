package Classwork;

public class CustomDeque<T> {

    T[] container;
    int front, back;

    public CustomDeque(int capacity) {
        container = (T[]) new Object[capacity];
        front = capacity / 2;
        back = capacity / 2;
    }

    public T addFront(T item) {
        if (front == -1) {
            front = container.length / 2;
            back = container.length / 2;
            container[front] = item;
            return item;
        }

        container[--front] = item;
        if (front == 0) {
            // move them;
        }
        return item;
    }

}
