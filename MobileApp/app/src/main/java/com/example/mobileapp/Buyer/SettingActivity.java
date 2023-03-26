package com.example.mobileapp.Buyer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.Prevalent.Prevalent;
import com.example.mobileapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {


    private CircleImageView profileImageView;
    private EditText fullNameEdittext, userphoneEditText, addressEdittext;
    private TextView profileChangeTextBtn, closeTextBtn, saveTextBtn;
    private Button securityQuestionBtn;


    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePictureRef;
    private String checker="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");



        profileImageView = (CircleImageView) findViewById(R.id.settings_profile_image1);
        fullNameEdittext = (EditText) findViewById(R.id.setting_full_name1);
        userphoneEditText = (EditText)findViewById(R.id.setting_phone_number1);
        addressEdittext = (EditText)findViewById(R.id.setting_address1);
        profileChangeTextBtn = (TextView)findViewById(R.id.profile_image_change_btn1);
        closeTextBtn = (TextView)findViewById(R.id.close_settings_btn1);
        saveTextBtn = (TextView)findViewById(R.id.update_account_settings_btn1);
        securityQuestionBtn = findViewById(R.id.security_questions_btn);

        userInfoDisplay(profileImageView, fullNameEdittext, userphoneEditText, addressEdittext);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



        securityQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ResetPasswordActivity.class);
                intent.putExtra("check","settings");
                startActivity(intent);
            }
        });

        saveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked")){
                    userInfosaved();
                }else{
                    updateOnlyUserInfo();
                }
            }
        });

        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingActivity.this);

            }
        });
    }

    private void updateOnlyUserInfo() {

        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name",fullNameEdittext.getText().toString());
        userMap.put("address",addressEdittext.getText().toString());
        userMap.put("phoneOrder",userphoneEditText.getText().toString());
        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);



        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
        Toast.makeText(SettingActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data !=null){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            imageUri = result.getUri();
            profileImageView.setImageURI(imageUri);
        }else{
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SettingActivity.this,SettingActivity.class));
            finish();
        }
    }

    private void userInfosaved() {

            if(TextUtils.isEmpty(fullNameEdittext.getText().toString())){
                Toast.makeText(this, "Name is mandatory!", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(addressEdittext.getText().toString())){
             Toast.makeText(this, "Address is mandatory!", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(userphoneEditText.getText().toString())){
             Toast.makeText(this, "Phone number is mandatory!", Toast.LENGTH_SHORT).show();
            }else if(checker.equals("clicked")){
               uploadImage();
            }

    }

    private void uploadImage() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("update Profile");
        progressDialog.setMessage("Please wait,while we are updating your account");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if(imageUri != null){
            final StorageReference fileRef = storageProfilePictureRef
                    .child(Prevalent.currentOnlineUser.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            })
            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task <Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl = task.getResult();
                        myUrl = downloadUrl.toString();

                        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("Users");

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("name",fullNameEdittext.getText().toString());
                        userMap.put("address",addressEdittext.getText().toString());
                        userMap.put("phoneOrder",userphoneEditText.getText().toString());
                        userMap.put("image",myUrl);
                        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);

                        progressDialog.dismiss();
                        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
                        Toast.makeText(SettingActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                        finish();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(SettingActivity.this, "Error!", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }else{
            Toast.makeText(this, "Image is not selected!", Toast.LENGTH_SHORT).show();
        }
    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEdittext, final EditText userphoneEditText, final EditText addressEdittext) {

        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.child("image").exists()){
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String phone = dataSnapshot.child("phoneOrder").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);
                        fullNameEdittext.setText(name);
                        userphoneEditText.setText(phone);
                        addressEdittext.setText(address);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void onBackPressed(){

        Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}



