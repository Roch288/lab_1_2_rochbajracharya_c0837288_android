package com.example.lab_1_2_rochbajracharya_c0837288_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.lab_1_2_rochbajracharya_c0837288_android.room.Product;
import com.example.lab_1_2_rochbajracharya_c0837288_android.room.ProductRoomDb;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductRoomDb productRoomDb;

    List<Product> productList;
    ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productListView = findViewById(R.id.products_list_view);
        productList = new ArrayList<>();

        productRoomDb = ProductRoomDb.getInstance(this);
        loadProducts();
        addProducts();

        getSupportActionBar().setTitle("Products");
    }

    // load all products from database
    private void loadProducts() {
        productList = productRoomDb.productDao().getAllProduct();

        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.product_item, productList);
        productListView.setAdapter(productAdapter);
    }


    // function to add 10 products initially
    private void  addProducts(){
        // check if there's already products
        if(productList.size() < 1){
            productRoomDb.productDao().insertProduct(new Product(
                    "Mascara",
                    "Instant Brow Styler: This non sticky and transparent brow glue gives you extra strong, 16 hour extreme hold instantly, allowing you to style brow hairs and hold them in place for limitless looks",
                    11,
                    45.12458,
                    141.98851));
            productRoomDb.productDao().insertProduct(new Product(
                    "Eyebrow Setting Gel",
                    "Give you big and beautiful eyebrows naturally. Great for brow shaping, used to tame unruly brows and boost brow color. Make your face more stereo and is the best choice for perfect eyebrows",
                    11.99,
                    -9.92497,
                    -147.94071));
            productRoomDb.productDao().insertProduct(new Product(
                    "Lipstick",
                    "Our lipstick formula contains all the same things as the other guys: rich color, vitamins A and E, aloe vera, and really feels like putting silk on your lips",
                    1.87,
                    -61.09758,
                    38.51174));
            productRoomDb.productDao().insertProduct(new Product(
                    "Eyelashes",
                    "It is self-adhesive, no glue or eyeliner is needed. Greatly simplify your makeup process, saving time and energy.",
                    18.99,
                    -24.56729,
                    -142.81066));
            productRoomDb.productDao().insertProduct(new Product(
                    "Eyelash Curler",
                    "The product will help you get longer and fuller lashes in seconds without tugging, pulling, or damaging your lashes. No matter the shape of your eyes, our stainless steel eyelash curlers will deliver the thick, curled lashes you need",
                    8.86,
                    -60.44218,
                    -175.53881));
            productRoomDb.productDao().insertProduct(new Product(
                    "Foundation Brush",
                    "Soft, Compact, Silky. Does not soak up excessive amounts. Applies different products on areas such as the forehead, cheeks, nose, chin, without trapping or absorption. ",
                    11.95,
                    19.77296,
                    41.72782));
            productRoomDb.productDao().insertProduct(new Product(
                    "Moisturizing Liquid Hand Soap",
                    "Moisturizing Hand Soap That Leaves Hands Feeling Smooth and Soft",
                    5.97,
                    25.55362,
                    -117.76451));
            productRoomDb.productDao().insertProduct(new Product(
                    "Lip Plumper",
                    "Our Lip Plumper cares for your skin round the clock with its two-components ginger and mint perfectly combined with Vitamin E and Collagen",
                    24.89,
                    43.69958,
                    -71.33836));
            productRoomDb.productDao().insertProduct(new Product(
                    "Putty Primer",
                    "Poreless Putty Primer is the ultimate skin perfecting primer and is infused with Squalane to help grip makeup for all-day wear and help protect skin from moisture loss.",
                    10.97,
                    -51.37318,
                    110.59958));
            productRoomDb.productDao().insertProduct(new Product(
                    "Mineral Infused Face Primer",
                    "Ideal for creating a smooth base for foundation.",
                    16.40,
                    36.03237,
                    113.77359));
            loadProducts();
        }

    }
}