package com.example.farmapp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farmapp.R;

public class BankRequestActivity extends AppCompatActivity {
    Spinner spinnerBankReq;
    Button btnSendReq;
    String Bank="";

    String arrBank[]={"Select Bank","State Bank of India","HDFC Bank","ICICI Bank","Bank of India Bank","Bank of Baroda","ICICI Bank"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_request);
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Bank Request");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        spinnerBankReq=findViewById(R.id.spinnerBankReq);
        btnSendReq=findViewById(R.id.btnSendReq);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, R.layout.spinner_item,arrBank);
        spinnerBankReq.setAdapter(arrayAdapter);

        spinnerBankReq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(BankRequestActivity.this, "Please Select Bank", Toast.LENGTH_SHORT).show();
                }
                else{
                    Bank=parent.getItemAtPosition(position).toString();
                    Toast.makeText(BankRequestActivity.this, "Selected Bank is "+Bank, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(BankRequestActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you want to send Request ?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BankRequestActivity.this);
                        LayoutInflater layoutInflater = getLayoutInflater();
                        View view = layoutInflater.inflate(R.layout.req_send_conf, null, false);
                        alertDialogBuilder.setView(view);
                        AlertDialog dialog1 = alertDialogBuilder.create();
                        TextView bankName=view.findViewById(R.id.textViewBankReqestName);
                        bankName.setText(Bank);
                        dialog1.show();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(BankRequestActivity.this, "You Canceled Request", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

            }
        });
    }

}

