package com.example.lab_1_2_rochbajracharya_c0837288_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_1_2_rochbajracharya_c0837288_android.R;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.Product;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.ProductRoomDb;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    EditText productNameLabel, productDesc, productPrice;

    MaterialButton btnEditProduct;

    private ProductRoomDb productRoomDb;

    static int id = 0;

    Product currentProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String productName = getIntent().getStringExtra("productName");

        getSupportActionBar().setTitle(productName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initialize();
    }

    void initialize() {
        productNameLabel = findViewById(R.id.productName_label);
        productDesc = findViewById(R.id.productDescription_label);
        productPrice = findViewById(R.id.productPrice_label);

        btnEditProduct = findViewById(R.id.button_edit);

        productRoomDb = ProductRoomDb.getInstance(this);

        id = getIntent().getIntExtra("id", 0);

        currentProduct = productRoomDb.productDao().getProductById(id);

        productNameLabel.setText(currentProduct.getProductName());
        productDesc.setText(currentProduct.getProductDescription());
        productPrice.setText(String.valueOf(currentProduct.getProductPrice()));

        btnEditProduct.setOnClickListener(this);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_edit) {

            if (checkAllValues()){
                Product currentProduct1 = productRoomDb.productDao().getProductById(id);

                currentProduct1.setProductPrice(Double.parseDouble(productPrice.getText().toString()));
                currentProduct1.setProductDescription(productDesc.getText().toString());
                currentProduct1.setProductName(productNameLabel.getText().toString());
                currentProduct1.setLatitude(currentProduct.getLatitude());
                currentProduct1.setLongitude(currentProduct.getLongitude());

                productRoomDb.productDao().update(currentProduct1);
                Toast.makeText(this, "Product Edited Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    boolean checkAllValues() {
        if (productNameLabel.getText().toString().contentEquals("")) {
            productNameLabel.setError("Field Required");
            return false;
        }
        if (productPrice.getText().toString().contentEquals("")) {
            productPrice.setError("Field Required");
            return false;
        }
        if (productDesc.getText().toString().contentEquals("")) {
            productDesc.setError("Field Required");
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng current = new LatLng(currentProduct.getLatitude(), currentProduct.getLongitude());
        googleMap.addMarker(new MarkerOptions()
                .position(current)
                .title("Current Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 10));

        googleMap.setOnMapClickListener(latLng -> {
            googleMap.clear();
            LatLng current1 = new LatLng(latLng.latitude, latLng.longitude);
            currentProduct.setLatitude(current1.latitude);
            currentProduct.setLongitude(current1.longitude);
            googleMap.addMarker(new MarkerOptions()
                    .position(current1)
                    .title("Current Location"));
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            View v = getCurrentFocus();
            if ( v instanceof EditText||v instanceof SearchView) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}