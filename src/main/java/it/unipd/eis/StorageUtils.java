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
    public static void createDirectoryIfNotExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

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
