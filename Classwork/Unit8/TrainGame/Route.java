import java.util.ArrayList;

public class Route {
    private ArrayList<City> cities = new ArrayList<>();
    private int distance;

    public static enum Color {
        X, P, B, G, Y, O, R, W, K
    }

    private Color color;

    public Route(City city1, City city2, int distance, char color) {
        this.cities.add(city1);
        this.cities.add(city2);
        cities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        this.distance = distance;
        this.color = Color.valueOf(String.valueOf(color));
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public City getNeighbour(City city) {
        if (cities.get(0).equals(city)) {
            return cities.get(1);
        } else if (cities.get(1).equals(city)) {
            return cities.get(0);
        } else {
            throw new IllegalArgumentException("City not found in route");
        }
    }

    public boolean containsCities(City city1, City city2) {
        return cities.contains(city1) && cities.contains(city2);
    }

    public int getDistance() {
        return distance;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cities.get(0)).append("--").append(distance).append(color).append("->").append(cities.get(1));
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + cities.hashCode();
        hash = 31 * hash + distance;
        hash = 31 * hash + color.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route other = (Route) obj;
        return distance == other.distance && color == other.color && cities.equals(other.cities);
    }
}
