public class FreeBlock {
    private int start;
    private int end;

    public FreeBlock(int start, int end) {
        this.start = start;
        this.end = end;
    }


    public int getStart() {
        return start;
    }


    public int getEnd() {
        return end;
    }


    public int getSize() {
        return end - start + 1;
    }


    public void setEnd(int end) {
        this.end = end;
    }


    public boolean isBuddy(FreeBlock other) {
        // Check if two blocks are buddies
        return this.getSize() == other.getSize() && Math.abs(this.getStart()
            - other.getStart()) == this.getSize();
    }


    public void setStart(int newStart) {
        this.start = newStart;
    }


    public void setSize(int newSize) {
        this.end = this.start + newSize - 1;
    }


   
    // You can add methods to access the previous and next blocks as needed
}
