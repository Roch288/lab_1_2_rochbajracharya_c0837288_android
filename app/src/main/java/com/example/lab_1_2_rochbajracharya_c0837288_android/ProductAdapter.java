package com.example.lab_1_2_rochbajracharya_c0837288_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab_1_2_rochbajracharya_c0837288_android.room.Product;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.ProductRoomDb;

import java.util.List;

public class ProductAdapter extends ArrayAdapter {

    Context context;
    int layoutRes;
    List<Product> productList;
    ProductRoomDb productRoomDb;

    public ProductAdapter(@NonNull Context context, int resource, List<Product> productList) {
        super(context, resource, productList);
        this.productList = productList;
        this.context = context;
        this.layoutRes = resource;
        productRoomDb = ProductRoomDb.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView productName = v.findViewById(R.id.product_name);
        TextView productPrice = v.findViewById(R.id.product_price);

        LinearLayout item = v.findViewById(R.id.product);


        final Product product = productList.get(position);
        productName.setText(product.getProductName());
        productPrice.setText("$" + String.valueOf(product.getProductPrice()));

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra("productName",product.getProductName());
                intent.putExtra("id",product.getId());
                v.getContext().startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

}
