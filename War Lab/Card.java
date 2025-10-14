public class Card {
    public String rank = "";
    public String suit = "";
    public String display = "";

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.display = getDisplay();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

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

    private String getDisplay() {
        if (rank.equals("A") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|A_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____A|";
        } else if (rank.equals("A") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|A ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____A|";
        } else if (rank.equals("A") && suit.equals("Club")) {
            return " _____ \n" +
                    "|A _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____A|";
        } else if (rank.equals("A") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|A .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____A|";
        } else if (rank.equals("2") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|2_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____2|";
        } else if (rank.equals("2") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|2 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____2|";
        } else if (rank.equals("2") && suit.equals("Club")) {
            return " _____ \n" +
                    "|2 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____2|";
        } else if (rank.equals("2") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|2 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____2|";
        } else if (rank.equals("3") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|3_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____3|";
        } else if (rank.equals("3") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|3 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____3|";
        } else if (rank.equals("3") && suit.equals("Club")) {
            return " _____ \n" +
                    "|3 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____3|";
        } else if (rank.equals("3") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|3 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____3|";
        } else if (rank.equals("4") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|4_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____4|";
        } else if (rank.equals("4") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|4 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____4|";
        } else if (rank.equals("4") && suit.equals("Club")) {
            return " _____ \n" +
                    "|4 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____4|";
        } else if (rank.equals("4") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|4 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____4|";
        } else if (rank.equals("5") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|5_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____5|";
        } else if (rank.equals("5") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|5 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____5|";
        } else if (rank.equals("5") && suit.equals("Club")) {
            return " _____ \n" +
                    "|5 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____5|";
        } else if (rank.equals("5") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|5 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____5|";
        } else if (rank.equals("6") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|6_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____6|";
        } else if (rank.equals("6") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|6 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____6|";
        } else if (rank.equals("6") && suit.equals("Club")) {
            return " _____ \n" +
                    "|6 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____6|";
        } else if (rank.equals("6") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|6 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____6|";
        } else if (rank.equals("7") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|7_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____7|";
        } else if (rank.equals("7") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|7 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____7|";
        } else if (rank.equals("7") && suit.equals("Club")) {
            return " _____ \n" +
                    "|7 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____7|";
        } else if (rank.equals("7") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|7 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____7|";
        } else if (rank.equals("8") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|8_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____8|";
        } else if (rank.equals("8") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|8 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____8|";
        } else if (rank.equals("8") && suit.equals("Club")) {
            return " _____ \n" +
                    "|8 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____8|";
        } else if (rank.equals("8") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|8 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____8|";
        } else if (rank.equals("9") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|9_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____9|";
        } else if (rank.equals("9") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|9 ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____9|";
        } else if (rank.equals("9") && suit.equals("Club")) {
            return " _____ \n" +
                    "|9 _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____9|";
        } else if (rank.equals("9") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|9 .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____9|";
        } else if (rank.equals("X") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|X_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____X|";
        } else if (rank.equals("X") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|X ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____X|";
        } else if (rank.equals("X") && suit.equals("Club")) {
            return " _____ \n" +
                    "|X _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____X|";
        } else if (rank.equals("X") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|X .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____X|";
        } else if (rank.equals("J") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|J_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____J|";
        } else if (rank.equals("J") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|J ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____J|";
        } else if (rank.equals("J") && suit.equals("Club")) {
            return " _____ \n" +
                    "|J _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____J|";
        } else if (rank.equals("J") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|J .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____J|";
        } else if (rank.equals("Q") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|Q_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____Q|";
        } else if (rank.equals("Q") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|Q ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____Q|";
        } else if (rank.equals("Q") && suit.equals("Club")) {
            return " _____ \n" +
                    "|Q _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____Q|";
        } else if (rank.equals("Q") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|Q .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____Q|";
        } else if (rank.equals("K") && suit.equals("Heart")) {
            return " _____ \n" +
                    "|K_ _ |\n" +
                    "|( v )|\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____K|";
        } else if (rank.equals("K") && suit.equals("Diamond")) {
            return " _____ \n" +
                    "|K ^  |\n" +
                    "| / \\ |\n" +
                    "| \\ / |\n" +
                    "|  .  |\n" +
                    "|____K|";
        } else if (rank.equals("K") && suit.equals("Club")) {
            return " _____ \n" +
                    "|K _  |\n" +
                    "| ( ) |\n" +
                    "|(_'_)|\n" +
                    "|  |  |\n" +
                    "|____K|";
        } else if (rank.equals("K") && suit.equals("Spade")) {
            return " _____ \n" +
                    "|K .  |\n" +
                    "| /.\\ |\n" +
                    "|(_._)|\n" +
                    "|  |  |\n" +
                    "|____K|";
        } else {
            return " _____ \n" +
                    "|?    |\n" +
                    "| ?   |\n" +
                    "|    ?|\n" +
                    "‾‾‾‾‾‾‾";
        }
    }
}
