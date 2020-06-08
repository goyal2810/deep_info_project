package com.example.deepinfo;

import android.util.Log;

import java.util.ArrayList;

public class PurchaseItemList {


    public static ArrayList<PurchaseItemList> purchasedProduct = new ArrayList<>();
    String productPrice, productId, productTitle;

    public PurchaseItemList() { }

    PurchaseItemList(String productPrice, String productId, String productTitle){
        this.productId = productId;
        this.productPrice = productPrice;
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductArray(){
        purchasedProduct.add(new PurchaseItemList(productId,productTitle,productPrice));
    }

    public ArrayList<PurchaseItemList> displayArrayResult() {
        String pId, pDes, pPrice;
        int size = purchasedProduct.size();

        for (int i = 0; i < size; i++) {
            pId = purchasedProduct.get(i).getProductId();
            pDes = purchasedProduct.get(i).getProductTitle();
            pPrice = purchasedProduct.get(i).getProductPrice();

            Log.d("ArrayOutPut", "ProductId" + pId + "Description" + pDes + "Price Is " + pPrice);
        }
        return purchasedProduct;
    }
}
