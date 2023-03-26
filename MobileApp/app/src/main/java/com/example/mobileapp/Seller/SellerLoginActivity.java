package com.example.mobileapp.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp.Buyer.MainActivity;
import com.example.mobileapp.Buyer.loginActivity;
import com.example.mobileapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SellerLoginActivity extends AppCompatActivity {

    private Button loginSellrtBtn;
    private EditText emailInput,passwordInput;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        mAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.seller_login_email);
        passwordInput = findViewById(R.id.seller_login_password);
        loginSellrtBtn= findViewById(R.id.seller_login_btn);
        loadingBar = new ProgressDialog(this);


        loginSellrtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSellr();
            }
        });

    }

    private void loginSellr() {
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();


        if( !email.equals("") && !password.equals("") ) {

            loadingBar.setTitle("Login to Your Account");
            loadingBar.setMessage("Please wait while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                loadingBar.dismiss();
                                Toast.makeText(SellerLoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SellerLoginActivity.this, SellerhomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(SellerLoginActivity.this, "Invalid email and password combination!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });


        }else{
            Toast.makeText(this, "Please fill all details.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed(){

        Intent intent = new Intent(SellerLoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
