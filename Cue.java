public class Cue<T> {
    Box<T> head, tail;

    public Cue() {
        head = (Box<T>) new Box<Integer>(0, null);
        tail = head;
    }

    public boolean encue(Box<T> b) {
        if (b == null)
            return false;
        if (tail == null) {
            head = b;
            tail = b;
            b.next = null;
            return true;
        }
        tail.next = b;
        b.next = null;
        tail = b;
        return true;
    }

    public Box<T> decue() {
        if (head == null)
            return null;

        Box<T> temp = head;
        head = head.next;
        temp.next = null;

        if (head == null)
            tail = null;

        return temp;
    }

    public int size() {
        int size = 0;
        Box<T> runner = head;
        while (runner != null) {
            size++;
            runner = runner.next;
        }
        return size;
    }

    public String toString() {
        String str = "" + head + "->";
        if (tail != null) {
            str += tail.toString();
        } else {
            str += "null";
        }
        return str;
    }
}
