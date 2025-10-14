import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;

public abstract class Dealer {

    static Scanner sc = new Scanner(System.in);

    public static Card GetHigherCard(Card cardA, Card cardB) {
        if (cardA.getValue() == 1) {
            if (cardB.getValue() == 2) {
                return cardB;
            } else if (!(cardB.getValue() == 1)) {
                return cardA;
            } else {
                return null; // Indicates a tie
            }
        }

        if (cardB.getValue() == 1) {
            if (cardA.getValue() == 2) {
                return cardA;
            } else if (!(cardA.getValue() == 1)) {
                return cardB;
            } else {
                return null; // Indicates a tie
            }
        }

        if (cardA.getValue() > cardB.getValue()) {
            return cardA;
        } else if (cardA.getValue() < cardB.getValue()) {
            return cardB;
        } else {
            return null; // Indicates a tie
        }
    }

    public static Stack<Card> makeDeck(int rankLimit) {

        rankLimit = Math.max(2, Math.min(13, rankLimit));

        String ranks = "A23456789XJQK".substring(0, rankLimit);
        Stack<String> suits = new Stack<String>();
        suits.push("Heart");
        suits.push("Diamond");
        suits.push("Club");
        suits.push("Spade");

        Stack<Card> deck = new Stack<Card>();

        while (!suits.isEmpty()) {
            String suit = suits.pop();
            for (Character rank : ranks.toCharArray()) {
                deck.push(new Card(rank.toString(), suit));
            }
        }
        return deck;
    }

    public static void shuffleDeck(Stack<Card> deck) {
        for (int i = 0; i < 5; i++) {
            // System.out.println("--------");
            // printDeckMini(deck);

            Stack<Card> tempDeck1 = new Stack<Card>();
            Stack<Card> tempDeck2 = new Stack<Card>();

            int random = (int) (Math.random() * (deck.size() - 1)) + 1;

            for (int j = 0; j < random; j++) {
                tempDeck1.push(deck.pop());
            }
            while (!deck.isEmpty()) {
                tempDeck2.push(deck.pop());
            }

            // System.out.println("----");
            // printDeckMini(tempDeck1);
            // printDeckMini(tempDeck2);

            while (!tempDeck1.isEmpty() || !tempDeck2.isEmpty()) {
                if (!tempDeck1.isEmpty()) {
                    deck.push(tempDeck1.pop());
                }
                if (!tempDeck2.isEmpty()) {
                    deck.push(tempDeck2.pop());
                }
            }
        }
    }

    public static void printDeck(Stack<Card> deck) {
        for (Card card : deck) {
            System.out.println(card.display);
        }
    }

    public static void printDeckMini(Stack<Card> deck) {
        for (Card card : deck) {
            System.out
                    .print(card.rank
                            + (card.suit.equals("Heart") ? " H"
                                    : card.suit.equals("Diamond") ? " D"
                                            : card.suit.equals("Club") ? " C" : card.suit.equals("Spade") ? " S" : "")
                            + " | ");
        }
        System.out.println();
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void PrintBothCards(Stack<Card> player1Deck, Stack<Card> player2Deck) {

        String[] card1Lines = player1Deck.peek().display.split("\n");
        String[] card2Lines = player2Deck.peek().display.split("\n");

        // Add card backs in lines of five per row
        StringBuilder player1CardsBuilder = new StringBuilder();
        for (int i = 0; i < player1Deck.size(); i++) {
            player1CardsBuilder.append("[?]");
            if ((i + 1) % 5 == 0 && i != player1Deck.size() - 1) {
                player1CardsBuilder.append("\n");
            }
        }
        StringBuilder player2CardsBuilder = new StringBuilder();
        for (int i = 0; i < player2Deck.size(); i++) {
            player2CardsBuilder.append("[?]");
            if ((i + 1) % 5 == 0 && i != player2Deck.size() - 1) {
                player2CardsBuilder.append("\n");
            }
        }

        String[] player1CardsLines = player1CardsBuilder.toString().split("\n");
        String[] player2CardsLines = player2CardsBuilder.toString().split("\n");

        // Add each line of card backs to card1Lines and card2Lines
        for (int i = 0; i < Math.max(player1CardsLines.length, player2CardsLines.length); i++) {
            card1Lines = Arrays.copyOf(card1Lines, card1Lines.length + 1);
            card1Lines[card1Lines.length - 1] = i < player1CardsLines.length ? player1CardsLines[i] : "";
            card2Lines = Arrays.copyOf(card2Lines, card2Lines.length + 1);
            card2Lines[card2Lines.length - 1] = i < player2CardsLines.length ? player2CardsLines[i] : "";
        }

        for (int i = 0; i < card1Lines.length; i++) {
            System.out.printf("%-20s %s%n", card1Lines[i], card2Lines[i]);
        }
    }

    public static void emptyCardsIntoStack(Stack<Card> source, Stack<Card> destination) {
        while (!source.isEmpty()) {
            destination.push(source.pop());
        }
    }

    public static String interject(String message, boolean clearAfterInput) {
        if (message == null) {
            message = "";
        }
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        String input = sc.nextLine().trim();
        if (clearAfterInput) {
            Dealer.clearConsole();
        }
        return input;
    }
}
