package com.example.project1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    private RecyclerView orderSummaryRecyclerView;
    private PurchaseAdapter summaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderSummaryRecyclerView = findViewById(R.id.orderSummaryRecyclerView);
        orderSummaryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Purchase> savedPurchases = PurchaseStorageHelper.getPurchasesFromSharedPreferences(this);

        summaryAdapter = new PurchaseAdapter(savedPurchases);
        orderSummaryRecyclerView.setAdapter(summaryAdapter);
    }
}
