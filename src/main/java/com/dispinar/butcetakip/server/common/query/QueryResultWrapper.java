package com.dispinar.butcetakip.server.common.query;

import java.util.List;

public class QueryResultWrapper<T extends Object> {

    private Long numberOfItems;

    private List<T> itemList;

    public QueryResultWrapper(Long numberOfItems, List<T> itemList){
        this.numberOfItems = numberOfItems;
        this.itemList = itemList;
    }

    public Long getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }
}
