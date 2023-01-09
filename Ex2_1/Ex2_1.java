package Ex2_1;

import java.util.*;
import java.util.concurrent.*;

/**
 * A class containing methods for creating text files and getting the sum of the number of lines in an array of files.
 */
public class Ex2_1 {

    /**
     * Creates `n` text files, each containing a random number between 0 and `bound`, exclusive. The name of each file is `file_i.txt`,
     * where `i` is a number between 1 and `n`, inclusive.
     *
     * @param n the number of text files to create
     * @param seed the seed for the random number generator
     * @param bound the upper bound (exclusive) for the random numbers
     * @return an array containing the names of the created files
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        Random rand = new Random(seed);
        String[] list = new String[n];
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            FileHandling.writeFile("file_" + i + ".txt", x);
            list[i - 1] = "file_" + i + ".txt";
        }
        return list;
    }

    /**
     * Returns the sum of the number of lines in each file, given an array of file names.
     *
     * @param fileNames an array containing the names of the files
     * @return the sum of the number of lines in each file
     */
    public static int getNumOfLines(String[] fileNames) {
        int sum = 0;
        for (int i = 0; i < fileNames.length; i++) {
            sum += FileHandling.getFileNumOfLines(fileNames[i]);
        }
        return sum;
    }

    /**
     * Returns the sum of the number of lines in each file, given an array of file names, using multiple threads.
     *
     * @param fileNames an array containing the names of the files
     * @return the sum of the number of lines in each file
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        FileReaderThread[] t = new FileReaderThread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            t[i] = new FileReaderThread("thread" + i, fileNames[i]);
            t[i].start();
        }
        for (int i = 0; i < fileNames.length; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int sum = 0;
        for (int i = 0; i < fileNames.length; i++) {
            sum += t[i].getNumOfLines();
        }
        return sum;
    }

    /**
     * Returns the sum of the number of lines in each file, given an array of file names, using a thread pool.
     *
     * @param fileNames an array containing the names of the files
     * @return the sum of the number of lines in each file
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int sum = 0;
        ExecutorService pool = Executors.newFixedThreadPool(fileNames.length);
        Future<Integer>[] results = new Future[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            results[i] = pool.submit(new FileReaderCallable(fileNames[i]));
        }
        for (int i = 0; i < fileNames.length; i++) {
            try {
                sum += results[i].get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        pool.shutdown();
        return sum;
    }


    /**
     * Measures the execution time of three different methods for counting the number of lines in a
     * given array of text files.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] fileNames = createTextFiles(5000, 1, 500);
        int numOfLines2 = Ex2_1.getNumOfLines(fileNames);

        // Measure the execution time of the getNumOfLines() method
        long startTime = System.currentTimeMillis();
        int numOfLines = Ex2_1.getNumOfLines(fileNames);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time for getNumOfLines(): " + elapsedTime + " milliseconds");

        // Measure the execution time of the getNumOfLinesThreads() method
        startTime = System.currentTimeMillis();
        int numOfLinesThreads = Ex2_1.getNumOfLinesThreads(fileNames);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time for getNumOfLinesThreads(): " + elapsedTime + " milliseconds");

        // Measure the execution time of the getNumOfLinesThreadPool() method
        startTime = System.currentTimeMillis();
        int numOfLinesThreadPool = Ex2_1.getNumOfLinesThreadPool(fileNames);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time for getNumOfLinesThreadPool(): " + elapsedTime + " milliseconds");
    }

}
