/*
 * Written by Lance Kimani
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Board {
    private char[][] board;

    public Board(String filename) {
        board = new char[10][10]; // Initialize the board array
        loadBoard(filename);
    }

    private void loadBoard(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 10) {
                if (line.length() >= 10) {
                    for (int col = 0; col < 10; col++) {
                        board[row][col] = line.charAt(col);
                    }
                    row++;
                } else {
                    throw new IOException("Invalid board file format.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading board: " + e.getMessage());
        }
    }

    public void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public char getCell(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return board.length;
    }
}
