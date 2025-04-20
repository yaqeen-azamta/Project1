package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private androidx.appcompat.widget.SearchView searchView;
    private Button btBuy;
    String[] names;
    String[] prices;
    int[] imageIds;
    int[] quantitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adp);


        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.search);
        btBuy =findViewById(R.id.showBuy);
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, OrderSummaryActivity.class);
                startActivity(intent);
            }
        });

        if (searchView != null) {
            searchView.clearFocus();
        }
        names = new String[Product.product.length];
        prices = new String[Product.product.length];
        imageIds = new int[Product.product.length];
        quantitys = new int[Product.product.length];

        for (int i = 0; i < Product.product.length; i++) {
            names[i] = Product.product[i].getName();
            prices[i] = Product.product[i].getPrice();
            imageIds[i] = Product.product[i].getImageResId();
            quantitys[i] = Product.product[i].getQuantity();
        }
        adapter = new ProductAdapter(this, names, prices, imageIds, quantitys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (searchView != null) {
            searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {  // استخدام النوع الصحيح هنا
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    searchList(newText);
                    return true;
                }
            });
        }
    }
    private void searchList(String text) {
        ArrayList<Integer> resultIndices = new ArrayList<>();

        if (text.isEmpty()) {
            for (int i = 0; i < names.length; i++) {
                resultIndices.add(i);
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                if (names[i].toLowerCase().contains(text.toLowerCase())) {
                    resultIndices.add(i);
                }
            }
        }
        adapter.filterList(resultIndices);
    }
}
