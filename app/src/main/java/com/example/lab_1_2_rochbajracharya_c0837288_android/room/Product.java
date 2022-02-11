package com.example.lab_1_2_rochbajracharya_c0837288_android.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "productName")
    private String productName;

    @NonNull
    @ColumnInfo(name = "productDescription")
    private String productDescription;

    @NonNull
    @ColumnInfo(name = "productPrice")
    private double productPrice;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    public Product(@NonNull String productName, @NonNull String productDescription, @NonNull double productPrice, double latitude, double longitude) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@NonNull String productDescription) {
        this.productDescription = productDescription;
    }

    @NonNull
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(@NonNull double productPrice) {
        this.productPrice = productPrice;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
