package it.unipd.eis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TermExtractor {
    private TermStorage termStorage;
    private ArticleStorage articleStorage;
    private final int numTermsToSave;
    private static final ArrayList<String> stopList = getStopList();

    public TermExtractor(int num) {
        numTermsToSave = num;
    }

    public void setArticleStorage(ArticleStorage articleStorage) {
        this.articleStorage = articleStorage;
    }

    public void setTermStorage(TermStorage termStorage) {
        this.termStorage = termStorage;
    }

    public void extractTerms() {
        ArrayList<Article> articles = articleStorage.getAllArticles();
        if(articles == null || articles.size() == 0) {
            throw new IllegalStateException("Error retrieving articles (articles.json file may be missing or empty).");
        }

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
        for(int i = 0; i < numTermsToSave && i < terms.size(); i++) {
            topTerms.add(terms.get(i));
        }
        termStorage.addTerms(topTerms);
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
        s = s.replaceAll("[^a-zA-Z0-9\\s]", " ");
        return s.split(" ");
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
        return new ArrayList<>();
    }
}
