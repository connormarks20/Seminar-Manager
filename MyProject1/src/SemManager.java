import java.util.*;
import java.io.File;

/**
 * The class containing the main method.
 *
 * @author Connor Marks (connorm20)
 * @author Andrew Albro (aalbro)
 * @version 8/24/2023
 */
public class SemManager {
    /**
     * @param args
     *            Command line parameters
     * @throws Exception
     */

    /**
     * This is the main method.
     * args[0] is the memory size
     * args[1] is the hash table size
     * args[2] is the input file name
     * 
     * @param args
     *            is the command line arguments.
     */
    public static void main(String[] args) throws Exception {
        // This is the main file for the program.
        if (args == null) {
            return;
        }
        int memSize = Integer.parseInt(args[0]);
        int hashSize = Integer.parseInt(args[1]);
        if (args.length != 3) {
            System.out.print("Not enough arguments");
        }
        else if (powerOfTwo(memSize) == false) {
            System.out.print("The memory size "
                + "needs to be a power of two.");
        }
        else if (powerOfTwo(hashSize) == false) {
            System.out.print("The Hash Table" + " needs to be a power of two.");
        }
        else {
            String inputFileName = args[2];
            HashTable hashTable = new HashTable(hashSize);
            MemoryManager memManage = new MemoryManager(memSize);
            WorldDataBase worldDatabase = new WorldDataBase(hashTable,
                memManage);

            CommandProcessor process = new CommandProcessor(inputFileName,
                hashTable, memManage);

            process.processCommand(inputFileName, args);
            // hashTable.print();
        }
    }


    /**
     * This is a simple method to
     * check and see if something
     * is a power of two or not.
     * 
     * @param a
     *            the integer to check if
     *            it is a power of two or not.
     * @return true if it is a power
     *         of two or false if not.
     */
    public static boolean powerOfTwo(int a) {
        while (a % 2 == 0) {
            a = a / 2;
        }
        if (a == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    // Seminar dum = new Seminar();

    // create a commandprocessor object to parse through the file

}
