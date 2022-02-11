package com.example.lab_1_2_rochbajracharya_c0837288_android.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductRoomDb extends RoomDatabase {

    private static final String DB_NAME = "product_db";

    public abstract ProductDao productDao();

    private static volatile ProductRoomDb INSTANCE;

    public static ProductRoomDb getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductRoomDb.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }
}
