package com.example.project1;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ProductDetails extends AppCompatActivity {

    private TextView productName, productPrice;
    private ImageView productImage;
    private Spinner sizeSpinner, colorSpinner, SpinnerQunt;
    private Button addButton;
    private String selectedSize = "Medium", selectedColor = "Red";
    private int imageResId;
    private int productIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailsactivity);

        productName = findViewById(R.id.txtProductName);
        productPrice = findViewById(R.id.txtProductPrice);
        productImage = findViewById(R.id.imgProduct);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        colorSpinner = findViewById(R.id.colorSpinner);
        SpinnerQunt = findViewById(R.id.SpinnerQunt);
        addButton = findViewById(R.id.addToCartButton);


        Intent intent = getIntent();
        String name = intent.getStringExtra("productName");
        String price = intent.getStringExtra("productPrice");
        imageResId = intent.getIntExtra("productImage", 0);

        productName.setText(name);
        productPrice.setText(price);
        productImage.setImageResource(imageResId);

        for (int i = 0; i < Product.product.length; i++) {
            if (Product.product[i].getName().equals(name)) {
                productIndex = i;
                break;
            }
        }

        if (productIndex == -1) {
            Toast.makeText(this, "wrong loading item", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int quantityAvailable = Product.product[productIndex].getQuantity();

        if (quantityAvailable == 0) {
            addButton.setEnabled(false);
            Toast.makeText(this, "Out of stock", Toast.LENGTH_LONG).show();
        }

        //qunt spinner
        Integer[] quantities = new Integer[quantityAvailable];
        for (int i = 0; i < quantityAvailable; i++) {
            quantities[i] = i + 1;
        }

        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantities);
        SpinnerQunt.setAdapter(quantityAdapter);

        // size spinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Small", "Medium", "Large", "XL","XXL"});
        sizeSpinner.setAdapter(sizeAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSize = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

       // color spinner
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Red", "Black", "White", "Blue","YELLOW","GREEN"});
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedColor = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });


        addButton.setOnClickListener(v -> {
            int selectedQty = Integer.parseInt(SpinnerQunt.getSelectedItem().toString());
            int availableQty = Product.product[productIndex].getQuantity();

            if (selectedQty > availableQty) {
                Toast.makeText(this, "In less than what You chose", Toast.LENGTH_SHORT).show();
                return;
            }

            // add to list shop
            Purchase purchase = new Purchase(
                    productName.getText().toString(),
                    productPrice.getText().toString(),
                    imageResId,
                    selectedSize,
                    selectedColor,
                    selectedQty

            );
            CartStorage.cartList.add(purchase);

           // update
            Product.product[productIndex].setQuantity(availableQty - selectedQty);

            Toast.makeText(this, "Add to list shop", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(ProductDetails.this, MyPurchasesActivity.class);
            startActivity(i);
        });
    }
}
