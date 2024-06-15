import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] test1 = new Integer[] {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
        runTest(test1, 3);
        Integer[] test2 = new Integer[] {8,1,0,7,3,0,3,4,5,3,5,2,0,6,8,4,8,1,5,3};
        runTest(test2, 3);
        Integer[] test3 = new Integer[] {4,6,4,8,6,3,6,0,5,9,2,1,0,4,6,3,0,6,8,4};
        runTest(test3, 3);

        Integer[] random = new Integer[15];
        for (int i = 0; i < 15; i++) {
            random[i] = (int)(Math.random() * 10);
        }
        runTest(random, 3);
        runTest(random, 5);
        runTest(random, 7);
    }

    public static void runTest(Integer[] testData, int frames) {
        System.out.println("---------------------------------------");
        System.out.println("Frames: " + frames);
        ReplacementAlgorithm fifo1 = new FIFO(frames, testData);
        fifo1.runAlgorithm(false);
        System.out.println(Arrays.toString(testData) + "\nFIFO: " + fifo1.getPageFaultCount());

        ReplacementAlgorithm lru = new LRU(frames, testData);
        lru.runAlgorithm(false);
        System.out.println("LRU: " + lru.getPageFaultCount());

        ReplacementAlgorithm opt = new OPT(frames, testData);
        opt.runAlgorithm(false);
        System.out.println("OPT: " + opt.getPageFaultCount());
        System.out.println("---------------------------------------");
    }
}
