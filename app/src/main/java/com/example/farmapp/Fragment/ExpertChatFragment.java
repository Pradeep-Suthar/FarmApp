package com.example.farmapp.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farmapp.Activity.HomeActivity;
import com.example.farmapp.R;

import static android.content.Context.MODE_PRIVATE;


public class ExpertChatFragment extends Fragment {

    ImageView imageViewCall,imageViewMail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_expert_chat, container, false);
        imageViewCall=view.findViewById(R.id.imageViewCall);
        imageViewMail=view.findViewById(R.id.imageViewMail);

        imageViewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("12345", "onClick: enter in send mail");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.mail_content_dialog, null, false);
                alertDialogBuilder.setView(view);
                final AlertDialog dialog=alertDialogBuilder.create();

                final EditText editTextSubject = view.findViewById(R.id.editTextMailSubect);
                final EditText editTextBody = view.findViewById(R.id.editTextMailBody);
                Button SendMailBtn=view.findViewById(R.id.SendMailBtn);

                SendMailBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sub=editTextSubject.getText().toString();
                        String body=editTextBody.getText().toString();

                        Log.d("12345", "onClick: enter in send mail action");
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"pradeepsuthar800@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT,sub);
                        intent.putExtra(Intent.EXTRA_TEXT,body);

                        try{
                            startActivity(Intent.createChooser(intent,"Send Email..."));
                        }catch (android.content.ActivityNotFoundException ex){
                            Toast.makeText(getActivity(), "There are no email client installed", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
               dialog.show();
            }
        });

        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("12345", "onClick: enter for call");
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8005562033"));
                startActivity(intent);
            }
        });

        return view;

    }

}