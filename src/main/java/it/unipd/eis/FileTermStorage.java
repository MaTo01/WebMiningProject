package it.unipd.eis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTermStorage implements TermStorage {
    private String filePath;

    public FileTermStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void addTerms(Map<String, Integer> terms) {
        Map<String, Integer> existingTerms = getAllTermsWithWeights();
        existingTerms.putAll(terms);
        saveTerms(existingTerms);
    }

    @Override
    public void removeTerm(String term) {
        Map<String, Integer> existingTerms = getAllTermsWithWeights();
        existingTerms.remove(term);
        saveTerms(existingTerms);
    }

    @Override
    public List<String> getTopTermsByWeight(int count) {
        Map<String, Integer> terms = getAllTermsWithWeights();
        List<String> topTerms = new ArrayList<>();

        terms.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(count)
                .forEach(entry -> topTerms.add(entry.getKey()));

        return topTerms;
    }

    @Override
    public List<String> getAllTerms() {
        Map<String, Integer> terms = getAllTermsWithWeights();
        return new ArrayList<>(terms.keySet());
    }

    @Override
    public int getTermCount() {
        Map<String, Integer> terms = getAllTermsWithWeights();
        return terms.size();
    }

    @Override
    public boolean containsTerm(String term) {
        Map<String, Integer> terms = getAllTermsWithWeights();
        return terms.containsKey(term);
    }

    @Override
    public boolean isEmpty() {
        Map<String, Integer> terms = getAllTermsWithWeights();
        return terms.isEmpty();
    }

    @Override
    public void clearStorage() {
        saveTerms(new HashMap<>());
    }

    private Map<String, Integer> getAllTermsWithWeights() {
        Map<String, Integer> terms = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
            terms = (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return terms;
    }

    private void saveTerms(Map<String, Integer> terms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            oos.writeObject(terms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}