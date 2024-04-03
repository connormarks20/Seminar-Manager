import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import student.TestCase;
import java.io.IOException;

/**
 * This is the CommandProcessorTest class. It tests the
 * commands and constructor for the class.
 * 
 * @author aalbro, connorm20
 * @version 09.03.2023
 */
public class CommandProcessorTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // nothing here
    }


    /**
     * This method tests the processCommand method
     * when the file is empty.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testEmptyFile() throws Exception {
        String file = "EmptyFile.txt";
        HashTable hash = new HashTable(16);
        MemoryManager mem = new MemoryManager(256);
        CommandProcessor processor = new CommandProcessor(file, hash, mem);
        processor.processCommand(file, null);
        assertEquals(systemOut().getHistory(), "");
    }


    /**
     * This tests the processCommand method
     * when there is a non empty file.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testNonEmptyFile() throws Exception {
        String file = "file.txt";
        HashTable hash = new HashTable(16);
        MemoryManager mem = new MemoryManager(256);
        CommandProcessor processor = new CommandProcessor(file, hash, mem);
        processor.processCommand(file, null);
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 102781824, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present "
                + "an overview of HCI research at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, " + "Virginia_Tech\r\n"
                + "Size: 172\r\n" + "");
    }


    /**
     * This tests the process command method.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testProcessCommand() throws Exception {
        String file = "testFile.txt";
        HashTable hash = new HashTable(16);
        MemoryManager mem = new MemoryManager(256);
        CommandProcessor processor = new CommandProcessor(file, hash, mem);
        processor.processCommand(file, null);
        assertEquals(systemOut().getHistory(),
            "Successfully inserted record with ID 3\r\n"
                + "ID: 3, Title: Overview of HPC and CSE Research at VT\r\n"
                + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\r\n"
                + "Description: Learn what kind of"
                + "    research is done on HPC  and CSE at VT\r\n"
                + "Keywords: HPC, CSE, computer_science\r\n" + "Size: 168\r\n"
                + "Found record with ID 3:\r\n"
                + "ID: 3, Title: Overview of HPC and CSE Research at VT\r\n"
                + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\r\n"
                + "Description: Learn what kind of"
                + "    research is done on HPC  and CSE at VT\r\n"
                + "Keywords: HPC, CSE, computer_science\r\n"
                + "Record with ID 3 successfully "
                + "deleted from the database\r\n" + "Hashtable:\r\n"
                + "3: TOMBSTONE\n" + "total records: 0\n");
    }


    /**
     * This will test the print branch
     * in the switch statement within
     * the class.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testProcessCommand2() throws Exception {
        String file = "printhashtable.txt";
        HashTable hash = new HashTable(16);
        MemoryManager mem = new MemoryManager(256);
        CommandProcessor processor = new CommandProcessor(file, hash, mem);
        processor.processCommand(file, null);
        assertEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "total records: 0\n"
            + "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 102781824, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will"
            + " present an overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 172\r\n" + "Hashtable:\r\n" + "1: 1\n"
            + "total records: 1\n");

    }


    /**
     * tests the hashtable command
     * 
     * @throws Exception
     *             if there's an error.
     */
    public void testPrintHashTableCommand() throws Exception {

        String file = "printhashtable.txt";

        HashTable hash = new HashTable(16);

        MemoryManager mem = new MemoryManager(16);

        CommandProcessor processor = new CommandProcessor(file, hash, mem);

        processor.processCommand(file, null);

        assertEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "total records: 0\n"
            + "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 102781824, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview "
            + "of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 172\r\n" + "Hashtable:\r\n" + "1: 1\n"
            + "total records: 1\n");
    }
}
