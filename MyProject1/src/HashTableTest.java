import student.TestCase;

/**
 * This is the tests class for the
 * HashTable class that will
 * test the classes methods and constructor.
 * 
 * @author connorm20, aalbro
 * @version 09.03.2023
 */
public class HashTableTest extends TestCase {
    private HashTable table = new HashTable(10);

    /**
     * This sets up the test class.
     */
    public void setUp() {
        // Nothing to do here.
    }


    /**
     * This tests the insert and
     * the search method.
     * 
     * @throws Exception
     */
    public void testInsertAndSearch() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(1, sem);
        assertTrue(table.search(1));
        Seminar sem2 = new Seminar(11, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present" + " an overview of HCI research at VT");
        table.insert(11, sem2);
        assertTrue(table.search(11));
    }


    /**
     * This tests the delete method.
     * 
     * @throws Exception
     */
    public void testDelete() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present" + " an overview of HCI research at VT");
        table.insert(1, sem);
        table.delete(1);
        assertFalse(table.search(1));
    }


    /**
     * This tests the linear probing
     * method.
     */
    public void testLinearProbing() {
        int iD = 1;
        assertEquals(table.linearProbing(iD, table.getSize()), 1);
        int iD2 = 11;
        assertEquals(table.linearProbing(iD2, table.getSize()), 3);
        int iD3 = 0;
        assertEquals(table.linearProbing(iD3, table.getSize()), 1);
    }


    /**
     * This tests the linearProbing
     * method.
     */
    public void testLinearProbing2() {
        double iD = 1.5;
        assertEquals(table.linearProbing((int)iD, table.getSize()), 1);
        int iD2 = -1;
        assertEquals(table.linearProbing(iD2, table.getSize()), 1);
    }


    /**
     * This tests the expand method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testExpand() throws Exception {
        HashTable hash = new HashTable(4);
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present" + " an overview of HCI research at VT");
        hash.insert(1, sem);
        hash.expand();
        assertEquals(hash.getSize(), 8);

    }


    /**
     * This tests the getSize method.
     */
    public void testGetSize() {
        HashTable hash = new HashTable(4);
        assertEquals(hash.getSize(), 4);
    }


    /**
     * This tests the serach1 method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testSearch1() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(1, sem);
        assertTrue(table.search1(1));
        assertFalse(table.search1(2));
        table.delete(1);
        assertFalse(table.search1(1));
        // table.insert(-1, sem);
        assertFalse(table.search1(-1));

    }


    /**
     * tests the linear probing method in hashtable
     * 
     * @throws Exception
     *             if there's an error
     */
    public void testLinearProbing3() throws Exception {

        HashTable hashTable = new HashTable(8);

        // Test the linearProbing method with various IDs and table lengths
        // For each test case, calculate the expected result manually
        int result1 = hashTable.linearProbing(6, 8);
        int result2 = hashTable.linearProbing(7, 8);
        int result3 = hashTable.linearProbing(15, 8);

        assertEquals(1, result1);
        assertEquals(1, result2);
        assertEquals(3, result3);

    }


    /**
     * This tests for when we search on
     * an empty table.
     */
    public void testEmptyTable() {
        int iD = 0;
        HashTable hash = new HashTable(16);
        assertFalse(hash.search(iD));
        assertEquals(systemOut().getHistory(),
            "Search FAILED -- There is no record with ID " + iD + "\n");
    }


    /**
     * This tests for when we delete
     * from an empty table.
     */
    public void testEmptyTable2() {
        int iD = 0;
        HashTable hash = new HashTable(16);
        hash.delete(iD);
        assertEquals(systemOut().getHistory(),
            "Delete FAILED -- There is no record with ID " + iD + "\n");
    }


    /**
     * This tests the print table method
     * when the table is empty.
     */
    public void testPrintTable() {
        table.printTable();
        assertEquals(systemOut().getHistory(), "Hashtable:\n"
            + "total records: 0\n");
    }


    /**
     * This tests the print table method
     * when the table has one element in it.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testPrintTable2() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(0, sem);
        table.printTable();
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 0\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present "
                + "an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n" + "Hashtable:\r\n" + "0: 0\n"
                + "total records: 1\n");
    }


    /**
     * This tests the print table method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testPrintTable3() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(0, sem);
        table.delete(0);
        table.printTable();
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 0\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will "
                + "present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n" + "Record with ID 0 successfully "
                + "deleted from the database\r\n" + "Hashtable:\r\n"
                + "0: TOMBSTONE\n" + "total records: 0\n");
    }


    /**
     * This tests the insert method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testInsert() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        Seminar sem2 = new Seminar(2, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(1, sem);
        table.insert(1, sem);
        table.insert(2, sem2);
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present "
                + "an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n"
                + "Insert FAILED - There is already a record with ID 1\r\n"
                + "Successfully inserted record with ID 2\r\n"
                + "ID: 2, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present "
                + "an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\n");

    }


    /**
     * This tests the insert command.
     * 
     * @throws Exception
     *             if there is an error
     */
    public void testInsert2() throws Exception {
        HashTable tab = new HashTable(4);
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        tab.insert(1, sem);
        tab.search1(1);
        assertEquals(tab.getSize(), 4);
    }


    /**
     * This tests the search1 method.
     */
    public void testSearch12() {
        assertFalse(table.search(20));
        assertFalse(table.search(-10));
    }


    /**
     * This tests the linear probing method
     */
    public void testLinearProbing4() {
        HashTable tab = new HashTable(16);
        assertEquals(tab.linearProbing(0, tab.getSize()), 1);
        HashTable tab2 = new HashTable(2);
        // assertEquals(tab2.linearProbing(1, 1), 1);
    }


    /**
     * This tests the expand method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testExpand2() throws Exception {
        HashTable tab = new HashTable(0);
        table.expand();
        assertEquals(tab.getSize(), 0);
        HashTable tab2 = new HashTable(2);
        Seminar sem = new Seminar(0, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        Seminar sem2 = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        tab2.insert(0, sem);
        tab2.insert(1, sem2);
        assertEquals(tab2.getSize(), 4);
    }


    /**
     * This tests the delete method.
     */
    public void testDelete2() {
        HashTable tab = new HashTable(2);
        tab.delete(0);
        tab.delete(100);
        assertEquals(systemOut().getHistory(), "Delete FAILED -- There"
            + " is no record with ID 0\r\n"
            + "Delete FAILED -- There is no record with ID 100\n");
    }


    /**
     * This tests the delete method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testDelete3() throws Exception {
        HashTable tab = new HashTable(4);
        Seminar sem = new Seminar(0, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        tab.insert(0, sem);
        tab.delete(0);
        tab.delete(0);
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 0\r\n"
                + "ID: 0, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will "
                + "present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n"
                + "Record with ID 0 successfully deleted from the database\r\n"
                + "Delete FAILED -- There is no record with ID 0\n");
    }


    /**
     * This tests the linear probing method
     * for when the length is zero.
     */
    public void testLinearProbe() {
        assertEquals(table.linearProbing(1, 0), 0);
    }


    /**
     * This tests the linear probing method
     * for when the length is non negative and
     * not zero.
     */
    public void testLinearProbe3() {
        assertEquals(table.linearProbing(2, 2), 1);
    }


    /**
     * This tests the linear probing method
     * for when the iD is zero.
     */
    public void testLinearProbe4() {
        assertEquals(table.linearProbing(0, 2), 1);
    }


    /**
     * This tests the insert method when
     * we insert into a tombstone.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testInsert3() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(1, sem);
        table.delete(1);
        table.insert(1, sem);
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n"
                + "Record with ID 1 successfully deleted from the database\r\n"
                + "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n" + "");
    }


    /**
     * This tests for the insert method when
     * the homeslot is not null and there
     * is a tombstone in the probed spot.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testInsert4() throws Exception {
        Seminar sem = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] {
                "HCI Computer_Science VT Virginia_Tech" },
            "This seminar will present " + "an overview of HCI research at VT");
        table.insert(1, sem);
        table.insert(4, sem);
        table.delete(4);
        table.insert(11, sem);
        table.printTable();
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n" + "Successfully inserted record with ID 4\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n"
                + "Record with ID 4 successfully deleted from the database\r\n"
                + "Successfully inserted record with ID 11\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI research at VT\r\n"
                + "Keywords: HCI Computer_Science VT Virginia_Tech\r\n"
                + "Size: 170\r\n" + "Hashtable:\r\n" + "1: 1\r\n" + "4: 11\r\n"
                + "total records: 2\r\n" + "");
    }
}
