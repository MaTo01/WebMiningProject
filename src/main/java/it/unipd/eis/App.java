package it.unipd.eis;

import java.io.File;
import org.apache.commons.cli.*;

/**
 * Main class for the application.
 */
public class App 
{
    private static final String FILE_ARTICLE_SOURCE_PATH = "Sources";
    private static final String FILE_ARTICLE_STORAGE_PATH = "Storage";
    private static final String FILE_TERM_STORAGE_PATH = "Storage";
    private static final int NUM_ARTICLES_TO_DOWNLOAD = 1000;
    private static final int MAX_TERMS_TO_SAVE = 50;

    /**
     * Entry point of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("d", true, "Download articles from the supported sources based on a key word/phrase.");
        options.addOption("e", false, "Extract the terms with the highest weight and save them in a text file.");
        options.addOption("h", false, "Display this help menu.");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("cmd", options);
            }
            if(cmd.hasOption("d")) {
                String query = cmd.getOptionValue("d");

                if(!query.equals("")) {
                    // Check if the "Sources" directory exists
                    File sourcesDirectory = new File(FILE_ARTICLE_SOURCE_PATH);
                    if (!sourcesDirectory.isDirectory()) {
                        throw new IllegalStateException("Sources directory not found.");
                    }

                    // Check if the APIKey exists
                    File apiKeyFile = new File(FILE_ARTICLE_SOURCE_PATH + "/TheGuardianAPIKey.txt");
                    if (!apiKeyFile.isFile()) {
                        throw new IllegalStateException("APIKey not found.");
                    }

                    // Check if the "CSV" directory exists
                    File csvDirectory = new File(FILE_ARTICLE_SOURCE_PATH + "/CSV");
                    if (!csvDirectory.isDirectory()) {
                        throw new IllegalStateException("CSV directory not found.");
                    }
                    
                    // Set article storage and sources
                    Source.setArticleStorage(new FileArticleStorage(FILE_ARTICLE_STORAGE_PATH));
                    TheGuardianAPISource theGuardianSource = new TheGuardianAPISource();
                    theGuardianSource.setNumArticles(NUM_ARTICLES_TO_DOWNLOAD);
                    CSVSource csvSource = new CSVSource("nytimes_articles_v2.csv");

                    // Clear article storage
                    Source.clearStorage();

                    System.out.println("Starting download using search term(s) \"" + query + "\".");
                    theGuardianSource.downloadArticles(query);
                    csvSource.downloadArticles();

                    System.out.println("Download and serialization of articles complete.");
                } else {
                    throw new IllegalArgumentException("Query string cannot be empty.");
                }
            }
            if(cmd.hasOption("e")) {
                // Check if articles.json file exists
                File articlesFile = new File(FILE_ARTICLE_STORAGE_PATH + "/articles.json");
                if (!articlesFile.exists()) {
                    throw new IllegalStateException("Uninitialized or invalid storage(s).");
                }

                // Set article and term storage, and create extractor
                FileArticleStorage articleStorage = new FileArticleStorage(FILE_ARTICLE_STORAGE_PATH);
                FileTermStorage termStorage = new FileTermStorage(FILE_TERM_STORAGE_PATH);
                TermExtractor extractor = new TermExtractor(MAX_TERMS_TO_SAVE);
                extractor.setArticleStorage(articleStorage);
                extractor.setTermStorage(termStorage);

                // Clear term storage
                termStorage.clearStorage();

                System.out.println("Starting extraction of top terms.");
                extractor.extractTerms();

                System.out.println("Extraction of top terms complete. See terms.txt file for results.");
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }
}
