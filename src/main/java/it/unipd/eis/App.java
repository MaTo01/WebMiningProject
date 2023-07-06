package it.unipd.eis;

import org.apache.commons.cli.*;

public class App 
{
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("d", true, "Download articles based on a key word/phrase");
        options.addOption("e", false, "Extract the terms with the highest weight");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if(cmd.hasOption("d")) {
                TheGuardianAPISource guardianSource = new TheGuardianAPISource();
                CSVSource csvSource = new CSVSource();

                //controllo su valore null
                guardianSource.downloadArticles(cmd.getOptionValue("d"));
            }
        } catch(ParseException pe) {

        }
    }
}
