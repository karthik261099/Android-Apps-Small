package com.nimbleq.nimbleqe_commerce;

public class Order {

    String name,url,price,personName,personAddress;

    public Order(String name, String url, String price) {
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

    public String getPersonName() {
        return personName;
    }

    public String getPersonAddress() {
        return personAddress;
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

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }
}
