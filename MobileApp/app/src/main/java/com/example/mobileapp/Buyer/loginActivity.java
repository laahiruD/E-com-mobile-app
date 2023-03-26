package com.example.mobileapp.Buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.Admin.AdminHomeActivity;
import com.example.mobileapp.Seller.SellerProductCategoryActivity;
import com.example.mobileapp.Model.Users;
import com.example.mobileapp.Prevalent.Prevalent;
import com.example.mobileapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class  loginActivity extends AppCompatActivity {


   private  EditText InputPhonenumber,InputPassword;
   private  Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink,NotAdminLink,ForgetPasswordLink;

    private  String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton = (Button) findViewById(R.id.login_Button);
        InputPassword =(EditText) findViewById(R.id.login_password_input);
        InputPhonenumber = (EditText)findViewById(R.id.login_phone_number_input);
        AdminLink =(TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink =(TextView) findViewById(R.id.not_admin_panel_link);
        ForgetPasswordLink = findViewById(R.id.forget_password_link);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_check_box);
        Paper.init(this);


        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        ForgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check","login");
                startActivity(intent);
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";

            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";

            }
        });
    }

    private void loginUser() {
        String phone = InputPhonenumber.getText().toString();
        String password = InputPassword.getText().toString();

         if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
         else{
             loadingBar.setTitle("Login to your Account");
             loadingBar.setMessage("Please wait while we are checking the credentials.");
             loadingBar.setCanceledOnTouchOutside(false);
             loadingBar.show();

             AllowAccessToAccount(phone,password);
         }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if((chkBoxRememberMe.isChecked())){
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {

                    Users userData =dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            if(parentDbName.equals("Admins")) {
                                Toast.makeText(loginActivity.this, "Logged in Successfully as Admin!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(loginActivity.this, AdminHomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }else if(parentDbName.equals("Users")){
                                Toast.makeText(loginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Prevalent.currentOnlineUser = userData;
                                startActivity(intent);
                            }
                        }else{
                            loadingBar.dismiss();
                            Toast.makeText(loginActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(loginActivity.this, "Invalid Phone Number!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    boolean doublebacktoexit=false;

    public void onBackPressed(){

       /* if(doublebacktoexit){
            super.onBackPressed();
            return;
        }

        this.doublebacktoexit = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doublebacktoexit=false;
            }
        },2000);*/
        Intent intent = new Intent(loginActivity.this,MainActivity.class);
        intent.putExtra("Exit",true);
        startActivity(intent);



    }



}


