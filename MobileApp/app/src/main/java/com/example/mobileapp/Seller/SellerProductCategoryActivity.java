package com.example.mobileapp.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobileapp.R;

public class SellerProductCategoryActivity extends AppCompatActivity {

    private ImageView TShirts,sportsTShirts,femaleDresses,sweathers;
    private ImageView glasses,hatsCaps,walletBagsPurses,shoes;
    private ImageView headPhonesHandFree,Laptops,watches,mobilePhones;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_category);



        TShirts =(ImageView)findViewById(R.id.t_shirts);
        sportsTShirts =(ImageView)findViewById(R.id.sports_t_shirts);
        femaleDresses =(ImageView)findViewById(R.id.female_dresses);
        sweathers =(ImageView)findViewById(R.id.sweathers);

        glasses =(ImageView)findViewById(R.id.glasses);
        hatsCaps =(ImageView)findViewById(R.id.hats_caps);
        walletBagsPurses =(ImageView)findViewById(R.id.purses_bags_wallets);
        shoes =(ImageView)findViewById(R.id.shoes);

        headPhonesHandFree =(ImageView)findViewById(R.id.headphones_handfree);
        Laptops =(ImageView)findViewById(R.id.laptop_pc);
        watches =(ImageView)findViewById(R.id.watches);
        mobilePhones =(ImageView)findViewById(R.id.mobilephones);



        TShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);

            }
        });

        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "sport TShirts");
                startActivity(intent);
            }
        });


        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "female Dresses");
                startActivity(intent);
            }
        });


        sweathers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Sweathers");
                startActivity(intent);
            }
        });



        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Glasses");
                startActivity(intent);
            }
        });

        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Hats/Caps");
                startActivity(intent);
            }
        });

        walletBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Wallets/Bags/Purses");
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Shoes");
                startActivity(intent);
            }
        });

        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "HeadPhone/Handfrees");
                startActivity(intent);
            }
        });

        Laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });


        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Watches");
                startActivity(intent);
            }
        });

        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddnewProductActivity.class);
                intent.putExtra("category", "Mobile Phones");
                startActivity(intent);
            }
        });
    }


}
