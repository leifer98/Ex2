package Ex2_1;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * A Callable that counts the number of lines in a file.
 */
public class FileReaderCallable implements Callable<Integer> {
    private final String fileName;

    /**
     * Creates a new Ex2_1.FileReaderCallable.
     *
     * @param fileName the name of the file to read
     */
    public FileReaderCallable(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Counts the number of lines in the file.
     *
     * @return the number of lines in the file
     * @throws Exception if an error occurs while reading the file
     */
    @Override
    public Integer call() throws Exception {
        return FileHandling.getFileNumOfLines(this.fileName);
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "Ex2_1.FileReaderCallable{" +
                "fileName='" + fileName + '\'' +
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
