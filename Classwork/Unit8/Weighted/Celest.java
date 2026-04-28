package Weighted;

import java.util.HashMap;

public class Celest implements Comparable<Celest> {
	String name;
	double price;
	Celest previous;
	HashMap<Celest, Double> neighbors;
	public Celest(String name) {
		this.name = name;
		neighbors = new HashMap<Celest, Double>();
	}
	@Override
    public int compareTo(Celest other) {
        // TODO Auto-generated method stub
        return (int) (this.price * 100 - other.price * 100);
    }

    @Override
    public String toString() {
        return name;
    }

}
