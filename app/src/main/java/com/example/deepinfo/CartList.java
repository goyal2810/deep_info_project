package com.example.deepinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.evrencoskun.tableview.TableView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartList extends AppCompatActivity {

    public static ArrayList<PurchaseItemList> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        ListView puchasedList = (ListView) findViewById(R.id.purchased_list);
        TextView noItemView = (TextView) findViewById(R.id.cart_nil);
        PurchaseItemList productInfo = new PurchaseItemList();
        details = productInfo.displayArrayResult();

        if(details.size() != 0){
            noItemView.setVisibility(View.GONE);
            PurchasedProductAdapter adapter = new PurchasedProductAdapter(this, R.layout.purchased_product_layout, details);
            puchasedList.setAdapter(adapter);
        } else {
            noItemView.setVisibility(View.VISIBLE);
        }


    }
}
