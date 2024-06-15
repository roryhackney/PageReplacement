import java.util.Arrays;

public class FIFO extends ReplacementAlgorithm {
    private int index;

    public FIFO(int pageFrameCount, Integer[] pages) {
        super(pageFrameCount, pages);
        inMemory = new Integer[pageFrameCount];
        index = 0;
    }

    @Override
    public void insert(int pageNumber) {
        inMemory[index++] = pageNumber;
    }

    //track pageFramesToProcess in one list
    //track currentlyInMemory in another list of length pageFrameCount
    //for every element to process
    //if it is in memory, continue
    //otherwise, check if frames are full
    //if no, insert()
    //if yes, remove...
    //FIFO: oldest item (at one end of list)
    //let's say we have a list of 3 items, the LAST item is always the one to be removed for efficiency?
    //page faults + 1

    public void runAlgorithm(boolean print) {
        if (print) {
            System.out.println("Starting:");
            System.out.println("Page fault count: 0");
        }
        for (int page : pages) {
            if (print) System.out.println("In memory: " + Arrays.toString(inMemory));
            if (! isInMemory(page)) {
                if (index >= pageFrameCount) removeOldestAndShift(print);
                pageFaultCount++;
                if (print) System.out.println("Page fault count: " + pageFaultCount + "\nInserting: " + page);
                insert(page);
            }
        }
        if (print) System.out.println("Done. Page faults: " + pageFaultCount);
    }

    public void removeOldestAndShift(boolean print) {
        if (print) System.out.println("Removing: " + inMemory[0]);
        for (int index = 1; index < pageFrameCount; index++) {
            inMemory[index - 1] = inMemory[index];
        }
        this.index--;
    }
}
