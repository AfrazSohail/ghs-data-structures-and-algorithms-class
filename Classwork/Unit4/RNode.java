package Classwork.Unit4;

public class RNode<T> {
    T data;
    RNode<T> next;

    public RNode(T data) {
        this.data = data;
    }

    public void add(T d) {
        if (this.next == null) {
            this.next = new RNode<T>(d);
            return;
        }

        this.next.add(d);
    }

    public RNode<T> remove(T d) {
        if (this.data.equals(d))
            return this.next;

        if (this.next != null)
            this.next = this.next.next;
        return this;
    }

    public String toString() {
        if (this.next == null) {
            return data.toString();
        }
        return data.toString() + "->" + next.toString();
    }
}
