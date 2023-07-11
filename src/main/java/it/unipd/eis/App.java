package it.unipd.eis;

import java.io.File;
import org.apache.commons.cli.*;

public class App 
{
    private static final String FILE_ARTICLE_STORAGE_PATH = "Storage";
    private static final String FILE_TERM_STORAGE_PATH = "Storage";
    private static final int NUM_ARTICLES_TO_DOWNLOAD = 1000;
    private static final int MAX_TERMS_TO_SAVE = 50;

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
                    Source.setArticleStorage(new FileArticleStorage(FILE_ARTICLE_STORAGE_PATH));
                    TheGuardianAPISource theGuardianSource = new TheGuardianAPISource();
                    theGuardianSource.setNumArticles(NUM_ARTICLES_TO_DOWNLOAD);
                    CSVSource csvSource = new CSVSource("nytimes_articles_v2.csv");
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
                File articlesFile = new File(FILE_ARTICLE_STORAGE_PATH + "/articles.json");
                if (!articlesFile.exists()) {
                    throw new IllegalStateException("Uninitialized or invalid storage(s).");
                }

                FileArticleStorage articleStorage = new FileArticleStorage(FILE_ARTICLE_STORAGE_PATH);
                FileTermStorage termStorage = new FileTermStorage(FILE_TERM_STORAGE_PATH);
                TermExtractor extractor = new TermExtractor(MAX_TERMS_TO_SAVE);
                extractor.setArticleStorage(articleStorage);
                extractor.setTermStorage(termStorage);
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
