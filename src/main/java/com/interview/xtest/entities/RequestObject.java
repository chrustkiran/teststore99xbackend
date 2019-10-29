package com.interview.xtest.entities;

public class RequestObject {
    private int id;
    private String name;
    private int total;
    private double price;
    private int number_of_box;
    private int number_of_item;

    public RequestObject(int id, String name, int total, int price, int number_of_box, int number_of_item) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.price = price;
        this.number_of_box = number_of_box;
        this.number_of_item = number_of_item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_box() {
        return number_of_box;
    }

    public void setNumber_of_box(int number_of_box) {
        this.number_of_box = number_of_box;
    }

    public int getNumber_of_item() {
        return number_of_item;
    }

    public void setNumber_of_item(int number_of_item) {
        this.number_of_item = number_of_item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
