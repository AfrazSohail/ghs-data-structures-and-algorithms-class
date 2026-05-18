
public class City {

    private String name;

    public City(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        City other = (City) obj;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
