package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private String[] names;
    private String[] prices;
    private int[] imageIds;
    private int[] quantitys;

    private List<Integer> filteredIndices;

    public ProductAdapter(Context context, String[] names, String[] prices, int[] imageIds, int[] quantitys) {
        this.context = context;
        this.names = names;
        this.prices = prices;
        this.imageIds = imageIds;
        this.quantitys = quantitys;

        filteredIndices = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            filteredIndices.add(i);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView priceText;
        public ImageView imageView;
        public TextView quantitys;
        public Button showMoreButton;

        public ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.product_name);
            priceText = view.findViewById(R.id.product_price);
            imageView = view.findViewById(R.id.product_image);
            quantitys = view.findViewById(R.id.product_quantitys);
            showMoreButton = view.findViewById(R.id.showMore);
        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int actualIndex = filteredIndices.get(position);

        holder.nameText.setText(names[actualIndex]);
        holder.priceText.setText(prices[actualIndex]);
        holder.imageView.setImageResource(imageIds[actualIndex]);
        holder.quantitys.setText("Quantity: " + quantitys[actualIndex]);

        holder.showMoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productName", names[actualIndex]);
            intent.putExtra("productPrice", prices[actualIndex]);
            intent.putExtra("productImage", imageIds[actualIndex]);
            intent.putExtra("Quantity", quantitys[actualIndex]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredIndices.size();
    }

    public void filterList(List<Integer> newFilteredIndices) {
        filteredIndices = newFilteredIndices;
        notifyDataSetChanged();
    }
}
