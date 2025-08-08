package it.unipd.eis;

import java.io.*;

/**
 * A utility class that provides methods for file storage operations.
 */
public class StorageUtils {
    /**
     * Creates the directory if it does not exist for the specified file path.
     *
     * @param dirPath the path to the directory
     */
    public static void createDirectoryIfNotExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Creates a file at the specified file path if it does not already exist.
     *
     * @param filePath the path of the file to create
     */
    public static void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
