import java.util.Arrays;

public class LRU extends ReplacementAlgorithm {
    private int index;

    public LRU(int pageFrameCount, Integer[] pages) {
        super(pageFrameCount, pages);
        inMemory = new Integer[pageFrameCount];
        index = 0;
    }
    @Override
    public void insert(int pageNumber) {
        inMemory[index++] = pageNumber;
    }

    @Override
    public void runAlgorithm(boolean print) {
        //ok so
        //for each page
        //if in, put at index 0 (most recently used) and shift everything else before it right
        //if not in, and there is room, add it, pagefault++
        //if not in, and there is not room, grab final index (least recently used), shift everything right, put in index 0
        //ordered from most recently used to least recently used
        if (print) {
            System.out.println("Starting:");
            System.out.println("Page fault count: 0");
        }
        for (int page : pages) {
            if (print) System.out.println("In memory: " + Arrays.toString(inMemory));
            int swapIdx = -1;
            for (int i = 0; i < inMemory.length; i++) {
                if (inMemory[i] != null && inMemory[i].equals(page)) {
                    swapIdx = i;
                    break;
                }
            }
            //if in memory, put in 0 (most recently used) and shift the rest
            if (swapIdx != -1) {
                if (print) System.out.println("Newly most recent: " + page);
                moveIdxTo0AndShift(swapIdx);
            } else {
                //if not in memory and there is room
                if (this.index < inMemory.length) {
                    inMemory[this.index++] = page;
                    moveIdxTo0AndShift(this.index-1);
                } else {
                    //not in memory and no room - remove last index and insert, then make most recent
                    if (print) System.out.println("Removing: " + inMemory[inMemory.length-1]);
                    index--;
                    insert(page);
                    moveIdxTo0AndShift(index - 1);
                }
                if (print) System.out.println("Inserting: " + page);
                pageFaultCount++;
                if (print) System.out.println("Page faults: " + pageFaultCount);
            }
        }
        if (print) System.out.println("In memory: " + Arrays.toString(inMemory) + "\nPage faults: " + pageFaultCount);
    }

    private void moveIdxTo0AndShift(int index) {
        Integer value = inMemory[index];
        for (int i = index; i > 0; i--) {
            inMemory[i] = inMemory[i-1];
        }
        inMemory[0] = value;
    }
}
