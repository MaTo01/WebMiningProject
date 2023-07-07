package it.unipd.eis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TermExtractor {
    private static FileTermStorage termStorage = new FileTermStorage("Storage");
    private static FileArticleStorage articleStorage = new FileArticleStorage("Storage");
    private static final int numTermsToSave = 50;
    private static final ArrayList<String> stopList = getStopList();

    public TermExtractor() {
        termStorage.clearStorage();
    }

    public void extractTerms() {
        ArrayList<Article> articles = articleStorage.getAllArticles();
        ArrayList<Term> terms = new ArrayList<>();

        for(Article a : articles) {
            String[] title = splitAndReformat(a.getTitle());
            String[] body = splitAndReformat(a.getBody());

            for(String t : title) {
                countTerm(terms, t.toLowerCase());
            }
            for(String t : body) {
                countTerm(terms, t.toLowerCase());
            }
        }

        Collections.sort(terms);

        ArrayList<Term> topTerms = new ArrayList<>();
        for(int i = 0; i < numTermsToSave; i++) {
            topTerms.add(terms.get(i));
        }
        termStorage.addTerms(topTerms);
    }

    private static ArrayList<String> getStopList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Sources/stopList.txt"))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void countTerm(ArrayList<Term> terms, String t) {
        if(!t.equals("") && !stopList.contains(t.toLowerCase())) {
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

    private String[] splitAndReformat(String s) {
        s = s.replaceAll("’", " ");
        s = s.replaceAll("[^a-zA-Z0-9\\s]", "");
        return s.split(" ");
    }
}
