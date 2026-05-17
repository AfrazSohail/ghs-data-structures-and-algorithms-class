
/**
 * Represents a track connecting two cities in the train game.
 * A track contains two cities, a distance, and a color designation.
 * Provides methods to convert characters to track colors.
 *
 * @author Afraz Sohail
 * @version 1.0
 * @note Documentation written by insert model
 */
public class Track {

    private final City city1;
    private final City city2;
    private final int distance;
    private final TrackColor color;

    public static enum TrackColor {
        X, Y, B, G, P, O, W, K, R
    }

    /**
     * Converts a character to its corresponding TrackColor enum value.
     *
     * @param c the character to convert (X, Y, B, G, P, O, W, K, or R)
     * @return the corresponding TrackColor enum value
     * @throws IllegalArgumentException if the character is not a valid track
     * color
     */
    public static TrackColor charToColor(char c) {
        switch (c) {
            case 'X' -> {
                return TrackColor.X;
            }
            case 'Y' -> {
                return TrackColor.Y;
            }
            case 'B' -> {
                return TrackColor.B;
            }
            case 'G' -> {
                return TrackColor.G;
            }
            case 'P' -> {
                return TrackColor.P;
            }
            case 'O' -> {
                return TrackColor.O;
            }
            case 'W' -> {
                return TrackColor.W;
            }
            case 'K' -> {
                return TrackColor.K;
            }
            case 'R' -> {
                return TrackColor.R;
            }
            default ->
                throw new IllegalArgumentException("Invalid track color character: " + c);
        }
    }

    /**
     * Constructs a Track connecting two cities.
     *
     * @param city1 the first city
     * @param city2 the second city
     * @param distance the distance between the cities
     * @param color the color of the track
     */
    public Track(City city1, City city2, int distance, TrackColor color) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
        this.color = color;
    }

    /**
     * Returns the first city connected by this track.
     *
     * @return the first city
     */
    public City getCity1() {
        return city1;
    }

    /**
     * Returns the second city connected by this track.
     *
     * @return the second city
     */
    public City getCity2() {
        return city2;
    }

    /**
     * Returns the distance of this track.
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the color of this track.
     *
     * @return the track color
     */
    public TrackColor getColor() {
        return color;
    }

    /**
     * Returns a formatted string representation of this track.
     *
     * @return a string showing the connected cities, color, and distance
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(city1).append(" --").append(color)
                .append(distance).append("-> ").append(city2).append("\n");
        return sb.toString();
    }
}
