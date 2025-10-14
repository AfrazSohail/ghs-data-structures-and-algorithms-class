import java.util.Stack;

public class War {

    private Stack<Card> makeDeck(int rankLimit) {
        Stack<Card> deck = Dealer.makeDeck(rankLimit);
        Dealer.shuffleDeck(deck);
        return deck;
    }

    private Stack<Card> player1Cards = new Stack<Card>();
    private Stack<Card> player1WinPile = new Stack<Card>();

    private Stack<Card> player2Cards = new Stack<Card>();
    private Stack<Card> player2WinPile = new Stack<Card>();

    private Stack<Card> tiePile = new Stack<Card>();

    public War(int rankLimit) {
        Stack<Card> deck = makeDeck(rankLimit);
        Dealer.printDeckMini(deck);

        distributeCards(deck);
    }

    private void distributeCards(Stack<Card> deck) {
        while (!deck.isEmpty()) {
            player1Cards.push(deck.pop());
            if (!deck.isEmpty()) {
                player2Cards.push(deck.pop());
            }
        }
    }

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
