package com.example.lab_1_2_rochbajracharya_c0837288_android.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM product_table ORDER BY productName")
    List<Product> getAllProduct();

    @Query("SELECT * FROM product_table WHERE id IN (:id)")
    Product getProductById(int id);

    @Query("SELECT * FROM product_table WHERE (productName LIKE '%' || :name || '%') OR (productDescription LIKE '%' || :name || '%')")
    List<Product> getProductsByName(String name);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

}
