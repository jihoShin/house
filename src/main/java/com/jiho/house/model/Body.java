package com.jiho.house.model;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public class Body {

    private Items Items;

    private int numOfRows;

    private int pageNo;

    private int totalCount;


    public com.jiho.house.model.Items getItems() {
        return Items;
    }

    public void setItems(com.jiho.house.model.Items items) {
        Items = items;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
