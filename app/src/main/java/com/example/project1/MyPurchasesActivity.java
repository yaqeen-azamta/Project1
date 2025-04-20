package com.example.project1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class MyPurchasesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PurchaseAdapter purchasesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);

        recyclerView = findViewById(R.id.purchasesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        purchasesAdapter = new PurchaseAdapter(CartStorage.cartList);
        recyclerView.setAdapter(purchasesAdapter);

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            List<Purchase> oldPurchases = PurchaseStorageHelper.getPurchasesFromSharedPreferences(MyPurchasesActivity.this);
            List<Purchase> newPurchases = new ArrayList<>(oldPurchases);

            newPurchases.addAll(CartStorage.cartList);

            PurchaseStorageHelper.savePurchasesToSharedPreferences(MyPurchasesActivity.this, newPurchases);
            CartStorage.cartList.clear();

            purchasesAdapter.clearPurchases();

            Intent intent = new Intent(MyPurchasesActivity.this, OrderSummaryActivity.class);
            startActivity(intent);
        });
    }
}
