package it.unipd.eis;

import org.apache.commons.cli.*;

public class App 
{
    public static final String fileArticleStoragePath = "Storage";
    public static final String fileTermStoragePath = "Storage";
    public static final int numArticlesToDownload = 1000;
    public static final int numTermsToSave = 50;

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
                    Source.setStorage(new FileArticleStorage(fileArticleStoragePath));
                    TheGuardianAPISource theGuardianSource = new TheGuardianAPISource();
                    theGuardianSource.setNumArticles(numArticlesToDownload);
                    CSVSource csvSource = new CSVSource("nytimes_articles_v2.csv");

                    System.out.println("Starting download using search term(s) \"" + query + "\".");
                    Source.clearStorage();
                    theGuardianSource.downloadArticles(query);
                    csvSource.downloadArticles();

                    System.out.println("Download and serialization of articles complete.");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if(cmd.hasOption("e")) {
                TermExtractor extractor = new TermExtractor(numTermsToSave);
                extractor.setArticleStorage(new FileArticleStorage(fileArticleStoragePath));
                extractor.setTermStorage(new FileTermStorage(fileTermStoragePath));

                System.out.println("Starting extraction of top terms.");
                extractor.extractTerms();

                System.out.println("Extraction of top terms complete. See terms.txt file for results.");
            }
        } catch(ParseException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
