package com.example.foodorderingapp.ModelData;


public class MainMenu_listdata {

    public int Mfoodimg ;
    public String Mfoodname;
    public String Mfoodprice;

    public MainMenu_listdata(int mfoodimg, String mfoodname, String mfoodprice) {
        Mfoodimg = mfoodimg;
        Mfoodname = mfoodname;
        Mfoodprice = mfoodprice;
    }

    public int getMfoodimg() {
        return Mfoodimg;
    }

    public void setMfoodimg(int mfoodimg) {
        Mfoodimg = mfoodimg;
    }

    public String getMfoodname() {
        return Mfoodname;
    }

    public void setMfoodname(String mfoodname) {
        Mfoodname = mfoodname;
    }

    public String getMfoodprice() {
        return Mfoodprice;
    }

    public void setMfoodprice(String mfoodprice) {
        Mfoodprice = mfoodprice;
    }
}
