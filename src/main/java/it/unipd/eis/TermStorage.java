package it.unipd.eis;

import java.util.List;
import java.util.Map;

public interface TermStorage {
    void addTerms(Map<String, Integer> terms);
    void removeTerm(String term);
    List<String> getTopTermsByWeight(int count);
    List<String> getAllTerms();
    int getTermCount();
    boolean containsTerm(String term);
    boolean isEmpty();
    void clearStorage();
}
