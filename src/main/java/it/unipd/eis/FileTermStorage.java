package it.unipd.eis;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that implements the {@link TermStorage} interface and provides storage for terms
 * in a file-based storage system.
 */
public class FileTermStorage implements TermStorage {
    private final String filePath;

    /**
     * Constructs a FileTermStorage object with the specified file path.
     *
     * @param filePath the path to the file used for storing the terms
     */
    public FileTermStorage(String filePath) {
        this.filePath = filePath + "/terms.txt";
        StorageUtils.createDirectoryIfNotExists(filePath);
    }

    /**
     * Adds a term to the storage.
     *
     * @param term the term to add
     */
    @Override
    public void addTerm(Term term) {
        List<Term> existingTerms = getAllTerms();
        existingTerms.add(term);
        saveTerms(existingTerms);
    }

    /**
     * Removes a term from the storage.
     *
     * @param term the term to remove
     */
    @Override
    public void removeTerm(Term term) {
        List<Term> existingTerms = getAllTerms();
        existingTerms.remove(term);
        saveTerms(existingTerms);
    }

    /**
     * Retrieves the top terms by weight from the storage.
     *
     * @param count the number of terms to retrieve
     * @return a list of terms, sorted in descending order by weight
     */
    @Override
    public List<Term> getTopTermsByWeight(int count) {
        List<Term> terms = getAllTerms();
        return terms.stream()
                .sorted(Comparator.comparingInt(Term::getWeight).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all the terms stored in the file.
     *
     * @return a list of all the terms in the storage
     */
    @Override
    public List<Term> getAllTerms() {
        List<Term> terms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String termName = parts[0].trim();
                    int weight = Integer.parseInt(parts[1].trim());
                    Term term = new Term(termName, weight);
                    terms.add(term);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return terms;
    }

    /**
     * Returns the number of terms in the storage.
     *
     * @return the number of terms
     */
    @Override
    public int getTermCount() {
        List<Term> terms = getAllTerms();
        return terms.size();
    }

    /**
     * Checks if the storage contains the specified term.
     *
     * @param term the term to check
     * @return true if the storage contains the term, false otherwise
     */
    @Override
    public boolean containsTerm(Term term) {
        List<Term> terms = getAllTerms();
        return terms.contains(term);
    }

    /**
     * Checks if the storage is empty.
     *
     * @return true if the storage is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return getTermCount() == 0;
    }

    /**
     * Clears the storage, removing all the terms.
     */
    @Override
    public void clearStorage() {
        saveTerms(new ArrayList<>());
    }

    /**
     * Saves the list of terms to the file.
     *
     * @param terms the list of terms to save
     */
    private void saveTerms(List<Term> terms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Term term : terms) {
                writer.write(term.getTerm() + ": " + term.getWeight());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}