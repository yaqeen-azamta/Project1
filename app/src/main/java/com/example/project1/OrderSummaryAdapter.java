package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private List<Purchase> purchaseList;

    public OrderSummaryAdapter(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, size, color , quntity;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            price = itemView.findViewById(R.id.txtPrice);
            size = itemView.findViewById(R.id.txtSize);
            quntity =itemView.findViewById(R.id.txtqunt);
            color = itemView.findViewById(R.id.txtColor);
        }
    }

    @Override
    public OrderSummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderSummaryAdapter.ViewHolder holder, int position) {
        Purchase item = purchaseList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("price: " + item.getPrice());
        holder.size.setText("size: " + item.getSize());
        holder.quntity.setText("quntity: " + item.getQuantitys());
        holder.color.setText("coloe: " + item.getColor());

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }
}
