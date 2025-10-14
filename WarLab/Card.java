/**
 * Represents a playing card with a rank, suit, and ASCII art display.
 * This class provides functionality for creating cards, getting their values,
 * and generating visual representations for display in the War card game.
 *
 * @author Your Name
 * @version 1.0
 */
public class Card {
    /** The rank of the card (A, 2-10, J, Q, K) */
    public String rank = "";

    /** The suit of the card (Heart, Diamond, Club, Spade) */
    public String suit = "";

    /** ASCII art representation of the card */
    public String display = "";

    /**
     * Constructs a new Card with the specified rank and suit.
     * The display representation is automatically generated.
     *
     * @param rank the rank of the card (A, 2-10, J, Q, K)
     * @param suit the suit of the card (Heart, Diamond, Club, Spade)
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.display = getDisplay();
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string in the format "rank of suit" (e.g., "A of Hearts")
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Gets the numerical value of the card for comparison purposes.
     * In the War card game, Ace has the lowest value (1), and King has the highest
     * (13).
     *
     * @return the numerical value of the card:
     *         - Ace (A): 1
     *         - Number cards (2-10): face value
     *         - Jack (J): 11
     *         - Queen (Q): 12
     *         - King (K): 13
     *         - Unknown rank: 0
     */
    public int getValue() {
        switch (rank) {
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            case "X":
                return 10;
            case "9":
                return 9;
            case "8":
                return 8;
            case "7":
                return 7;
            case "6":
                return 6;
            case "5":
                return 5;
            case "4":
                return 4;
            case "3":
                return 3;
            case "2":
                return 2;
            case "A":
                return 1;
            default:
                return 0; // Unknown rank
        }
    }

