import java.util.Stack;
import java.util.Scanner;

/**
 * Abstract utility class providing static methods for card game operations.
 * This class contains methods for deck creation, shuffling, card comparison,
 * display utilities, and user interaction for the War card game.
 *
 * @author Your Name
 * @version 1.0
 */
public abstract class Dealer {

    /** Scanner instance for user input */
    static Scanner sc = new Scanner(System.in);

    /**
     * Compares two cards and determines which has a higher value.
     * In the War card game, Ace is low (value 1) unless comparing against a 2,
     * in which case the 2 wins.
     *
     * @param cardA the first card to compare
     * @param cardB the second card to compare
     * @return the card with the higher value, or null if the cards tie
     */
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

    /**
     * Creates a deck of cards with a specified number of ranks per suit.
     * The deck includes all four suits (Heart, Diamond, Club, Spade) with
     * ranks from Ace through the specified limit.
     *
     * @param rankLimit the maximum rank to include (2-13, where 13 = King).
     *                  Values outside this range are clamped to valid bounds.
     * @return a Stack containing all cards in the deck
     */
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

    /**
     * Shuffles a deck of cards using a riffle shuffle algorithm.
     * The deck is split at a random point and the two halves are
     * interleaved. This process is repeated 5 times for thorough shuffling.
     *
     * @param deck the Stack of cards to shuffle (modified in place)
     */
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

    /**
     * Prints the full ASCII art display of each card in the deck.
     * Each card's visual representation is printed on separate lines.
     *
     * @param deck the Stack of cards to display
     */
    public static void printDeck(Stack<Card> deck) {
        for (Card card : deck) {
            System.out.println(card.display);
        }
    }

    /**
     * Prints a compact representation of the deck showing rank and suit
     * abbreviations.
     * Cards are displayed as "rank suit_abbreviation" (e.g., "A H", "K S").
     * All cards are printed on a single line separated by " | ".
     *
     * @param deck the Stack of cards to display in compact format
     */
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

    /**
     * Clears the console screen using platform-specific commands.
     * On Windows systems, uses "cls" command. On other systems, uses ANSI escape
     * codes.
     *
     */
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

    /**
     * Displays both players' top cards and their remaining deck sizes.
     * Shows the ASCII art of each player's top card side by side, along with
     * visual representations of their remaining cards as "[?]" symbols.
     * Card backs are displayed in rows of 5 for better formatting.
     * This method uses only Stack data structures for all operations.
     *
     * @param player1Deck the first player's deck of cards
     * @param player2Deck the second player's deck of cards
     */
    public static void PrintBothCards(Stack<Card> player1Deck, Stack<Card> player2Deck) {

        // Use stacks to store the display lines for each card
        Stack<String> card1Lines = new Stack<String>();
        Stack<String> card2Lines = new Stack<String>();

        // Split card displays and push to stacks (reverse order to maintain proper
        // sequence)
        String[] card1Display = player1Deck.peek().display.split("\n");
        String[] card2Display = player2Deck.peek().display.split("\n");

        for (int i = card1Display.length - 1; i >= 0; i--) {
            card1Lines.push(card1Display[i]);
        }
        for (int i = card2Display.length - 1; i >= 0; i--) {
            card2Lines.push(card2Display[i]);
        }

        // Build card back representations using stacks
        Stack<String> player1CardBacks = new Stack<String>();
        Stack<String> player2CardBacks = new Stack<String>();

        // Create card back lines for player 1
        StringBuilder currentLine = new StringBuilder();
        for (int i = 0; i < player1Deck.size(); i++) {
            currentLine.append("[?]");
            if ((i + 1) % 5 == 0 && i != player1Deck.size() - 1) {
                player1CardBacks.push(currentLine.toString());
                currentLine = new StringBuilder();
            }
        }
        if (currentLine.length() > 0) {
            player1CardBacks.push(currentLine.toString());
        }

        // Create card back lines for player 2
        currentLine = new StringBuilder();
        for (int i = 0; i < player2Deck.size(); i++) {
            currentLine.append("[?]");
            if ((i + 1) % 5 == 0 && i != player2Deck.size() - 1) {
                player2CardBacks.push(currentLine.toString());
                currentLine = new StringBuilder();
            }
        }
        if (currentLine.length() > 0) {
            player2CardBacks.push(currentLine.toString());
        }

        // Reverse the card back stacks to get proper order
        Stack<String> player1CardBacksReversed = new Stack<String>();
        Stack<String> player2CardBacksReversed = new Stack<String>();

        while (!player1CardBacks.isEmpty()) {
            player1CardBacksReversed.push(player1CardBacks.pop());
        }
        while (!player2CardBacks.isEmpty()) {
            player2CardBacksReversed.push(player2CardBacks.pop());
        }

        // Add card back lines to the main display stacks
        while (!player1CardBacksReversed.isEmpty() || !player2CardBacksReversed.isEmpty()) {
            String line1 = player1CardBacksReversed.isEmpty() ? "" : player1CardBacksReversed.pop();
            String line2 = player2CardBacksReversed.isEmpty() ? "" : player2CardBacksReversed.pop();
            card1Lines.push(line1);
            card2Lines.push(line2);
        }

        // Display all lines by popping from stacks (this reverses them back to correct
        // order)
        Stack<String> displayStack1 = new Stack<String>();
        Stack<String> displayStack2 = new Stack<String>();

        while (!card1Lines.isEmpty()) {
            displayStack1.push(card1Lines.pop());
        }
        while (!card2Lines.isEmpty()) {
            displayStack2.push(card2Lines.pop());
        }

        while (!displayStack1.isEmpty() || !displayStack2.isEmpty()) {
            String line1 = displayStack1.isEmpty() ? "" : displayStack1.pop();
            String line2 = displayStack2.isEmpty() ? "" : displayStack2.pop();
            System.out.printf("%-20s %s%n", line1, line2);
        }
    }

    /**
     * Moves all cards from the source stack to the destination stack.
     * Cards are moved one at a time, preserving their order (last in, first out).
     * The source stack will be empty after this operation.
     *
     * @param source      the stack to move cards from (will be emptied)
     * @param destination the stack to move cards to
     */
    public static void emptyCardsIntoStack(Stack<Card> source, Stack<Card> destination) {
        while (!source.isEmpty()) {
            destination.push(source.pop());
        }
    }

    /**
     * Displays a message to the user and waits for input.
     * Optionally clears the console after receiving input.
     *
     * @param message         the message to display to the user (can be null or
     *                        empty)
     * @param clearAfterInput if true, clears the console after receiving input
     * @return the user's input as a trimmed string
     */
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
