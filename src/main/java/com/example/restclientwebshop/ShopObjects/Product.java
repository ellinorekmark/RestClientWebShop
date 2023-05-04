package com.example.restclientwebshop.ShopObjects;

public class Product {

    public long id;

    public String name;
    public String description;

    public double price;

    public int inventory;

    public Category category;

    public Product() {
    }

    public Product(String name, String description, double price, int inventory,Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int stock) {
        this.inventory = stock;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(String category) {
        Category cat;
        switch(category){
            case("Fruit") -> cat = Category.FRUIT;
            case("Vegetable") -> cat = Category.VEGETABLE;
            default -> cat = Category.OTHER;
        }
        this.category = cat;
    }
    public void addInventory(int x) {
        if (x > 0) {
            inventory = inventory + x;
        }
    }

    public String toString(){
        return "Item Id: "+ getId() + " - Name: " + getName() + " - In Stock: " + getInventory() + " - Price: "+ getPrice();
    }




}
