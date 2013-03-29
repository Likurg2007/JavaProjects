package org.dpolianskyi.epam.delivery.paging;

/**
 *
 * @author Likurg
 */
public class Pagination {

    int recordsOnPage;
    int currentPage;
    boolean possiblePrev = false;
    boolean possibleNext = false;

    /**
     * Constructor
     *
     * @param recordsOnPage
     * @param currentPage begins from '0'
     */
    public Pagination(int recordsOnPage, int currentPage) {
        super();
        this.recordsOnPage = recordsOnPage;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isPossiblePrev() {
        return possiblePrev;
        
    }

    public void setPossiblePrev(boolean possiblePrev) {
        this.possiblePrev = possiblePrev;
    }

    public boolean isPossibleNext() {
        return possibleNext;
    }

    public void setPossibleNext(boolean possibleNext) {
        this.possibleNext = possibleNext;
    }

    public int getRecordsOnPage() {
        return recordsOnPage;
    }

    public void setRecordsOnPage(int recordsOnPage) {
        this.recordsOnPage = recordsOnPage;
    }
}