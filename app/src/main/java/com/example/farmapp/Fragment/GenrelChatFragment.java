package com.example.farmapp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farmapp.Adepter.MessageAdapter;
import com.example.farmapp.Pojo.MessagePojo;
import com.example.farmapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class GenrelChatFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;
    EditText editTextMessage;
    ArrayList<MessagePojo> arrayList = new ArrayList<MessagePojo>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_genrel_chat, container, false);

        recyclerView = view.findViewById(R.id.msgRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        ImageView imageViewSend = view.findViewById(R.id.imageViewMsgSend);
        editTextMessage=view.findViewById(R.id.editMsg);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("message");

        sharedPreferences=getActivity().getSharedPreferences("myprf",MODE_PRIVATE);
        final String url=sharedPreferences.getString("imgUrl",null);

        Log.d("12345", "onCreateView: get data firebase call");

        getDataFromFirebase();

        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editTextMessage.getText().toString();
                Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                int hr = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                String time = hr + ":" + min;
                String date = day + "/" + (month + 1) + "/" + year;

                MessagePojo messagePojo = new MessagePojo();
                messagePojo.setDate(date);
                messagePojo.setTime(time);
                messagePojo.setImg_url(url);
                messagePojo.setMessage(message);
                reference.push().setValue(messagePojo);
                Log.d("12345", "onCreateView: get data pushed");
                editTextMessage.setText(null);

            }
        });

        return view;
    }

    public void getDataFromFirebase() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                Log.d("1234", "onDataChange: ref ");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("1234", "onDataChange: for " + dataSnapshot1);
                    MessagePojo messagePojo = dataSnapshot1.getValue(MessagePojo.class);
                    arrayList.add(messagePojo);
                }
                Log.d("1234", "onDataChange: " + arrayList.size());
                adapter = new MessageAdapter(getActivity(), arrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}