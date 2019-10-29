package com.interview.xtest.entities;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private List<Item> items;

    public ItemList(){
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
