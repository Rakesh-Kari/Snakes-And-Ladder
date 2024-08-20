public class Cell {
    private Jump jump;
    private int position;

    public Cell(int position) {
        this.position = position;
        this.jump = null;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }

    public Jump getJump() {
        return jump;
    }

    public boolean hasJump() {
        return jump != null;
    }

    public String toString() {
        return "Cell " + position + (hasJump() ? " with " + jump : " ");
    }
}
