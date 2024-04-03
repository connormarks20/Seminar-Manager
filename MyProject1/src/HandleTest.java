import student.TestCase;

/**
 * This is the testing class
 * for the Handle class.
 * This tests the constructor
 * and methods in the Handle class.
 * 
 * @author aalbro, connorm20
 * @version 08.28.2023
 */
public class HandleTest extends TestCase {
    private Handle hand = new Handle(10, 10);

    /**
     * This is the setUp.
     */
    public void setUp() {
        // nothing to do here...
    }


    /**
     * This tests the starting index method
     */
    public void testGetStartingIndex() {
        assertEquals(hand.getStartingIndex(), 10);
    }


    /**
     * This tests the get length method.
     */
    public void testGetLength() {
        assertEquals(hand.getLength(), 10);
    }
}
