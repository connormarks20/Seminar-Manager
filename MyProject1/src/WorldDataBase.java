/**
 * This class represents the database where all
 * the records and information of the command
 * ID's are. This class initializes the hashtable
 * and memory manager in order for everything to
 * work and run together.
 * 
 * @author aalbro, connorm20
 * @version 08.25.2023
 */
public class WorldDataBase {

    private HashTable hashTable;
    private MemoryManager memManage;

    /**
     * This is the constructor for the
     * WorldDatabase class.
     * 
     * @param hashTable
     *            is a HashTable..
     */
    public WorldDataBase(HashTable hashTable, MemoryManager memManage) {
        this.memManage = memManage;
        this.hashTable = hashTable;

    }


    /**
     * This method inserts a seminar object
     * into the HashTable.
     * 
     * @param iD
     *            the seminars ID
     * @param sem
     *            is the seminar object to insert.
     */
    public void insertSeminar(int iD, Seminar sem) throws Exception {
        if (!hashTable.search1(iD)) {
            memManage.insert(sem.serialize(), 256);
        }

        hashTable.insert(iD, sem);

    }


    /**
     * This method prints the HashTable
     */
    public void printHashTable() {
        hashTable.printTable();
        memManage.printFreeBlocks();
    }


    /**
     * This searches for the ID of the
     * Seminar in the HashTable and returns it.
     * 
     * @param iD
     *            is the ID to find.
     */
    public void searchID(int iD) {
        hashTable.search(iD);
    }


    /**
     * This is the delete method
     * 
     * @param iD
     *            the ID to delete.
     */
    public void deleteID(int iD) {
        hashTable.delete(iD);

    }
}
