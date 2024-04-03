/**
 * This class represents a record
 * being stored in the hash table.
 * It has getter methods we can use to
 * acquire things like the ID, title, etc.
 * 
 * @author connorm20, aalbro
 * @version 08.29.2023
 * @param <Key>
 *            is the first data type
 *            to compare to the second one.
 * @param <Value>
 *            is the second data type
 *            to compare to the first one.
 */
public class Record<Key, Value> {
    public Key key;
    private Value value;
    private Record<Key, Value> next;

    /**
     * This is the constructor for the
     * Record class.
     * 
     * @param key
     *            is the key of the
     *            record.
     * @param value
     *            the value of the
     *            record.
     */
    public Record(Key key, Value value) {
        this.key = key;
        this.value = value;
    }


    /**
     * This gets the key of
     * the record.
     * 
     * @return the key of the record.
     */
    public Key getKey() {
        return key;
    }


    /**
     * This gets the value of
     * the record.
     * 
     * @return the value of the record.
     */
    public Value getValue() {
        return value;
    }


    /**
     * This sets the value of
     * a record.
     * 
     * @param value
     *            the value to set
     *            for the record.
     */
    public void setValue(Value value) {
        this.value = value;
    }


    /**
     * This method gets the next
     * record.
     * 
     * @return the next record.
     */
    // public Record<Key, Value> getNext()
    // {
    // return next;
    // }
    /**
     * This sets the next record.
     */
    // public void setNext(Record<Key, Value> nex)
    // {
    // this.next = nex;
    // }
    @Override
    /**
     * This is the toString method
     * used to print out the records
     * information.
     */
    public String toString() {
        return "Record{" + "key=" + key + ", value=" + value + '}';
    }
}
