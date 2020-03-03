package com.samu.volly_crud;



public class Product_2 {
    private int id;
    private String name, price;



    public Product_2(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}