package Classwork.Unit7;

public class Injury implements Comparable<Object> {
    String injury;
    int priority;

    public Injury(String injury, int priority) {
        super();
        this.injury = injury;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "injury=" + injury + ", priority=" + priority;
    }

    @Override
    public int compareTo(Object other) {
        return ((Injury) other).priority - this.priority;
    }

}
