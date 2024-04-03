
/**
 * 
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MemoryManager {
    // creates a byte array to represent the memory pool
    private byte[] pool;
    // creates an array of linked lists.
    private LinkedList<FreeBlock>[] blocks;
    // integer to represent the size of the memory pool
    private int memSize;
    // creates an array of handles to keep track of handles after allocation
    private List<Handle> handles;

    public MemoryManager(int memSize) {

        this.memSize = memSize;
        // initialize the pool to be a new byte array that's the size of the
        // memory pool
        this.pool = new byte[memSize];

        // initialize the array of linked list to be logbase2 of the memsize
        this.blocks = new LinkedList[getInitialSize(memSize)];

        // assign each index of the array to be an empty linked list
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new LinkedList<FreeBlock>();

        }
        this.handles = new ArrayList<Handle>();
        // create an initial freeblock
        FreeBlock initBlock = new FreeBlock(0, memSize - 1);

        // the last index of the array will be a linkedlist containing a
        // freeblock covering the entire memory pool
        blocks[blocks.length - 1].add(initBlock);
    }


    public void printFreeBlocks() {

        for (LinkedList<FreeBlock> freeLists : blocks) {
            System.out.println(freeLists);
            for (FreeBlock block : freeLists) {
                if (block != null) {
                    System.out.print(block.getStart() + ":" + block.getEnd()
                        + "\n");

                }
            }
        }
    }


    private int getTotalFreeMemory() {
        int totalFreeMemory = 0;

        for (LinkedList<FreeBlock> freeList : blocks) {
            for (FreeBlock freeBlock : freeList) {
                totalFreeMemory += freeBlock.getSize();
            }
        }

        return totalFreeMemory;
    }


    /**
     * public Handle insert(byte[] space, int size) {
     * // Start at the last index of the blocks array and work your way to the
     * // first
     * 
     * for (int i = blocks.length - 1; i >= 0; i--) {
     * LinkedList<FreeBlock> freeList = blocks[i];
     * ListIterator<FreeBlock> iterator = freeList.listIterator();
     * 
     * while (iterator.hasNext()) {
     * FreeBlock block = iterator.next();
     * 
     * // Check if the block can accommodate the request size or is
     * // larger
     * if (block.getSize() >= size) {
     * // Allocate the block and remove it from the free list
     * iterator.remove();
     * 
     * // Calculate the size of the remaining block after
     * // allocation
     * int remainingSize = block.getSize() - size;
     * 
     * // Check if splitting is needed (remainingSize > 0)
     * if (remainingSize > 0) {
     * // Create a new FreeBlock for the remaining part
     * FreeBlock splitBlock = new FreeBlock(block.getStart()
     * + size, block.getEnd());
     * 
     * // Update the size of the original block
     * block.setSize(size);
     * 
     * // Add the split block back to the free list
     * iterator.add(splitBlock);
     * }
     * 
     * // Allocate the block (you should implement the allocation
     * // logic here)
     * // and return a handle if needed
     * 
     * // Make sure to update your memory allocation data
     * // structures
     * // as needed, e.g., track allocated blocks and handles
     * 
     * // Return a handle if you have allocated the block
     * return new Handle(block.getStart(), size);
     * }
     * }
     * }
     * return null;
     * 
     * }
     */

    public Handle insert(byte[] space, int size) {
        // search the array and find a block that can accomodate the request
        // size

        expand();

        // start at the last index of the blocks array
        for (int i = blocks.length - 1; i >= 0; i--) {
            // look for a linkedlist that contains a freeblock suitable for the
            // request
            for (LinkedList<FreeBlock> freeList : blocks) {
                for (FreeBlock block : freeList) {

                    // if a block is found whose size is greater than that of
                    // the request size,
                    // do the operations below

                    // split the freeblocks into powers of 2
                    while (block != null && block.getSize() > size) {
                        // allocate that block
                        freeList.remove(block);
                        // create a new empty linked list at that location
                        blocks[i] = new LinkedList<FreeBlock>();
                        // set the size of the block to be half its original
                        // size
                        block.setSize(block.getSize() / 2);
                        // set the start to be zero and the end to be the
                        // requested size
                        block.setStart(0);
                        block.setEnd((block.getSize() / 2));

                        FreeBlock duplicate = block;
                        duplicate.setStart(memSize / 2);
                        duplicate.setEnd(memSize - 1);
                        freeList.add(block);
                        freeList.add(duplicate);
                        LinkedList<FreeBlock> newList = new LinkedList<>();

                        newList.add(block);
                        newList.add(duplicate);
                        if (i > 0) {
                            blocks[i - 1] = newList;

                        }
                        return null;

                    }
                    // once the while loop breaks each block up, we have to
                    // actually allocate that block to be the size;

                }
            }
        }
        for (int i = 0; i < blocks.length; i++) {
            for (LinkedList<FreeBlock> freeList : blocks) {
                for (FreeBlock free : freeList) {
                    if (free.getSize() < size) {

                        freeList.remove(free);
                        free.setSize(free.getSize() - size);
                        free.setStart(0);

                    }
                }
            }
        }
        return null;
    }


    /**
     * while (block != null && block.getSize() >= size) {
     * block.setSize(block.getSize() / 2);
     * block.setStart(block.getStart());
     * block.setEnd(block.getEnd());
     * FreeBlock newBlock = new FreeBlock(0, block.getSize());
     * blocks[i].remove(block);
     * freeList.add(block);
     * freeList.add(newBlock);
     * if (i > 0) {
     * blocks[i - 1] = freeList;
     * }
     * }
     */
    // split if necessary, remove the freeblock from that
    // list
    // allocate that block to the memory pool. grab the
    // handle
    // and return it
    // }
    // }
    // }
    // return null;
    // }

    // don't really know how to do .arraycopy, these parameters are probably
    // wrong

    public int findSmaller(int requestSize) {
        int temp = 1;
        while (temp > requestSize) {
            temp *= 2;
        }
        return temp;
    }


    public int LogBase2(int someSize) {
        int count = 0;
        while (someSize > 1) {
            someSize /= 2;
            count++;
        }
        return count;
    }


    private int findSmallestPowerOf2BlockSize(int size) {
        int blockSize = 1;
        while (blockSize < size) {
            blockSize *= 2;
        }
        return blockSize;
    }


    public int getInitialSize(int memSize) {
        int count = 0;
        int size = 1;
        while (size < memSize) {
            size *= 2;
            count++;
        }
        return count;
    }


    private void expand() {
        int totalAllocatedMemory = memSize - getTotalFreeMemory();

        // Check if more than half of the total memory pool size is allocated
        if (totalAllocatedMemory * 2 >= memSize) {
            int newMemSize = memSize * 2;
            byte[] newPool = new byte[newMemSize];

            // Copy data from the old pool to the new pool
            System.arraycopy(pool, 0, newPool, 0, memSize);

            // Update the memory pool and size
            pool = newPool;
            memSize = newMemSize;

            // Adjust existing free blocks to accommodate the expanded pool
            for (int i = 0; i < blocks.length; i++) {
                for (FreeBlock freeBlock : blocks[i]) {
                    freeBlock.setEnd(memSize - 1);
                }
            }

            System.out.println("Memory Pool Expanded to " + newMemSize);
        }
    }

}
