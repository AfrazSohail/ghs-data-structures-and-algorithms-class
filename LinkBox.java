import java.util.Iterator;

public class LinkBox<T> implements Iterable<T> {
    Box<T> head;
    // add
    // remove
    // get
    // size and isEmpty

    @Override
    public Iterator<T> iterator() {
        return new BoxCutter();
    }

    private class BoxCutter implements Iterator<T> {
        Box<T> amazonEmployee;

        public BoxCutter() {
            amazonEmployee = head;
        }

        @Override
        public boolean hasNext() {
            return !(amazonEmployee == null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                T temp = amazonEmployee.data;
                amazonEmployee = amazonEmployee.next;
                return temp;
            }
            return null;
        }
    }
}
