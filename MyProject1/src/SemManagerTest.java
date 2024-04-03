import student.TestCase;

/**
 * @author aalbro, connorm20
 * @version 08.25.2023
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testMInitx() throws Exception {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        SemManager.main(null);
    }


    /**
     * This tests the power of two
     * method.
     */
    public void testPowerOfTwo() {
        assertFalse(SemManager.powerOfTwo(3));
        assertTrue(SemManager.powerOfTwo(2));
        assertFalse(SemManager.powerOfTwo(10));
        assertTrue(SemManager.powerOfTwo(8));
        assertFalse(SemManager.powerOfTwo(22));
        assertTrue(SemManager.powerOfTwo(32));
    }


    /**
     * This tests when the arguments
     * are empty and also nonempty
     * but still not the right amount.
     * 
     * @throws Exception
     */
    public void testEmptyArgs() throws Exception {
        String[] args = null;
        SemManager.main(args);
        assertEquals(systemOut().getHistory(), "");
    }


    /**
     * This tests the main method
     * when there are only two arguments.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testTwoArgs() throws Exception {
        String[] args2 = { "16", "16" };
        SemManager.main(args2);
        assertEquals(systemOut().getHistory(), "Not enough arguments");
    }


    /**
     * This tests the main method
     * when the first argument isn't
     * a power of two.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testNotPowerOfTwo() throws Exception {
        String[] args = { "17", "16", "P1Sample_input.txt" };
        SemManager.main(args);
        assertEquals(systemOut().getHistory(), "The memory size needs "
            + "to be a power of two.");
    }


    /**
     * This tests the main method
     * when the second argument isn't
     * a power of two.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testNotPowerOfTwo2() throws Exception {
        String[] args = { "16", "17", "P1Sample_input.txt" };
        SemManager.main(args);
        assertEquals(systemOut().getHistory(), "The Hash Table needs "
            + "to be a power of two.");
    }


    /**
     * This method tests the main
     * method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testMain() throws Exception {
        String[] args = { "16", "16", "file.txt" };
        SemManager.main(args);
        assertEquals(systemOut().getHistory(), "Successfully"
            + " inserted record with ID 1\r\n" + "ID: 1, Title: Overview of "
            + "HCI Research at VT\r\n" + "Date: 102781824, Length: 90,"
            + " X: 10, Y: 10, Cost: 45\r\n" + "Description: This seminar will "
            + "present an overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 172\n");
    }
}
