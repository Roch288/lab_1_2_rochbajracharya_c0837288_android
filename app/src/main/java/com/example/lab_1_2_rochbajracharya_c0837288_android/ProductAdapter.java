package com.example.lab_1_2_rochbajracharya_c0837288_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_rochbajracharya_c0837288_android.ProductDetailActivity;
import com.example.lab_1_2_rochbajracharya_c0837288_android.R;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.Product;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.ProductRoomDb;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    int layoutRes;
    List<Product> productList;
    ProductRoomDb productRoomDb;

    public ProductAdapter(@NonNull Context context, int resource, List<Product> productList) {
        this.productList = productList;
        this.context = context;
        this.layoutRes = resource;
        productRoomDb = ProductRoomDb.getInstance(context);
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText("$" + String.valueOf(product.getProductPrice()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProductDetailActivity.class);
            intent.putExtra("productName", product.getProductName());
            intent.putExtra("id", product.getId());
            view.getContext().startActivity(intent);
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName;
        TextView productPrice;
        LinearLayout item;

        public MyViewHolder(View v) {
            super(v);

            productName = v.findViewById(R.id.product_name);
            productPrice = v.findViewById(R.id.product_price);
            item = v.findViewById(R.id.product);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void removeItem(int position) {
        ProductRoomDb.getInstance(context).productDao().delete(productList.get(position));
        productList.remove(position);
        notifyItemRemoved(position);
    }

}
