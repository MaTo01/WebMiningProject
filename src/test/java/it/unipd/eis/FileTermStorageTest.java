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

        ArrayList<Term> terms = termStorage.getAllTerms();
        assertEquals(2, terms.size());
        assertTrue(terms.contains(term1));
        assertTrue(terms.contains(term2));
    }

    @Test
    void testAddTerms() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);
        Term term3 = new Term("mela", 7);

        ArrayList<Term> terms = new ArrayList<>();
        terms.add(term1);
        terms.add(term2);
        terms.add(term3);

        termStorage.addTerms(terms);

        ArrayList<Term> storedTerms = termStorage.getAllTerms();
        assertEquals(3, storedTerms.size());
        assertTrue(storedTerms.contains(term1));
        assertTrue(storedTerms.contains(term2));
        assertTrue(storedTerms.contains(term3));
    }

    @Test
    void testRemoveTerm() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        termStorage.removeTerm(term1);

        ArrayList<Term> terms = termStorage.getAllTerms();
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
        assertEquals(term3, topTerms.get(0));
        assertEquals(term1, topTerms.get(1));
    }

    @Test
    void testGetAllTerms() {
        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);

        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        ArrayList<Term> terms = termStorage.getAllTerms();
        assertEquals(2, terms.size());
        assertTrue(terms.contains(term1));
        assertTrue(terms.contains(term2));
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