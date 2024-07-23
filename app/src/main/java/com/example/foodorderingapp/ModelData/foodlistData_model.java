package com.example.foodorderingapp.ModelData;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class foodlistData_model {


    String  foodname, foodprice,foodimg;

    public String getFooddetail() {
        return fooddetail;
    }

    public void setFooddetail(String fooddetail) {
        this.fooddetail = fooddetail;
    }

    public int getFoodqyantity() {
        return foodqyantity;
    }

    public void setFoodqyantity(int foodqyantity) {
        this.foodqyantity = foodqyantity;
    }

    String fooddetail;
    int foodqyantity;

    public String getFoodimg() {
        return foodimg;
    }

    public void setFoodimg(String foodimg) {
        this.foodimg = foodimg;
    }

    //    public int getFoodimg(){
//        return foodimg;
//    }
//    public void setFoodimg(int foodimg){
//        this.foodimg = foodimg;
//    }
    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public foodlistData_model(String foodimg, String foodname, String foodprice) {
        this.foodimg = foodimg;
        this.foodname = foodname;
        this.foodprice = foodprice;
    }

    public foodlistData_model(String foodimg, String foodname, String foodprice,String fooddetail){
        this.foodimg = foodimg;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.fooddetail = fooddetail;
    }
}