package com.example.lab_1_2_rochbajracharya_c0837288_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab_1_2_rochbajracharya_c0837288_android.room.Product;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.ProductRoomDb;

public class ProductDetailActivity extends AppCompatActivity {

    TextView productNameLabel, productDesc, productPrice;

    private ProductRoomDb productRoomDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String productName = getIntent().getStringExtra("productName");
        int id = getIntent().getIntExtra("id",0);

        getSupportActionBar().setTitle(productName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productNameLabel = findViewById(R.id.product_name);
        productDesc = findViewById(R.id.productDescription_label);
        productPrice = findViewById(R.id.productPrice_label);

        productRoomDb = ProductRoomDb.getInstance(this);

        Product currentProduct = productRoomDb.productDao().getProductById(id);

        productNameLabel.setText(currentProduct.getProductName());
        productDesc.setText(currentProduct.getProductDescription());
        productPrice.setText(String.valueOf(currentProduct.getProductPrice()));

    }
}