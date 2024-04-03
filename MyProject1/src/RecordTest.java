import student.TestCase;

/**
 * This class tests the record class
 * and all its methods and constructor.
 * 
 * @author Andrew Albro (aalbro)
 * @author Connor Marks (connorm20)
 * @version 09/02/2023
 */
public class RecordTest extends TestCase {

    private Record<Integer, String> record = new Record<>(1, "Value");

    /**
     * This sets up the record test class
     * by initializing a record.
     */
    public void setUp() {
        // nothing needs to go here.
    }


    /**
     * This tests the getKey method.
     */
    public void testGetKey() {
        assertEquals(record.getKey().intValue(), 1);
    }


    /**
     * This tests the getValue method.
     */
    public void testGetValue() {
        assertEquals("Value", record.getValue());
    }


    /**
     * This tests the setValue method.
     */
    public void testSetValue() {
        record.setValue("NewValue");
        assertEquals("NewValue", record.getValue());
    }


    /**
     * This tests the toString method.
     */
    public void testToString() {
        assertEquals("Record{key=1, value=Value}", record.toString());
    }
}
