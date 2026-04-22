package Unweighted;
public class Meanie {
    String name;
    int id;
    String rumor;

    public Meanie(String name, int id) {
        super();
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return  name + " id." + id + " rumor: " + rumor ;
    }
}
