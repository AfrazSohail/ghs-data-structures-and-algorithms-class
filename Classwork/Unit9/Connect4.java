import java.util.*;

public class Connect4 {

	int ROWS;
	int COLS;

	char EMPTY = '.';
	char AI = 'X';
	char HUMAN = 'O';
	char[][] board;

	public Connect4(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		board = new char[ROWS][COLS];
		this.initBoard();
	}

	// Initialize board
	public void initBoard() {
		for (int r = 0; r < ROWS; r++) {
			Arrays.fill(board[r], EMPTY);
		}
	}


	// Move validation
	boolean isValidMove(int col) {
		return col >= 0 && col < COLS && board[0][col] == EMPTY;
	}

	// Drop piece
	int dropPiece(int col, char player) {
		for (int row = ROWS - 1; row >= 0; row--) {
			if (board[row][col] == EMPTY) {
				board[row][col] = player;
				return row;
			}
		}
		return -1;
	}



	// Terminal check
	char isGameOver() {
		if (checkWin(AI) == AI)
			return AI;
		if (checkWin(HUMAN) == HUMAN)
			return HUMAN;

		return isFull() ? 'D' : 0;
	}

	boolean isFull() {
		for (int c = 0; c < COLS; c++) {
			if (board[0][c] == EMPTY)
				return false;
		}
		return true;
	}

	// Win detection
	char checkWin(char player) {// Row 0 is the top row

		// Horizontal
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS - 3; c++)
				if (board[r][c] == player && board[r][c + 1] == player && board[r][c + 2] == player
						&& board[r][c + 3] == player)
					return player;

		// Vertical
		for (int c = 0; c < COLS; c++)
			for (int r = 0; r < ROWS - 3; r++)
				if (board[r][c] == player && board[r + 1][c] == player && board[r + 2][c] == player
						&& board[r + 3][c] == player)
					return player;

		// Diagonal /
		for (int r = 3; r < ROWS; r++)
			for (int c = 0; c < COLS - 3; c++)
				if (board[r][c] == player && board[r - 1][c + 1] == player && board[r - 2][c + 2] == player
						&& board[r - 3][c + 3] == player)
					return player;

		// Diagonal \
		for (int r = 0; r < ROWS - 3; r++)
			for (int c = 0; c < COLS - 3; c++)
				if (board[r][c] == player && board[r + 1][c + 1] == player && board[r + 2][c + 2] == player
						&& board[r + 3][c + 3] == player)
					return player;

		return 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Column labels
		sb.append(" ");
		for (int c = 0; c < COLS; c++) {
			sb.append(c).append(" ");
		}
		sb.append("\n");

		// Board rows
		for (int r = 0; r < ROWS; r++) {
			sb.append("|");
			for (int c = 0; c < COLS; c++) {
				sb.append(board[r][c]).append("|");
			}
			sb.append("\n");
		}

		// Bottom border
		sb.append(" ");
		for (int c = 0; c < COLS; c++) {
			sb.append("- ");
		}
		sb.append("\n");

		return sb.toString();
	}

    public int getBestMove() {
        // return (int)(Math.random()*COLS);

        int bestEval = Integer.MIN_VALUE;
        int bestCol = -1;
        for (int col = 0; col < COLS; col++) {
            if (isValidMove(col)) {
                bestCol = col;
                break;
            }
        }
        int depth = 5;
        for (int col = 0; col < COLS; col++) {
            int row = dropPiece(col, AI);
            int eval = minimax(depth, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (eval > bestEval) {
                bestEval = eval;
                bestCol = col;
            }
            board[row][col] = EMPTY;
        }
        return bestCol;
    }

    private int minimax(int depth, boolean isMax, int alpha, int beta) {
        if (depth <= 0 || isGameOver() != 0) {
            return evaluate();
        }
        if (isMax) {
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < COLS; col++) {
                if (isValidMove(col)) {
                    int row = dropPiece(col, AI);
                    int eval = minimax(depth - 1, false, alpha, beta);
                    maxEval = Math.max(maxEval, eval);
                    board[row][col] = EMPTY;
                    alpha = Math.max(eval, alpha);
                    if (beta <= alpha)
                        break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < COLS; col++) {
                if (isValidMove(col)) {
                    int row = dropPiece(col, HUMAN);
                    int eval = minimax(depth - 1, false, alpha, beta);
                    minEval = Math.min(minEval, eval);
                    board[row][col] = EMPTY;
                    beta = Math.min(beta, eval);
                    if (beta <= alpha)
                        break;
                }
            }
            return minEval;
        }
    }

    private int evaluate() {
        if (isGameOver() == AI) {
            return Integer.MAX_VALUE;
        }
        if (isGameOver() == HUMAN) {
            return Integer.MIN_VALUE;
        }
        if (isGameOver() == 'D') {
            return 0;
        }

        int eval = 0;
        for (int col = COLS / 2 - 1; col <= COLS / 2 + 1; col++) {
            for (int row = 0; row < ROWS; row++) {
                if (board[row][col] == AI || board[row][col] == HUMAN) {
                    if (col == COLS / 2)
                        eval += board[row][col] == AI ? 10 : -10;
                    else
                        eval += board[row][col] == AI ? 8 : -8;
                }
            }
        }
        return eval;
    }
}
