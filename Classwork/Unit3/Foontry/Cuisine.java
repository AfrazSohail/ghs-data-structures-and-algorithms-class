package Classwork.Unit3.Foontry;

import java.util.HashSet;

public class Cuisine {
    public HashSet<String> dishes;

    public Cuisine(HashSet<String> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "[dishes=" + dishes + "]";
    }

}
