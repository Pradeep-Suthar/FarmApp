package com.example.farmapp.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farmapp.Fragment.ChatFragment;
import com.example.farmapp.Fragment.CropFragment;
import com.example.farmapp.Fragment.HomeFragment;
import com.example.farmapp.Fragment.ServicesFragment;
import com.example.farmapp.Fragment.WeatherFragment;
import com.example.farmapp.Pojo.UserPojo;
import com.example.farmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textViewProfile;
    ImageView imageViewProfile;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    static final Pattern s3 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userInfo");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        View nview=navigationView.getHeaderView(0);
        textViewProfile=nview.findViewById(R.id.textViewProfile);
        imageViewProfile=nview.findViewById(R.id.imageViewProfile);

        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
        String imageUrl=sharedPreferences.getString("imgUrl",null);
        String name=sharedPreferences.getString("name",null);

        Log.d("1234", "onCreate: "+imageUrl+"and"+name);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        Glide.with(HomeActivity.this).load(imageUrl).into(imageViewProfile);
        textViewProfile.setText(name);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();//Default fragment in on create
    }
    Fragment selectedFragment=null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment=new HomeFragment();
                    getSupportActionBar().setTitle("Home");
                    break;
                case R.id.navigation_Crop:
                    selectedFragment=new CropFragment();
                    getSupportActionBar().setTitle("Crop");
                    break;
                case R.id.navigation_Chat:
                    selectedFragment=new ChatFragment();
                    getSupportActionBar().setTitle("Chat");
                    break;
                case R.id.navigation_Services:
                    selectedFragment=new ServicesFragment();
                    getSupportActionBar().setTitle("Services");
                    break;
                case R.id.navigation_Weather:
                    selectedFragment=new WeatherFragment();
                    getSupportActionBar().setTitle("Weather");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.viewprofile, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();

            ImageView VpImageView=view.findViewById(R.id.Vprofileimageview1);
            TextView VpTextname = view.findViewById(R.id.VprofileTextViewName);
            TextView VpTextemail = view.findViewById(R.id.VprofileTextViewEmail);
            TextView VpTextMobile = view.findViewById(R.id.VprofileTextViewMobile);

            SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
            String ImgUrl=sharedPreferences.getString("imgUrl",null);
            String name=sharedPreferences.getString("name",null);
            String Email=sharedPreferences.getString("email",null);
            String Mobile=sharedPreferences.getString("mobile",null);

            Glide.with(HomeActivity.this).load(ImgUrl).into(VpImageView);
            VpTextname.setText(name);
            VpTextemail.setText(Email);
            VpTextMobile.setText(Mobile);
            dialog.show();

        } else if (id == R.id.nav_EditProfile) {
            final AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(HomeActivity.this);
            LayoutInflater layoutInflater1 = getLayoutInflater();
            View view1 = layoutInflater1.inflate(R.layout.upadteprofile, null, false);
            SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
            String ImgUrl=sharedPreferences.getString("imgUrl",null);
            String name1=sharedPreferences.getString("name",null);
            String pin1=sharedPreferences.getString("pin",null);
            String number1=sharedPreferences.getString("mobile",null);

            alertDialogBuilder1.setView(view1);
            final AlertDialog dialog1 = alertDialogBuilder1.create();
            ImageView uprofile=view1.findViewById(R.id.profile_image);
            final EditText editTextname = view1.findViewById(R.id.UeditTextname);
            final EditText editTextmobile = view1.findViewById(R.id.UeditTextmobile);
            final EditText editTextpin = view1.findViewById(R.id.UeditTextPin);
            Button buttonUpadate = view1.findViewById(R.id.UPButton);

            editTextmobile.setText(number1);
            editTextname.setText(name1);
            editTextpin.setText(pin1);

            Glide.with(HomeActivity.this).load(ImgUrl).into(uprofile);

            buttonUpadate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
                    final String id1=sharedPreferences.getString("id",null);
                    final String Name=editTextname.getText().toString();
                    final String Number=editTextmobile.getText().toString();
                    final String Pin=editTextpin.getText().toString();

                    HashMap<String,Object> map=new HashMap<String,Object>();
                    map.put("name",Name);
                    map.put("moble_no",Number);
                    map.put("pinCode",Pin);
                    databaseReference.child(id1).updateChildren(map);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);
                                String id=userPojo.getId();
                                if(id.equals(id1))
                                {
                                    SharedPreferences sharedPreferences1 = getSharedPreferences("myprf", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                    editor1.putString("name", userPojo.getName());
                                    editor1.putString("id", userPojo.getId());
                                    editor1.putString("pin",userPojo.getPinCode());
                                    editor1.putString("pwd", userPojo.getPwd());
                                    editor1.putString("mobile", userPojo.getMoble_no());
                                    editor1.commit();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(HomeActivity.this, "Updated Successfully" , Toast.LENGTH_SHORT).show();
                    dialog1.dismiss();
                }
            });
            dialog1.show();
        } else if (id == R.id.nav_ChangePwd) {
            final AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(HomeActivity.this);
            LayoutInflater layoutInflater2 = getLayoutInflater();
            View view2 = layoutInflater2.inflate(R.layout.updatepassword, null, false);

            alertDialogBuilder2.setView(view2);
            final AlertDialog dialog2 = alertDialogBuilder2.create();

            final EditText editTextOld = view2.findViewById(R.id.UeditTextOld);
            final EditText editTextNew = view2.findViewById(R.id.UeditTextNew);
            final EditText editTextCon = view2.findViewById(R.id.UeditTextCon);
            Button buttonUpwd = view2.findViewById(R.id.UpwdButton);
            buttonUpwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
                    final String id1=sharedPreferences.getString("id",null);
                    String Pwd=sharedPreferences.getString("pwd",null);
                    String OldPwd=editTextOld.getText().toString();
                    String NewPwd=editTextNew.getText().toString();
                    String ConPwd=editTextCon.getText().toString();

                    if (!NewPwd.equals(ConPwd)) {
                        editTextCon.setError("Password does not match");
                        editTextCon.requestFocus();
                    }else if(!Pwd.equals(OldPwd)){
                        editTextOld.setError("Password does not match");
                        editTextOld.requestFocus();
                    }else if (!s3.matcher(NewPwd).matches()) {
                        editTextNew.setError("enter valid password");
                    }else if (!s3.matcher(ConPwd).matches()) {
                        editTextCon.setError("enter valid password");
                    }else{
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("pwd", NewPwd);
                        map.put("conPwd", ConPwd);
                        databaseReference.child(id1).updateChildren(map);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    UserPojo userPojo = dataSnapshot1.getValue(UserPojo.class);
                                    String id=userPojo.getId();
                                    if(id.equals(id1)) {
                                        SharedPreferences sharedPreferences1 = getSharedPreferences("data", MODE_PRIVATE);
                                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                        editor1.putString("pwd", userPojo.getPwd());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Toast.makeText(HomeActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }
                }
            });
            dialog2.show();
        } else if (id == R.id.nav_About) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.about_dialog, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();

            dialog.show();

        } else if (id == R.id.nav_Help) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.help_dialog, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();

            dialog.show();


        } else if (id == R.id.nav_SignOut) {
            SharedPreferences sharedPreferences1=getSharedPreferences("myprf",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences1.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(this,"Successfully Logout",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
