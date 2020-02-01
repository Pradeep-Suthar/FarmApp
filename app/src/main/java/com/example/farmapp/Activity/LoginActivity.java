package com.example.farmapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmapp.Pojo.UserPojo;
import com.example.farmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText loginEditTextEmail,loginEditTextPwd;
    Button btnLogin;
    TextView textViewClicklogin;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewClicklogin=findViewById(R.id.textClickLogin);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userInfo");

        loginEditTextEmail = findViewById(R.id.loginemail);
        loginEditTextPwd = findViewById(R.id.loginPwd);
        btnLogin = findViewById(R.id.btnsignin);
        textViewClicklogin = findViewById(R.id.textClickLogin);

        SharedPreferences sharedPreferences1=getSharedPreferences("myprf",MODE_PRIVATE);
        Boolean status=sharedPreferences1.getBoolean("loginStatus",false);
        if(status==true){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });

        textViewClicklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }
    public void setLogin() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String email=loginEditTextEmail.getText().toString();
        final String password=loginEditTextPwd.getText().toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren() ){
                    UserPojo userPojo=dataSnapshot1.getValue(UserPojo.class);
                    String ids=userPojo.getId();
                    String names=userPojo.getName();
                    String emails=userPojo.getEmail();
                    String mobiles=userPojo.getMoble_no();
                    String pinCode=userPojo.getPinCode();
                    String pwds=userPojo.getPwd();
                    String urls=userPojo.getImg();
                    if(email.equals(emails)&&password.equals(pwds)){
                        SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("id",ids);
                        editor.putString("name",names);
                        editor.putString("email",emails);
                        editor.putString("mobile",mobiles);
                        editor.putString("pwd",pwds);
                        editor.putString("pin",pinCode);
                        editor.putString("imgUrl",urls);
                        editor.putBoolean("loginStatus",true);
                        editor.commit();
                        flag=1;
                        break;
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Login Error" ,Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                }
                if(flag==1) {
                    progressDialog.cancel();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



