public abstract class ReplacementAlgorithm {
    // the number of page faults
    protected int pageFaultCount;

    // the number of physical page frame
    protected int pageFrameCount;

    //the pages to run through the algorithm
    protected Integer[] pages;

    protected Integer[] inMemory;

    /**
     * @param pageFrameCount - the number of physical page frames
     */
    public ReplacementAlgorithm(int pageFrameCount, Integer[] pages) {
        if (pageFrameCount < 0)
            throw new IllegalArgumentException();
        if (pages == null)
            throw new IllegalArgumentException("Invalid pages");
        this.pageFrameCount = pageFrameCount;
        pageFaultCount = 0;
        this.pages = pages;
    }

    public boolean isInMemory(Integer element) {
        for (int index = 0; index < pageFrameCount; index++) {
            if (inMemory[index] != null && inMemory[index].equals(element)) return true;
        }
        return false;
    }

    /**
     * @return - the number of page faults that occurred.
     */
    public int getPageFaultCount() {
        return pageFaultCount;
    }

    /**
     * @param pageNumber - the page number to be inserted
     */
    public abstract void insert(int pageNumber);

    /**
     * Runs the algorithm, do this before retrieving page fault count
     * @param print whether to display intermediate state step by step
     */
    public abstract void runAlgorithm(boolean print);
}