package com.example.lab_1_2_rochbajracharya_c0837288_android;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

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

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    EditText productNameLabel, productDesc, productPrice;

    MaterialButton btnEditProduct;

    Product currentProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // showing same layout for add and edit
        setContentView(R.layout.activity_product_detail);

        getSupportActionBar().setTitle("Add New Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        currentProduct = new Product();
        initialize();
    }

    void initialize() {
        productNameLabel = findViewById(R.id.productName_label);
        productDesc = findViewById(R.id.productDescription_label);
        productPrice = findViewById(R.id.productPrice_label);

        btnEditProduct = findViewById(R.id.button_edit);
        btnEditProduct.setText("Add Product");

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
                currentProduct = new Product(productNameLabel.getText().toString(), productDesc.getText().toString(), Double.parseDouble(productPrice.getText().toString()), 12, 12);

                ProductRoomDb.getInstance(this).productDao().insertProduct(currentProduct);
                Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
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