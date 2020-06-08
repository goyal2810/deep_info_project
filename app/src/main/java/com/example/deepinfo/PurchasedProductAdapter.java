package com.example.deepinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

class PurchasedProductAdapter extends ArrayAdapter<PurchaseItemList> {

    private Context mContext;
    private int mResource;
    public PurchasedProductAdapter(@NonNull Context context, int resource, ArrayList<PurchaseItemList> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String pId = getItem(position).getProductId();
        String pDes = getItem(position).getProductTitle();
        String pPrice = getItem(position).getProductPrice();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView idTextView = (TextView) convertView.findViewById(R.id.product_id_info);
        TextView desTextView = (TextView) convertView.findViewById(R.id.product_description_info);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.product_price_info);

        idTextView.setText(pId);
        desTextView.setText(pDes);
        priceTextView.setText(pPrice);

        return convertView;
    }
}
