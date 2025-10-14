public class Box<T> implements Comparable {
    T data;
    Box<T> next;

    public Box(T data, Box<T> box) {
        this.data = data;
        this.next = box;
    }

    public String toString() {
        String str = "" + data + "->";
        if (next != null) {
            str += next.toString(); // Explicit call is clearer
        } else {
            str += "null"; // Terminate the chain
        }
        return str;
    }

    @Override
    public int compareTo(Object o) {
        return ((Comparable) data).compareTo(((Box) o).data);
    }
}
