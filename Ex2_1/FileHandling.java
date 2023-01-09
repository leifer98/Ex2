package Ex2_1;

import java.io.*;

/**
 * A utility class for working with files.
 */
public class FileHandling {
    /**
     * Writes a specified number of lines to a file.
     *
     * @param fileName the name of the file to write to
     * @param x the number of lines to write
     */
    public static void writeFile(String fileName, int x) {
        //		try write to the file
        try {
            FileWriter fw = new FileWriter(fileName);
            PrintWriter outs = new PrintWriter(fw);
            for (int i = 0; i < x; i++) {
                outs.println("happy hanuka!");
            }
            outs.close();
            fw.close();
        } catch (IOException ex) {
            System.out.print("Error writing file\n" + ex);
        }
    }

    /**
     * Counts the number of lines in a file.
     *
     * @param fileName the name of the file to read
     * @return the number of lines in the file
     */
    public static int getFileNumOfLines(String fileName) {
        int i = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            for (i = 1; str != null ; i++) {
                str = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException ex) {
            System.out.print("Error reading file\n" + ex);
            System.exit(2);
        }
        // optional added code, to see difference in research
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //TODO to make I need to return i and not (i-1)
        return i;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Ex2_1.FileHandling{}";
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Compares the given object with the object on which the method is called for equality.
     *
     * @param obj the object to compare with
     * @return `true` if the objects are equal, `false` otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
