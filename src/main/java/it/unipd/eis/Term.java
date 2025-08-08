package it.unipd.eis;

import java.util.Objects;

/**
 * A class representing a term.
 */
public class Term implements Comparable<Term> {
    private String term;
    private int weight;

    /**
     * Constructs a Term object with the specified term string and default weight of 0.
     *
     * @param term the term string
     */
    public Term(String term) {
        this.term = term;
        this.weight = 0;
    }
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
        return this.term.equals(other.getTerm());
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

    /**
     * Compares this Term object with the specified Term object for order.
     * The comparison is based on the weight of the terms.
     * If the weights are equal, the terms are compared lexicographically.
     *
     * @param o the Term object to be compared
     * @return a negative integer, zero, or a positive integer as this Term is less than, equal to, or greater than the specified Term
     */
    @Override
    public int compareTo(Term o) {
        if(this.weight == o.weight) {
            if(this.term.compareTo(o.term) > 0)
                return 1;
            else
                return -1;
        } else if (this.weight > o.weight) {
            return -1;
        } else {
            return 1;
        }
    }
}