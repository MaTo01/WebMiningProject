package it.unipd.eis;

import org.apache.commons.cli.*;

public class App 
{
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
                    TheGuardianAPISource theGuardianSource = new TheGuardianAPISource();
                    theGuardianSource.setNumArticles(1000);
                    CSVSource csvSource = new CSVSource("nytimes_articles_v2.csv");

                    Source.clearStorage();
                    theGuardianSource.downloadArticles(query);
                    csvSource.downloadArticles();

                    System.out.println("Download and serialization of articles complete.");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if(cmd.hasOption("e")) {
                TermExtractor extractor = new TermExtractor();
                extractor.extractTerms();

                System.out.println("Extraction of top terms complete.");
            }
        } catch(ParseException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
