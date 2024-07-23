package com.example.foodorderingapp.ModelData;

public class FoodItem_DetailModel {
    public String name;
    public String image;
    public String detail;
    public  String price;
    public int quantity;

    // Default constructor required for calls to DataSnapshot.getValue(FoodItem.class)
    public FoodItem_DetailModel() {}

    // Constructor to initialize all fields
    public FoodItem_DetailModel( String name, String image, String detail, int quantity,String price) {
        this.name = name;
        this.image = image;
        this.detail = detail;
        this.quantity = quantity;
        this.price = price;
    }
}
