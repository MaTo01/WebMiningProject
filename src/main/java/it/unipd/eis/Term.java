package it.unipd.eis;

import java.util.Objects;

/**
 * A class representing a term.
 */
public class Term {
    private String term;
    private int weight;

    /**
     * Constructs a Term object with the specified term and weight.
     *
     * @param term   the term
     * @param weight the weight of the term
     */
    public Term(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }

    /**
     * Returns the term.
     *
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Returns the weight of the term.
     *
     * @return the weight of the term
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the term.
     *
     * @param term the term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Sets the weight of the term.
     *
     * @param weight the weight of the term
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Checks if this term is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the terms are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Term other = (Term) obj;
        return Objects.equals(term, other.term);
    }

    /**
     * Returns the hash code value for this term.
     *
     * @return the hash code value for this term
     */
    @Override
    public int hashCode() {
        return Objects.hash(term);
    }
}