import java.util.Scanner;
import java.io.File;

/**
 * The class responsible for parsing the input file
 *
 * @author Connor Marks (connorm20)
 * @author Andrew Albro (aalbro)
 * @version 8/29/2023
 */

public class CommandProcessor {

    // write all the field variables

    private WorldDataBase worldDataBase;

    private String title;
    private String dateAndTime;
    private String nameOfFile;
    private int length;
    private String[] keyWords;
    private short x;
    private short y;
    private String description;
    private int cost;
    private int iD;

    /**
     * This is the constructor for the CommandProcessor class.
     * 
     * @param filename
     *            the name of the file to create
     *            the command processor with.
     * @param hashSize
     *            is the size of the
     *            HashTable.
     */
    public CommandProcessor(
        String filename,
        HashTable hashSize,
        MemoryManager memManage) {
        this.nameOfFile = filename;
        this.worldDataBase = new WorldDataBase(hashSize, memManage);

    }


    /**
     * This method processes the commands from
     * the file.
     * 
     * @param fileName
     *            the name of the file to parse.
     * @param args
     *            is the argument from the
     *            commandline.
     * @throws Exception
     *             if there is an error.
     */
    public void processCommand(String fileName, String[] args)
        throws Exception {
        this.nameOfFile = fileName;
        Scanner scan = new Scanner(new File(nameOfFile));

        while (scan.hasNext()) {
            String commandType = scan.next();
            switch (commandType) {
                case "insert":

                    iD = Integer.parseInt(scan.next().trim());
                    scan.nextLine();
                    title = scan.nextLine().trim();
                    dateAndTime = scan.next().trim();
                    length = Integer.parseInt(scan.next().trim());
                    x = Short.parseShort(scan.next().trim());
                    y = Short.parseShort(scan.next().trim());
                    cost = Integer.parseInt(scan.next().trim());
                    scan.nextLine();

                    // splits the keywords into an array of substrings
                    String keywordsLine = scan.nextLine().trim();
                    // use the regular expression \s to ignore all whitespace
                    // between strings
                    keyWords = keywordsLine.trim().split("\\s+");

                    description = scan.nextLine().trim();

                    Seminar seminar = new Seminar(iD, title, dateAndTime,
                        length, x, y, cost, keyWords, description);
                    worldDataBase.insertSeminar(iD, seminar);
                    // worldDataBase.insert(ID, Seminar)
                    // call to the test method, can be deleted later
                    // printFields();
                    break;
                case "delete":
                    this.iD = Integer.parseInt(scan.next().trim());
                    worldDataBase.deleteID(iD);
                    // Search the database for the ID to see
                    // if it can be removed or not. If it's not found,
                    // it will say that it cannot be found.
                    break;
                case "search":
                    iD = Integer.parseInt(scan.next().trim());
                    worldDataBase.searchID(iD);
                    // Print ID if in database
                    break;
                case "print":
                    if (scan.next().trim().equals("hashtable")) {
                        worldDataBase.printHashTable();
                        break;
                        // If not found, print not found message
                    }
                    
            }
        }
    }
}
