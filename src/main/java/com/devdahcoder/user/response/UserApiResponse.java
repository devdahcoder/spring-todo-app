package com.devdahcoder.user.response;

import java.util.List;

/**
 * Represents a generic API response for paginated data.
 *
 * @param <T> The type of data being returned in the response.
 */
public class UserApiResponse<T> {

    /**
     * The current page number.
     */
    private int page = 0;

    /**
     * The list of data items for the current page.
     */
    private List<T> data;

    /**
     * The maximum number of data items per page.
     */
    private int limit = 0;

    /**
     * The offset of the data items for the current page.
     */
    private int offSet = 0;

    /**
     * The total number of data items across all pages.
     */
    private int totalData = 0;

    /**
     * The total number of pages in the paginated data.
     */
    private int totalPage = 0;

    /**
     * The order in which data is sorted (e.g., ASC or DESC).
     */
    private String order = "ASC";

    /**
     * Indicates whether there is a next page of data.
     */
    private boolean hasNext = false;

    /**
     * The current total number of data items on the current page.
     */
    private int currentTotalData = 0;

    /**
     * A message describing the data content (e.g., "All data").
     */
    private String message = "All data";

    /**
     * Indicates whether there is a previous page of data.
     */
    private boolean hasPrevious = false;

    public UserApiResponse(int page, List<T> data, int limit, int offSet, int totalData, int totalPage, String order, boolean hasNext, int currentTotalData, String message, boolean hasPrevious) {

        this.page = page;
        this.data = data;
        this.limit = limit;
        this.offSet = offSet;
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.order = order;
        this.hasNext = hasNext;
        this.currentTotalData = currentTotalData;
        this.message = message;
        this.hasPrevious = hasPrevious;

    }

    public int getPage() {

        return page;

    }

    public List<T> getData() {

        return data;

    }

    public int getLimit() {

        return limit;

    }

    public int getOffSet() {

        return offSet;

    }

    public int getTotalData() {

        return totalData;

    }

    public int getTotalPage() {

        return totalPage;

    }

    public String getOrder() {

        return order;

    }

    public boolean isHasNext() {

        return hasNext;

    }

    public int getCurrentTotalData() {

        return currentTotalData;

    }

    public String getMessage() {

        return message;

    }

    public boolean isHasPrevious() {

        return hasPrevious;

    }

}
