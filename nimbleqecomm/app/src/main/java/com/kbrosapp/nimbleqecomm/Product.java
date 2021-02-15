package com.kbrosapp.nimbleqecomm;

public class Product {
    String name,url,price;

    Product(){

    }

    public Product(String name, String url, String price) {
        this.name = name;
        this.url = url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
