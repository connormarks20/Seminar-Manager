import student.TestCase;

/**
 * This is the test class for the
 * WorldDataBase class.It will test
 * all of it's methods and it's constructor.
 * 
 * @author connorm20, aalbro
 * @version 09.04.2023
 */
public class WorldDataBaseTest extends TestCase {
    private HashTable table = new HashTable(16);
    private MemoryManager manage = new MemoryManager(256);
    private WorldDataBase base = new WorldDataBase(table, manage);

    /**
     * This is the place to
     * initialize and setup things
     * in this test class.
     */
    public void setUp() {
        // nothing here.
    }


    /**
     * This tests the seminarInsert method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testSeminarInsert() throws Exception {
        int iD = 1;
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present" + " an overview of HCI research at VT");
        base.insertSeminar(iD, sem);
        base.searchID(iD);
        assertTrue(table.search(iD));
    }
}
