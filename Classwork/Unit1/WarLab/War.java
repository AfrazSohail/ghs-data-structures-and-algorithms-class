package WarLab;

import java.util.Stack;

/**
 * Implements the War card game logic and gameplay mechanics.
 * This class manages the game state, player decks, and handles battles between
 * players.
 * The War card game is played between two players who each have a deck of
 * cards.
 * Players reveal their top cards simultaneously, and the player with the higher
 * card wins both cards. In case of a tie, a "war" occurs where players put down
 * additional cards before the next comparison.
 *
 * DOCUMENTATION DONE BY AI---Claude Sonnet 4
 *
 * @author Afraz Sohail
 */
public class War {

    /**
     * Creates and shuffles a deck with the specified rank limit.
     *
     * @param rankLimit the maximum rank to include in the deck
     * @return a shuffled Stack of cards
     */
    private Stack<Card> makeDeck(int rankLimit) {
        Stack<Card> deck = Dealer.makeDeck(rankLimit);
        Dealer.shuffleDeck(deck);
        return deck;
    }

    /** Player 1's current hand of cards */
    private Stack<Card> player1Cards = new Stack<Card>();

    /** Player 1's pile of won cards */
    private Stack<Card> player1WinPile = new Stack<Card>();

    /** Player 2's current hand of cards */
    private Stack<Card> player2Cards = new Stack<Card>();

    /** Player 2's pile of won cards */
    private Stack<Card> player2WinPile = new Stack<Card>();

    /** Pile of cards involved in tie situations (wars) */
    private Stack<Card> tiePile = new Stack<Card>();

    /**
     * Constructs a new War game with the specified number of ranks per suit.
     * Creates and shuffles a deck, then distributes cards evenly between the two
     * players.
     *
     * @param rankLimit the maximum rank to include in the deck (2-13)
     */
    public War(int rankLimit) {
        Stack<Card> deck = makeDeck(rankLimit);
        Dealer.printDeckMini(deck);

        distributeCards(deck);
    }

    /**
     * Distributes cards from the deck alternately between the two players.
     * Player 1 gets the first card, Player 2 gets the second, and so on.
     *
     * @param deck the deck of cards to distribute
     */
    private void distributeCards(Stack<Card> deck) {
        while (!deck.isEmpty()) {
            player1Cards.push(deck.pop());
            if (!deck.isEmpty()) {
                player2Cards.push(deck.pop());
            }
        }
    }

    /**
     * Plays one round of the War card game.
     * Handles user input, displays game state, and processes the battle between
     * players.
     *
     * @return true if the game should continue, false if the game is over
     */
    public boolean playRound() {

        String input = Dealer.interject("Press Enter to play the next round or type 'quit' to exit:", true);
        if (input.equals("quit")) {
            System.out.println("Game ended by user.");
            return false;
        }

        if (!checkCardsAll()) {
            return false;
        }

        Dealer.PrintBothCards(player1Cards, player2Cards);
        Dealer.interject("", false);
        if (!handleBattle()) {
            return false;
        }
        return true;

    }

    /**
     * Checks if both players have cards available to continue playing.
     * If a player is out of cards but has a win pile, moves the win pile to their
     * hand.
     *
     * @return true if both players can continue, false if one or both players are
     *         out of cards
     */
    private boolean checkCardsAll() {
        if (!checkCards(player1Cards, player1WinPile) && !checkCards(player2Cards, player2WinPile)) {
            System.out.println("Both players are out of cards! It's a draw!");
            return false;
        } else if (!checkCards(player1Cards, player1WinPile)) {
            System.out.println("Player 1 is out of cards! Game over!");
            return false;
        } else if (!checkCards(player2Cards, player2WinPile)) {
            System.out.println("Player 2 is out of cards! Game over!");
            return false;
        }
        return true;
    }

    /**
     * Checks if a player has cards available and manages their win pile.
     * If the player's hand is empty but they have cards in their win pile,
     * moves all cards from the win pile to their hand.
     *
     * @param playerCards   the player's current hand
     * @param playerWinPile the player's pile of won cards
     * @return true if the player has cards available, false if completely out of
     *         cards
     */
    private boolean checkCards(Stack<Card> playerCards, Stack<Card> playerWinPile) {
        if (playerCards.isEmpty()) {
            if (playerWinPile.isEmpty()) {
                return false;
            } else {
                System.out.println("Player is out of cards, adding Win Pile to deck.");
                // Dealer.printDeckMini(playerCards);
                // Dealer.printDeckMini(playerWinPile);
                Dealer.emptyCardsIntoStack(playerWinPile, playerCards);
            }
        }
        return true;
    }

    /**
     * Handles the battle between players' top cards.
     * Compares the top cards of both players and determines the winner.
     * In case of a tie, initiates a "war" by placing additional cards in the tie
     * pile.
     * The winner takes all cards involved in the battle.
     *
     * @return true if the battle was completed successfully, false if the game
     *         should end
     */
    private boolean handleBattle() {

        Card player1TopCard = player1Cards.peek();
        Card player2TopCard = player2Cards.peek();

        Dealer.clearConsole();
        Dealer.PrintBothCards(player1Cards, player2Cards);

        Card higherCard = Dealer.GetHigherCard(player1TopCard, player2TopCard);
        if (higherCard == player1TopCard) {
            System.out.println("Player 1 wins the round!");
            player1WinPile.push(player1Cards.pop());
            player1WinPile.push(player2Cards.pop());
            Dealer.emptyCardsIntoStack(tiePile, player1WinPile);
            if (!checkCardsAll()) {
                return false;
            }
        } else if (higherCard == player2TopCard) {
            System.out.println("Player 2 wins the round!");
            player2WinPile.push(player1Cards.pop());
            player2WinPile.push(player2Cards.pop());
            Dealer.emptyCardsIntoStack(tiePile, player2WinPile);
            if (!checkCardsAll()) {
                return false;
            }
        } else {
            if (tiePile.isEmpty()) {
                System.out.println("We've entered war! Each player puts down three cards.");
            }

            tiePile.push(player1Cards.pop());
            tiePile.push(player2Cards.pop());
            if (!checkCardsAll()) {
                return false;
            }

            tiePile.push(player1Cards.pop());
            tiePile.push(player2Cards.pop());
            if (!checkCardsAll()) {
                return false;
            }

            Dealer.interject("", false);
        }
        return true;
    }
}
