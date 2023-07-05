package it.unipd.eis;

import java.io.*;

/**
 * A utility class that provides methods for file storage operations.
 */
public class StorageUtils {
    /**
     * Creates the directory if it does not exist for the specified file path.
     *
     * @param filePath the path to the file or directory
     */
    public static void createDirectoryIfNotExists(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
