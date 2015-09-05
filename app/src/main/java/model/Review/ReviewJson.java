package model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/27/2015.
 */
public class ReviewJson {



    private int id;
    private int page;
    //private List<Result> results = new ArrayList<Result>();
    private List<Review> reviews = new ArrayList<Review>();
    //private LinkedTreeMap results;
    private int totalPages;
    private int totalResults;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }




    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Review> getResults() {
        return reviews;
    }

    public void setResults(List<Review> reviews) {
        this.reviews = reviews;
    }
}
