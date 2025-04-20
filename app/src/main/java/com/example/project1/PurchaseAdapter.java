package com.example.project1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {
    private List<Purchase> purchaseList;

    public PurchaseAdapter(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public void clearPurchases() {
        purchaseList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productSize, productColor ,productQunt ;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.purchasedImage);
            productName = itemView.findViewById(R.id.purchasedName);
            productPrice = itemView.findViewById(R.id.purchasedPrice);
            productSize = itemView.findViewById(R.id.purchasedSize);
            productColor = itemView.findViewById(R.id.purchasedColor);
            productQunt=itemView.findViewById(R.id.purchasedQunt);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_purchase, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Purchase purchase = purchaseList.get(position);
        holder.productName.setText(purchase.getName());
        holder.productPrice.setText(purchase.getPrice());
        holder.productImage.setImageResource(purchase.getImageResId());
        holder.productSize.setText("Size: " + purchase.getSize());
        holder.productColor.setText("Color: " + purchase.getColor());
        holder.productColor.setText("Color: " + purchase.getColor());
        holder.productQunt.setText("qquantity:"+purchase.getQuantitys());
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }
}
