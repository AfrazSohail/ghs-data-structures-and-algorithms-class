import java.util.Scanner;

public class Connect4Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		Connect4 C4 = new Connect4(5,6);

		C4.initBoard();
		System.out.println("Connect 4: You are 'O', AI is 'X'");

		boolean gameOver = false;
		boolean humanTurn = true;

		while (!gameOver) {
			System.out.println(C4);

			if (humanTurn) {
				int col;

				while (true) {
					System.out.print("Enter column: ");
					col = scanner.nextInt();

					if (C4.isValidMove(col)) {
						break;
					}
					System.out.println("Invalid move. Try again.");
				}

				C4.dropPiece(col, C4.HUMAN);

				if (C4.checkWin(C4.HUMAN)==C4.HUMAN) {
					System.out.println(C4);
					System.out.println("You win.");
					gameOver = true;
				}

			} else {
				System.out.println("AI is thinking...");
				int col = C4.getBestMove();
				C4.dropPiece(col, C4.AI);

				if (C4.checkWin(C4.AI)==C4.AI) {
					System.out.println(C4);
					System.out.println("AI wins.");
					gameOver = true;
				}
			}

			if (!gameOver && C4.isFull()) {
				System.out.println(C4);
				System.out.println("It's a draw.");
				gameOver = true;
			}

			humanTurn = !humanTurn;
		}

		scanner.close();

	}

}
