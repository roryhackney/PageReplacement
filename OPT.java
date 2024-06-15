import java.util.Arrays;

public class OPT extends ReplacementAlgorithm {
    private int index;

    public OPT(int pageFrameCount, Integer[] pages) {
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
        //for each page number
        //if in memory, continue
        //if there is room, insert and pageFault++
        //if no room, figure out which number to remove
        //look at the remainder of the page numbers to process
        //track options in another array of Ints
        //if a page num is in the arr, set to null and options--
        //once options == 1 or there are no remaining page nums, return (one of) the remaining option(s)
        if (print) {
            System.out.println("Starting:");
            System.out.println("Page fault count: 0");
        }
        for (int index = 0; index < pages.length; index++) {
            if (print) System.out.println(Arrays.toString(inMemory));
            if (! isInMemory(pages[index])) {
                if (this.index >= inMemory.length) {
                    int removeIdx = lastNeededPage();
                    if (print) System.out.println("Removing: " + inMemory[removeIdx]);
                    removeAndShiftLeft(removeIdx);
                }
                if (print) System.out.println("Inserting: " + pages[index]);
                insert(pages[index]);
                pageFaultCount++;
                if (print) System.out.println("Page faults: " + pageFaultCount);
            }
        }
        if (print) System.out.println(Arrays.toString(inMemory));

    }

    private void removeAndShiftLeft(int removeIdx) {
        for (int i = removeIdx; i < inMemory.length-1; i++) {
            inMemory[i] = inMemory[i+1];
        }
        this.index--;
    }

    private int lastNeededPage() {
        int options = inMemory.length;
        Integer[] x = Arrays.copyOf(inMemory, options);
        //for every remaining page
        for (int i = index + 1; i < pages.length; i++) {
            //find it in x and set that one to null and decrement options
            for (int j = 0; j < x.length; j++) {
                if (x[j] != null && x[j].equals(pages[i])) {
                    options--;
                    x[j] = null;
                    if (options == 1) {
                        for (int k = 0; k < x.length; k++) {
                            //return the index of the option to remove
                            if (x[k] != null) return k;
                        }
                    }
                }
            }
        }
        //if we reach the end of remaining pages, then we should just return one of the options
        for (int k = 0; k < x.length; k++) {
            if (x[k] != null) return k;
        }
        return -1;
    }
}
