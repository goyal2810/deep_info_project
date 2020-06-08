package com.example.deepinfo;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ModalClass {


    private int productImage;
    private String productDescription, productId, productPrice;


    ModalClass(String productDescription, String productId, String productPrice, int productImage){

        this.productDescription = productDescription;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public int getProductImage() { return productImage; }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductImage(int productImage) { this.productImage = productImage; }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ModalClass{" +
                "productImage=" + productImage +
                ", productDescription='" + productDescription + '\'' +
                ", productId='" + productId + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }

}
