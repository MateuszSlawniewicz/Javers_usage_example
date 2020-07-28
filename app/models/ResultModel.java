package models;

import java.util.Collections;
import java.util.List;

public class ResultModel {

    private String next;
    private String previous;
    private List<QuoteDTO> results = Collections.emptyList();

    public ResultModel() {
    }

    public ResultModel(String next, String previous, List<QuoteDTO> results) {
        this.next = next;
        this.previous = previous;
        this.results = results;
    }


    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<QuoteDTO> getResults() {
        return results;
    }

    public void setResults(List<QuoteDTO> results) {
        this.results = results;
    }
}
