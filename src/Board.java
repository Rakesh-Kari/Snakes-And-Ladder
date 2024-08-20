import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;
    public Board(int size, int numberOfSnakes, int numberOfLadders) {
        initializeCells(size);
        addSnakesAndLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        int position = 0;
        for(int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                cells[i][j] = new Cell(position++);
            }
        }
    }

    private void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders) {
        while(numberOfSnakes > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(0, cells.length* cells.length );
            int snakeTail = ThreadLocalRandom.current().nextInt(0, cells.length*cells.length );
            if(snakeTail >= snakeHead) {
                continue;
            }
            Jump snakeObj = new Jump(snakeHead, snakeTail);
            getCellPosition(snakeHead).setJump(snakeObj);
            numberOfSnakes--;
        }

        while(numberOfLadders > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(0, cells.length* cells.length );
            int ladderEnd = ThreadLocalRandom.current().nextInt(0, cells.length* cells.length );
            if(ladderStart >= ladderEnd) {
                continue;
            }
            Jump ladderObj = new Jump(ladderStart, ladderEnd);
            getCellPosition(ladderStart).setJump(ladderObj);
            numberOfLadders--;
        }
    }

    public Cell getCellPosition(int position) {
        if(position < 0 || position >= cells.length * cells.length) {
            throw new IllegalArgumentException("Position out of bounds: " + position);
        }
        int row = (position) / cells.length;
        int col = (position) % cells.length;
        return cells[row][col];
    }

    public Cell[][] getCells() {
        return cells;
    }
}
