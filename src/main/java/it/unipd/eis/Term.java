package it.unipd.eis;

import java.util.Objects;

public class Term {
    private String term;
    private int weight;

    public Term(String term, int weight) {
        this.term = term;
        this.weight = weight;
    }

    public String getTerm() {
        return term;
    }

    public int getWeight() {
        return weight;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(term);
    }
}