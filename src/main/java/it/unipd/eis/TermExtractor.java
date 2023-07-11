package it.unipd.eis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TermExtractor {
    private final int MAX_TERMS_TO_SAVE;
    private static final String STOPLIST_FILE_PATH = "Sources/stopList.txt";
    private static final ArrayList<String> STOPLIST = loadStopList();
    private TermStorage termStorage;
    private ArticleStorage articleStorage;
    private ArrayList<Term> terms;

    /**
     * Constructs a TermExtractor using the specified maximum number of terms to save.
     * @param numTermsToSave the maximum number of terms to save
     */
    public TermExtractor(int numTermsToSave) {
        MAX_TERMS_TO_SAVE = numTermsToSave;
        terms = new ArrayList<>();
    }

    /**
     * Sets the ArticleStorage to be used by the TermExtractor.
     * @param articleStorage the ArticleStorage to be used
     */
    public void setArticleStorage(ArticleStorage articleStorage) {
        this.articleStorage = articleStorage;
    }

    /**
     * Sets the TermStorage to be used by the TermExtractor.
     * @param termStorage the TermStorage to be used
     */
    public void setTermStorage(TermStorage termStorage) {
        this.termStorage = termStorage;
    }

    /**
     * Extracts the top terms from the articles in the ArticleStorage and saves them using the TermStorage.
     */
    public void extractTerms() {
        ArrayList<Article> articles = articleStorage.getAllArticles();
        if(articles == null || articles.size() == 0) {
            throw new IllegalStateException("Error retrieving articles (articles.json file may be missing or empty).");
        }

        for(Article a : articles) {
            String[] title = splitAndReformat(a.getTitle());
            String[] body = splitAndReformat(a.getBody());

            for(String t : title) {
                countTerm(t.toLowerCase());
            }
            for(String t : body) {
                countTerm(t.toLowerCase());
            }
        }

        Collections.sort(terms);
        serializeTerms();
    }

    /**
     * Serializes the top MAX_TERMS_TO_SAVE Terms
     */
    private void serializeTerms() {
        ArrayList<Term> topTerms = new ArrayList<>();
        for(int i = 0; i < MAX_TERMS_TO_SAVE && i < terms.size(); i++) {
            topTerms.add(terms.get(i));
        }
        termStorage.addTerms(topTerms);
    }

    /**
     * Adds a previously not encountered Term into the terms list,
     * increments its weight if it has already been encountered,
     * or ignores it if it's in the stoplist
     * @param t the term in String format to be checked
     */
    private void countTerm(String t) {
        if(!t.equals("") && !STOPLIST.contains(t.toLowerCase())) {
            Term aux = new Term(t);
            if(terms.contains(aux)) {
                int index = terms.indexOf(aux);
                terms.get(index).setWeight(terms.get(index).getWeight() + 1);
            } else {
                aux.setWeight(1);
                terms.add(aux);
            }
        }
    }

    /**
     * Removes all punctuation and symbols from a String
     * and splits it based on whitespaces into individual words
     * @param s the String to be processed
     * @return an array of Strings representing all the individual words
     */
    private String[] splitAndReformat(String s) {
        s = s.replaceAll("[^a-zA-Z0-9\\s]", " ");
        return s.split(" ");
    }

    /**
     * Static method to read the stoplist from the specified text file
     * @return an ArrayList containing the individual items of the stoplist
     */
    private static ArrayList<String> loadStopList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STOPLIST_FILE_PATH))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
