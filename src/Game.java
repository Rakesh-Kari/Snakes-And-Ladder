import java.util.Deque;
import java.util.LinkedList;

public class Game {

    private Player winner;
    private final Dice dice;
    private final Board board;
    private final Deque<Player> playersList;

    public Game() {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        playersList = new LinkedList<>();
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame() {
        int maxPosition = board.getCells().length * board.getCells().length;
        System.out.println("Printing the maximum position " + maxPosition);
        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            System.out.println("Player turn is: " + playerTurn.getId() + " current position is: " + playerTurn.getCurrentPosition());

            int diceNumbers = dice.rollDice();
            int playerNewPosition = diceNumbers + playerTurn.getCurrentPosition();

            System.out.println("Player new position is: " + playerNewPosition);
            if (playerNewPosition > maxPosition) {
                System.out.println("Player " + playerTurn.getId() + " New position exceeds maximum. Staying at position: " + playerTurn.getCurrentPosition());
                continue;
            }

            if (playerNewPosition == maxPosition) {
                winner = playerTurn;
                System.out.println("The winner is: " + winner.getId());
                return;
            }

            playerNewPosition = jumpCheck(playerNewPosition);

            if (playerNewPosition > maxPosition) {
                playerNewPosition = maxPosition;
            }

            playerTurn.setCurrentPosition(playerNewPosition);
            System.out.println("Player " + playerTurn.getId() + " New position is: " + playerNewPosition);

            // Check for winner
            if (playerNewPosition == maxPosition) {
                winner = playerTurn;
                System.out.println("The winner is: " + winner.getId());
            }
        }
    }

    private int jumpCheck(int playerNewPosition) {
        Cell cell = board.getCellPosition(playerNewPosition);
        if (cell.hasJump()) {
            Jump jump = cell.getJump();
            String jumpBy = jump.isLadder() ? "ladder" : "snake";
            System.out.println("Jump done by: " + jumpBy + " from " + jump.getStart() + " to " + jump.getEnd());
            playerNewPosition = jump.getEnd();
        }
        return playerNewPosition;
    }


    private Player findPlayerTurn() {
        Player playerTurn = playersList.removeFirst();
        playersList.add(playerTurn);
        return playerTurn;
    }
}
