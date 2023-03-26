package com.example.mobileapp.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobileapp.Buyer.HomeActivity;
import com.example.mobileapp.Buyer.MainActivity;
import com.example.mobileapp.Buyer.loginActivity;
import com.example.mobileapp.Prevalent.Prevalent;
import com.example.mobileapp.R;

public class AdminHomeActivity extends AppCompatActivity {

    private Button LogoutBtn,CheckOrdersBtn,maintainProductsBtn,checkApproveProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        LogoutBtn = (Button)findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button)findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button)findViewById(R.id.maintain_btn);
        checkApproveProductsBtn = (Button)findViewById(R.id.check_approve_products_btn);



        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);
                finish();
            }
        });

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminNewOrdersActivity.class);

                startActivity(intent);

            }
        });

        checkApproveProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminCheckNewProductsActivity.class);

                startActivity(intent);

            }
        });


    }

    public void onBackPressed(){

            new AlertDialog.Builder(this).setTitle("Are you sure")
                    .setMessage("You want to logout?")
                    .setNegativeButton(android.R.string.no,null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(AdminHomeActivity.this, loginActivity.class);
                            startActivity(intent);
                        }
                    }).create().show();



        }


    }

