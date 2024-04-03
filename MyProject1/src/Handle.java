/**
 * This is the Handle class. It
 * represents an array class that
 * will be used with memory pool and
 * hashtable to store records.
 * 
 * @author aalbro, connorm20
 * @version 08.28.2023
 */
public class Handle {
    private int start;
    private int length;

    /**
     * This is the main method
     * of the Handle class.
     * 
     * @param startIndex
     *            the starting
     *            position of the record in the
     *            memory pool.
     * @param len
     *            the length of the
     *            record.
     */
    public Handle(int startIndex, int len) {
        this.start = startIndex;
        this.length = len;
    }


    /**
     * This is the getter method
     * of getting the starting position
     * of the handle in the memory pool.
     * 
     * @return the starting index.
     */
    public int getStartingIndex() {
        return this.start;
    }


    /**
     * This gets the length of the
     * record.
     * 
     * @return the length of the record.
     */
    public int getLength() {
        return this.length;
    }


    public int getEnd() {
        // TODO Auto-generated method stub
        return 0;
    }
}
