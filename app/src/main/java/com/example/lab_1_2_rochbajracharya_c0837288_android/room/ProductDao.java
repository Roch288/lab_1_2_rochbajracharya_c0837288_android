package com.example.lab_1_2_rochbajracharya_c0837288_android.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM product_table ORDER BY productName")
    List<Product> getAllProduct();

    @Query("SELECT * FROM product_table WHERE id IN (:id)")
    Product getProductById(int id);

}