    /**
     * Generates an ASCII art representation of the card based on its rank and suit.
     * Each card has a unique visual representation with appropriate suit symbols.
     *
     * @return a multi-line string containing ASCII art of the card, or a generic
     *         unknown card representation if the rank/suit combination is not
     *         recognized
     */
    private String getDisplay() {
        if (rank.equals("A") && suit.equals("Heart")) {
            return "|____A|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|A_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("A") && suit.equals("Diamond")) {
            return "|____A|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|A ^  |\n" +
                    " _____ ";
        } else if (rank.equals("A") && suit.equals("Club")) {
            return "|____A|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|A _  |\n" +
                    " _____ ";
        } else if (rank.equals("A") && suit.equals("Spade")) {
            return "|____A|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|A .  |\n" +
                    " _____ ";
        } else if (rank.equals("2") && suit.equals("Heart")) {
            return "|____2|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|2_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("2") && suit.equals("Diamond")) {
            return "|____2|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|2 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("2") && suit.equals("Club")) {
            return "|____2|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|2 _  |\n" +
                    " _____ ";
        } else if (rank.equals("2") && suit.equals("Spade")) {
            return "|____2|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|2 .  |\n" +
                    " _____ ";
        } else if (rank.equals("3") && suit.equals("Heart")) {
            return "|____3|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|3_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("3") && suit.equals("Diamond")) {
            return "|____3|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|3 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("3") && suit.equals("Club")) {
            return "|____3|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|3 _  |\n" +
                    " _____ ";
        } else if (rank.equals("3") && suit.equals("Spade")) {
            return "|____3|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|3 .  |\n" +
                    " _____ ";
        } else if (rank.equals("4") && suit.equals("Heart")) {
            return "|____4|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|4_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("4") && suit.equals("Diamond")) {
            return "|____4|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|4 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("4") && suit.equals("Club")) {
            return "|____4|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|4 _  |\n" +
                    " _____ ";
        } else if (rank.equals("4") && suit.equals("Spade")) {
            return "|____4|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|4 .  |\n" +
                    " _____ ";
        } else if (rank.equals("5") && suit.equals("Heart")) {
            return "|____5|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|5_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("5") && suit.equals("Diamond")) {
            return "|____5|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|5 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("5") && suit.equals("Club")) {
            return "|____5|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|5 _  |\n" +
                    " _____ ";
        } else if (rank.equals("5") && suit.equals("Spade")) {
            return "|____5|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|5 .  |\n" +
                    " _____ ";
        } else if (rank.equals("6") && suit.equals("Heart")) {
            return "|____6|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|6_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("6") && suit.equals("Diamond")) {
            return "|____6|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|6 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("6") && suit.equals("Club")) {
            return "|____6|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|6 _  |\n" +
                    " _____ ";
        } else if (rank.equals("6") && suit.equals("Spade")) {
            return "|____6|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|6 .  |\n" +
                    " _____ ";
        } else if (rank.equals("7") && suit.equals("Heart")) {
            return "|____7|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|7_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("7") && suit.equals("Diamond")) {
            return "|____7|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|7 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("7") && suit.equals("Club")) {
            return "|____7|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|7 _  |\n" +
                    " _____ ";
        } else if (rank.equals("7") && suit.equals("Spade")) {
            return "|____7|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|7 .  |\n" +
                    " _____ ";
        } else if (rank.equals("8") && suit.equals("Heart")) {
            return "|____8|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|8_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("8") && suit.equals("Diamond")) {
            return "|____8|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|8 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("8") && suit.equals("Club")) {
            return "|____8|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|8 _  |\n" +
                    " _____ ";
        } else if (rank.equals("8") && suit.equals("Spade")) {
            return "|____8|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|8 .  |\n" +
                    " _____ ";
        } else if (rank.equals("9") && suit.equals("Heart")) {
            return "|____9|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|9_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("9") && suit.equals("Diamond")) {
            return "|____9|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|9 ^  |\n" +
                    " _____ ";
        } else if (rank.equals("9") && suit.equals("Club")) {
            return "|____9|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|9 _  |\n" +
                    " _____ ";
        } else if (rank.equals("9") && suit.equals("Spade")) {
            return "|____9|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|9 .  |\n" +
                    " _____ ";
        } else if (rank.equals("X") && suit.equals("Heart")) {
            return "|____X|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|X_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("X") && suit.equals("Diamond")) {
            return "|____X|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|X ^  |\n" +
                    " _____ ";
        } else if (rank.equals("X") && suit.equals("Club")) {
            return "|____X|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|X _  |\n" +
                    " _____ ";
        } else if (rank.equals("X") && suit.equals("Spade")) {
            return "|____X|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|X .  |\n" +
                    " _____ ";
        } else if (rank.equals("J") && suit.equals("Heart")) {
            return "|____J|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|J_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("J") && suit.equals("Diamond")) {
            return "|____J|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|J ^  |\n" +
                    " _____ ";
        } else if (rank.equals("J") && suit.equals("Club")) {
            return "|____J|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|J _  |\n" +
                    " _____ ";
        } else if (rank.equals("J") && suit.equals("Spade")) {
            return "|____J|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|J .  |\n" +
                    " _____ ";
        } else if (rank.equals("Q") && suit.equals("Heart")) {
            return "|____Q|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|Q_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("Q") && suit.equals("Diamond")) {
            return "|____Q|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|Q ^  |\n" +
                    " _____ ";
        } else if (rank.equals("Q") && suit.equals("Club")) {
            return "|____Q|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|Q _  |\n" +
                    " _____ ";
        } else if (rank.equals("Q") && suit.equals("Spade")) {
            return "|____Q|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|Q .  |\n" +
                    " _____ ";
        } else if (rank.equals("K") && suit.equals("Heart")) {
            return "|____K|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "|( v )|\n" +
                    "|K_ _ |\n" +
                    " _____ ";
        } else if (rank.equals("K") && suit.equals("Diamond")) {
            return "|____K|\n" +
                    "|  .  |\n" +
                    "| \\ / |\n" +
                    "| / \\ |\n" +
                    "|K ^  |\n" +
                    " _____ ";
        } else if (rank.equals("K") && suit.equals("Club")) {
            return "|____K|\n" +
                    "|  |  |\n" +
                    "|(_'_)|\n" +
                    "| ( ) |\n" +
                    "|K _  |\n" +
                    " _____ ";
        } else if (rank.equals("K") && suit.equals("Spade")) {
            return "|____K|\n" +
                    "|  |  |\n" +
                    "|(_._)|\n" +
                    "| /.\\ |\n" +
                    "|K .  |\n" +
                    " _____ ";
        } else {
            return "‾‾‾‾‾‾‾\n" +
                    "|    ?|\n" +
                    "| ?   |\n" +
                    "|?    |\n" +
                    " _____ ";
        }
    }
}
