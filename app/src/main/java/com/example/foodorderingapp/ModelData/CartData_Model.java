package com.example.foodorderingapp.ModelData;


public class CartData_Model {

    String CartItems, CarItemPrice,Cartimg,CartDetail;
    int CartQuantity;

    public String getCartimg() {
        return Cartimg;
    }

    public String getCartDetail() {
        return CartDetail;
    }

    public void setCartDetail(String cartDetail) {
        CartDetail = cartDetail;
    }

    public void setCartimg(String cartimg) {
        Cartimg = cartimg;
    }

    public String getCartItems() {
        return CartItems;
    }



    public void setCartItems(String cartItems) {
        CartItems = cartItems;
    }

    public int getCartQuantity() {
        return CartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        CartQuantity = cartQuantity;
    }

    public String getCarItemPrice() {
        return CarItemPrice;
    }

    public void setCarItemPrice(String carItemPrice) {
        CarItemPrice = carItemPrice;
    }

    public CartData_Model( String cartItems, String carItemPrice,String cartimg,int cartquantity,String cartDetail) {
        Cartimg = cartimg;
        CartItems = cartItems;
        CarItemPrice = carItemPrice;
        CartQuantity = cartquantity;
        CartDetail = cartDetail;
    }
}
