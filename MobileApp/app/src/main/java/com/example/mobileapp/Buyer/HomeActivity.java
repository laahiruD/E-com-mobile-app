package com.example.mobileapp.Buyer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.mobileapp.Admin.AdminHomeActivity;
import com.example.mobileapp.Admin.AdminMaintainProductsActivity;
import com.example.mobileapp.Model.Products;
import com.example.mobileapp.Prevalent.Prevalent;
import com.example.mobileapp.R;
import com.example.mobileapp.Seller.SellerhomeActivity;
import com.example.mobileapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;



public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

        //CheckThis

    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    private String type ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //I think You should change here for home  segment
        setContentView(R.layout.activity_home);

       Paper.init(this);
        //Also This
        ProductRef = FirebaseDatabase.getInstance().getReference().child("Products");


       Intent intent = getIntent();
       Bundle bundle =intent.getExtras();
       if(bundle != null){
           type = getIntent().getExtras().get("Admin").toString();
       }


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);


      final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!type.equals("Admin")){

                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);

                }


            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        View headerView =navigationView.getHeaderView(0);
        TextView userNameTextView =headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);



        if(!type.equals("Admin")){
            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        }



        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);






        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Cart, R.id.nav_search,
                 R.id.nav_settings, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
//R.id.nav_categories
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {


                int menuId = destination.getId();
        if(menuId==R.id.nav_Cart || menuId==R.id.nav_search || menuId==R.id.nav_categories || menuId==R.id.nav_settings || menuId==R.id.nav_logout ){
            recyclerView.setVisibility(View.INVISIBLE);
        }
        if(menuId == R.id.nav_home){
            recyclerView.setVisibility(View.VISIBLE);
        }
       /* if(menuId == R.id.nav_logout){
            if(!type.equals("Admin")){

                Paper.book().destroy();
                Intent intent = new Intent(HomeActivity.this ,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        }*/

                switch (menuId){

                    case R.id.nav_Cart:
                        if(!type.equals("Admin")){
                            Intent intent = new Intent(HomeActivity.this,CartActivity.class);
                            startActivity(intent);
                            fab.hide();
                            break;
                        }
                        if(type.equals("Admin")) {
                            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                        }


                    case R.id.nav_search:
                        if(!type.equals("Admin")){
                            Intent intent1 = new Intent(HomeActivity.this, SearchproductsActivity.class);
                            startActivity(intent1);
                            fab.hide();
                            break;
                        }
                        if(type.equals("Admin")) {
                            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                        }


                    case R.id.nav_categories:
                        if(!type.equals("Admin")){
                            fab.hide();
                            break;
                        }
                        if(type.equals("Admin")) {
                            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                        }


                    case R.id.nav_settings:

                        if(!type.equals("Admin")){
                            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                            startActivity(intent);
                            fab.hide();
                            break;
                        }
                        if(type.equals("Admin")) {
                            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                        }


                    case R.id.nav_logout:

                        if(!type.equals("Admin")){
                            Paper.book().destroy();
                            Intent intent = new Intent(HomeActivity.this , MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            fab.hide();
                            break;
                        }
                        if(type.equals("Admin")) {
                            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                        }


                    default:
                        fab.show();
                }

            }
        });
    }


    @Override
   protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductRef.orderByChild("productState").equalTo("Approved"),Products.class)
                .build();


        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Products model) {
                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price = $" + model.getPrice() );
                        Picasso.get().load(model.getImage()).into(holder.imageView);




                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                if(type.equals("Admin")){

                                    Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);

                                }else{

                                    Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);
                                }


                            }
                        });



                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

       recyclerView.setAdapter(adapter);
       adapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onBackPressed(){


        if(!type.equals("Admin")) {
            new AlertDialog.Builder(this).setTitle("Are you sure")
                    .setMessage("You want to logout?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Paper.book().destroy();
                            Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).create().show();

        }if(type.equals("Admin")){
            Intent intent = new Intent(HomeActivity.this, AdminHomeActivity.class);
            startActivity(intent);

        }

    }

}
