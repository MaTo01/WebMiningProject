package it.unipd.eis;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileTermStorage implements TermStorage {
    private final String filePath;

    public FileTermStorage(String filePath) {
        this.filePath = filePath + "/terms.txt";
        createDirectoryIfNotExists();
    }

    @Override
    public void addTerm(Term term) {
        List<Term> existingTerms = getAllTerms();
        existingTerms.add(term);
        saveTerms(existingTerms);
    }

    @Override
    public void removeTerm(Term term) {
        List<Term> existingTerms = getAllTerms();
        existingTerms.remove(term);
        saveTerms(existingTerms);
    }

    @Override
    public List<Term> getTopTermsByWeight(int count) {
        List<Term> terms = getAllTerms();
        return terms.stream()
                .sorted(Comparator.comparingInt(Term::getWeight).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

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

    @Override
    public int getTermCount() {
        List<Term> terms = getAllTerms();
        return terms.size();
    }

    @Override
    public boolean containsTerm(Term term) {
        List<Term> terms = getAllTerms();
        return terms.contains(term);
    }

    @Override
    public boolean isEmpty() {
        return getTermCount() == 0;
    }

    @Override
    public void clearStorage() {
        saveTerms(new ArrayList<>());
    }

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

    private void createDirectoryIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}