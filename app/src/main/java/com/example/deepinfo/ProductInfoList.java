package com.example.deepinfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoList extends RecyclerView.Adapter<ProductInfoList.myViewHolder> {

    List<ModalClass> productDetail;

    public ProductInfoList(List<ModalClass> productDetail) {
        this.productDetail = productDetail;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_design, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ModalClass modalClass = productDetail.get(position);
        holder.productDescription.setText(modalClass.getProductDescription());
        holder.productPrice.setText(modalClass.getProductPrice());
        holder.productId.setText(modalClass.getProductId());
        holder.productImage.setImageResource(modalClass.getProductImage());

       final String description, id, price;
        description = holder.productDescription.getText().toString();
        id= holder.productId.getText().toString();
        price = holder.productPrice.getText().toString();

        holder.purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseItemList addToList = new PurchaseItemList(price, id, description);
                addToList.setProductArray();
                addToList.displayArrayResult();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetail.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView productDescription, productId, productPrice;
        ImageView productImage;
        Button purchaseButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            productDescription = itemView.findViewById(R.id.product_description);
            productId = itemView.findViewById(R.id.product_id);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
            purchaseButton = (Button) itemView.findViewById(R.id.product_purchase);

//            ModalClass modalClass = productDetail.get(getAdapterPosition());
//            notifyItemChanged(getAdapterPosition());
//            itemView.setOnClickListener(this);

        }
    }
}
