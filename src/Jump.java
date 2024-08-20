public class Jump {

    int start;
    int end;

    public Jump(int start, int end) {
        if(start == end) {
            throw new IllegalArgumentException("Start and end position cannot be same");
        }
        this.start = start;
        this.end = end;
    }

    public void setStart(int start) {
        if(start == this.end) {
            throw new IllegalArgumentException("Start and end position cannot be same");
        }
        this.start = start;
    }

    public void setEnd(int end) {
        if(end == this.start) {
            throw new IllegalArgumentException("Start and end position cannot be same");
        }
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public boolean isSnake() {
        return start > end;
    }

    public boolean isLadder() {
        return start < end;
    }

    public int getEnd() {
        return end;
    }
}
