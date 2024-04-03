/**
 * This is the HashTable class where
 * we have our HashTable. It will have
 * methods like insert, delete, search, and
 * a double hashing method to probe.
 * 
 * @author Connor Marks (connorm20),
 *         Andrew Albro (aalbro)
 * @version 09.03.2023
 */
public class HashTable {

    // call the memory manager object and create space for the slots

    // check for collisions

    // find the id, do the linear probing. Just

    // the hashtable is basically an array of keyvalues

    private int size;
    private int recordCount;
    private Record<Integer, Seminar>[] table;
    private Record<Integer, Seminar> tombStone;

    /**
     * This is the constructor for the
     * HashTable class.
     * 
     * @param initialSize
     *            is the initial
     *            size of the HashTable.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashTable(int initialSize) {
        this.size = initialSize;
        this.table = new Record[size];
        tombStone = new Record(-1, "TOMBSTONE");
        recordCount = 0;

    }


    /**
     * This is the method for
     * using double Hashing. It uses
     * two different functions to probe
     * for an object.
     * 
     * @param iD
     *            is the ID of the seminar.
     * @param len
     *            is the length of the table.
     * @return the position in the table
     * 
     */
    public int linearProbing(int iD, int len) {
        if (len != 0) {
            return (((iD / len) % (len / 2)) * 2) + 1;
        }
        return 0;
    }


    /**
     * 
     * Method to print the records stored in the hashtable
     */
    public void printTable() {
        System.out.println("Hashtable:");

        for (int i = 0; i < table.length; i++) {

            if (table[i] != null && table[i].getKey().equals(-1)) {
                System.out.println(i + ": " + "TOMBSTONE");
            }

            else if (table[i] != null) {

                System.out.println(i + ": " + table[i].getKey());

            }

        }
        System.out.println("total records: " + recordCount);
        return;
    }


    /**
     * This method inserts a seminars ID and
     * the actual seminar object into the table.
     * 
     * @param iD
     *            the ID of the item to insert
     *            into the HashTable.
     * 
     * @param serialized
     *            the seminar object to
     *            insert into the HashTable.
     */
    public void insert(int iD, Seminar serialized) throws Exception {
        // assigns an initial home slot for the table

        if (table.length / 2 <= recordCount) {
            expand();
            System.out.println("Hash table expanded to " + table.length
                + " records");
        }
        byte[] serializedData = serialized.serialize();

        int home = iD % size;

        // after we get the length of bytes for allocation, call allocate() in
        // MemoryManager

        // implement a function to determine if the ID passed is already present
        // in table

        // handles the linear probing.

        // for h2(k) = (k/M)%((M/2*2)+1 where M is the length of table and k is
        // iD

        while (table[home] != null && !table[home].getKey().equals(-1)) {
            // look at step sizes to make sure they're right.
            // after finding the home slot, we good
            home = (home + linearProbing(iD, table.length)) % table.length;
        }

        if (search1(iD)) {
            System.out.println(
                "Insert FAILED - There is already a record with ID" + " " + iD);
            return;
        }

        // Insert the record at the found position
        table[home] = new Record<>(iD, serialized);
        System.out.println("Successfully inserted record with ID " + iD);
        recordCount++;

        System.out.println(table[home].getValue().toString());
        System.out.println("Size: " + serializedData.length);
    }


    /**
     * This search method tests if an ID
     * already exists in the table.
     * 
     * @param iD
     *            the ID to find.
     * @return true if the ID is found
     *         or false if not.
     */
    public boolean search1(int iD) {
        for (int i = 0; i < table.length; i++) {
            if (this.table[i] != null) {
                if (this.table[i].getKey() == iD) {
                    // ensures the slot is not a tombstone
                    if (!this.table[i].getKey().equals(-1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * This searches for a specific ID in the hashTable.
     * 
     * @param iD
     *            is the ID to find.
     * @return true if it is found or false if not.
     */
    public boolean search(int iD) {

        for (int i = 0; i < table.length; i++) {
            if (this.table[i] != null && this.table[i].getKey() == iD) {
                System.out.println("Found record with ID " + iD + ":\n"
                    + this.table[i].getValue());
                return true;
            }
        }
        System.out.println("Search FAILED -- There is no record with ID " + iD);
        return false;
    }


    /**
     * This method will double the
     * size of the HashTable when
     * it is 50 percent full or more.
     */
    public void expand() {

        int newSize = table.length * 2;
        @SuppressWarnings("unchecked")
        Record<Integer, Seminar>[] newTable = new Record[newSize];

        for (int i = 0; i < table.length; i++) {
            Record<Integer, Seminar> rec = table[i];
            if (rec != null) {
                int newHash = hashFunction(rec.getKey(), newSize);
                while (newTable[newHash] != null) {
                    newHash = (newHash + 1) % newSize;
                }
                newTable[newHash] = rec;
            }
        }
        table = newTable;
        size = newSize;
    }


    /**
     * This method deletes a record
     * in the HashTable.
     * 
     * @param iD
     *            is the record to delete.
     */
    public void delete(int iD) {

        int home = (iD % table.length);
        int currPos = home;

        while (table[currPos] != null) {
            if (table[currPos].getKey() == iD) {

                table[currPos] = tombStone;
                recordCount--;

                System.out.println("Record with ID " + iD
                    + " successfully deleted from the database");
                return;
            }
            currPos = (currPos + linearProbing(iD, table.length))
                % table.length;
            // If we have looped back to the starting position, break to avoid
            // an infinite loop
            if (currPos == home) {
                break;
            }
        }

        // Record with the given ID not found
        System.out.println("Delete FAILED -- There is no record with ID " + iD);
    }


    /**
     * This is a getter method
     * to get the size of
     * the table.
     * 
     * @return the size of the table.
     */
    public int getSize() {
        return size;
    }


    /**
     * This method is used to rehash
     * when a new table is made.
     * 
     * @param key
     *            the integer to hash
     * @param tableSize
     *            the size of the table.
     * @return the hash code of the table.
     */
    private int hashFunction(int key, int tableSize) {
        return (Math.abs(key) % tableSize);
    }

}
