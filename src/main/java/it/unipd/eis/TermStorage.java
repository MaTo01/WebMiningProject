package it.unipd.eis;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for storing and managing terms.
 */
public interface TermStorage {
    /**
     * Adds a term to the storage.
     *
     * @param term the term to be added
     */
    void addTerm(Term term);

    void addTerms(List<Term> terms);
    /**
     * Removes a term from the storage.
     *
     * @param term the term to be removed
     */
    void removeTerm(Term term);

    /**
     * Retrieves the top terms by weight from the storage.
     *
     * @param count the number of terms to retrieve
     * @return a list of terms, sorted in descending order by weight
     */
    ArrayList<Term> getTopTermsByWeight(int count);

    /**
     * Retrieves all the terms stored in the storage.
     *
     * @return a list of all the terms in the storage
     */
    ArrayList<Term> getAllTerms();

    /**
     * Retrieves the number of terms stored in the storage.
     *
     * @return the number of terms in the storage
     */
    int getTermCount();

    /**
     * Checks if the storage contains the specified term.
     *
     * @param term the term to be checked
     * @return {@code true} if the storage contains the term, {@code false} otherwise
     */
    boolean containsTerm(Term term);

    /**
     * Checks if the storage is empty.
     *
     * @return {@code true} if the storage is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Clears the storage, removing all the terms.
     */
    void clearStorage();
}
