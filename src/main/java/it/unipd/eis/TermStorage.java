package it.unipd.eis;

import java.util.List;

public interface TermStorage {
    void addTerm(Term term);
    void removeTerm(Term term);
    List<Term> getTopTermsByWeight(int count);
    List<Term> getAllTerms();
    int getTermCount();
    boolean containsTerm(Term term);
    boolean isEmpty();
    void clearStorage();
}
