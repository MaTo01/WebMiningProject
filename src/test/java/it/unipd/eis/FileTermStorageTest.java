package it.unipd.eis;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileTermStorageTest {
    private static final String FILE_PATH = "./Storage";
    private TermStorage termStorage;

    @BeforeEach
    public void setup() {
        termStorage = new FileTermStorage(FILE_PATH);
        termStorage.clearStorage();
    }

    @Test
    void testAddTerm() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        List<Term> terms = termStorage.getAllTerms();
        assertEquals(2, terms.size());
        assertTrue(terms.contains(term1));
        assertTrue(terms.contains(term2));
    }

    @Test
    void testRemoveTerm() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        termStorage.removeTerm(term1);

        List<Term> terms = termStorage.getAllTerms();
        assertEquals(1, terms.size());
        assertFalse(terms.contains(term1));
        assertTrue(terms.contains(term2));
    }

    @Test
    void testGetTopTermsByWeight() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("peron", 3);
        Term term3 = new Term("perone", 7);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);
        termStorage.addTerm(term3);

        int count = 2;
        List<Term> topTerms = termStorage.getTopTermsByWeight(count);

        assertEquals(count, topTerms.size());
        assertEquals(term3, topTerms.get(0)); // Highest weight
        assertEquals(term1, topTerms.get(1)); // Second-highest weight
    }

    @Test
    void testContainsTerm() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);

        assertTrue(termStorage.containsTerm(term1));
        assertFalse(termStorage.containsTerm(term2));
    }

    @Test
    void testClearStorage() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        assertFalse(termStorage.isEmpty());

        termStorage.clearStorage();

        assertTrue(termStorage.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(termStorage.isEmpty());

        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);
        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        assertFalse(termStorage.isEmpty());
    }



}