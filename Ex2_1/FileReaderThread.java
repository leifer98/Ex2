package Ex2_1;

import java.util.Objects;

public class FileReaderThread extends Thread {
    private final String fileName;
    private int numOfLines;

    /**
     * Creates a new Ex2_1.FileReaderThread.
     *
     * @param name the name of the thread
     * @param fileName the name of the file to read
     */
    public FileReaderThread(String name, String fileName) {
        super(name);
        this.fileName = fileName;
    }

    /**
     * Counts the number of lines in the file.
     */
    @Override
    public void run() {
        this.numOfLines = FileHandling.getFileNumOfLines(this.fileName);
    }

    /**
     * Returns the number of lines in the file.
     *
     * @return the number of lines in the file
     */
    public int getNumOfLines() {
        return this.numOfLines;
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileName, numOfLines);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "Ex2_1.FileReaderThread{" +
                "fileName='" + fileName + '\'' +
                ", numOfLines=" + numOfLines +
                '}';
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
