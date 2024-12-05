/*
 * Written by Lance Kimani
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RobotSimulator {
    private Board board;
    private int x, y;

    public RobotSimulator(Board board) {
        this.board = board;
        this.x = 0;
        this.y = 0;
    }

    public void simulateCommands(CommandQueue<String> commands) {
        while (!commands.isEmpty()) {
            String command = commands.dequeue();
            moveRobot(command);
            if (x < 0 || x >= board.getSize() || y < 0 || y >= board.getSize() ||
                board.getCell(x, y) == 'X') {
                System.out.println("CRASH");
                return;
            }
            displayBoardWithRobot();
        }
        System.out.println("All commands executed successfully.");
    }

    private void moveRobot(String command) {
        switch (command) {
            case "Move Up":
                x--;
                break;
            case "Move Down":
                x++;
                break;
            case "Move Left":
                y--;
                break;
            case "Move Right":
                y++;
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

    private void displayBoardWithRobot() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (i == x && j == y) {
                    System.out.print("R ");
                } else {
                    System.out.print(board.getCell(i, j) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board file name: ");
        String boardFile = scanner.nextLine();
        Board board = new Board(boardFile);
        board.displayBoard();
        System.out.print("Enter robot command file name: ");
        String commandFile = scanner.nextLine();
        CommandQueue<String> commands = new CommandQueue<>();
        try (Scanner fileScanner = new Scanner(new FileReader(commandFile))) {
            while (fileScanner.hasNextLine()) {
                commands.enqueue(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading command file: " + e.getMessage());
        }
        RobotSimulator simulator = new RobotSimulator(board);
        simulator.simulateCommands(commands);
        scanner.close();
    }
}
