package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button  LogInB;
    private Animation top, bottom;
    private TextView txt;
    private ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        txt = findViewById(R.id.welcomeTV);
        img = findViewById(R.id.webImg);
        LogInB =findViewById(R.id.LogInB);

        txt.setAnimation(bottom);
        img.setAnimation(top);
        LogInB.setAnimation(bottom);


        LogInB = findViewById(R.id.LogInB);
        LogInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    public static class PurchasedItem {
        private String name;
        private String size;
        private int quantity;
        private String imageUrl;

        public PurchasedItem() {
            // Required for Firebase
        }

        public PurchasedItem(String name, String size, int quantity, String imageUrl) {
            this.name = name;
            this.size = size;
            this.quantity = quantity;
            this.imageUrl = imageUrl;
        }

        public String getName() { return name; }
        public String getSize() { return size; }
        public int getQuantity() { return quantity; }
        public String getImageUrl() { return imageUrl; }
    }
}