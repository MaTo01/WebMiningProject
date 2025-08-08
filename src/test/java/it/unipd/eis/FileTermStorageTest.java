package it.unipd.eis;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileTermStorageTest {
    private static final String DIR_PATH = "./Storage/Test";
    private TermStorage termStorage;

    @BeforeEach
    public void setup() {
        termStorage = new FileTermStorage(DIR_PATH);
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
        ArrayList<Term> terms = new ArrayList<>();
        terms.add(term1);
        terms.add(term2);

        termStorage.addTerms(terms);

        List<Term> termsTest = termStorage.getAllTerms();
        assertEquals(2, termsTest.size());
        assertTrue(termsTest.contains(term1));
        assertTrue(termsTest.contains(term2));
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
    void testIsEmpty() {
        assertTrue(termStorage.isEmpty());

        Term term1 = new Term("zio", 5);
        Term term2 = new Term("pera", 3);
        termStorage.addTerm(term1);
        termStorage.addTerm(term2);

        assertFalse(termStorage.isEmpty());
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
}