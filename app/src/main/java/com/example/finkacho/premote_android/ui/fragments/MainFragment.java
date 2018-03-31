package com.example.finkacho.premote_android.ui.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finkacho.premote_android.Constants;
import com.example.finkacho.premote_android.helpers.PermissionManager;
import com.example.finkacho.premote_android.helpers.SpeechToText.BeforeRecognitionStart;
import com.example.finkacho.premote_android.helpers.SpeechToText.OnRecognitionFailure;
import com.example.finkacho.premote_android.helpers.SpeechToText.OnSuccessRecognition;
import com.example.finkacho.premote_android.R;
import com.example.finkacho.premote_android.helpers.SpeechToText.SpeechToText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements OnSuccessRecognition, OnRecognitionFailure, BeforeRecognitionStart{

    @BindView(R.id.main_fragment_hello) TextView helloPlaceHolder;
    @BindView(R.id.commandSenderLayout) RelativeLayout commandSenderLayout;
    @BindView(R.id.main_fragment_textInput) EditText commandInput;
    @BindView(R.id.main_fragment_mic) ImageView commandSenderImage;
    @BindView(R.id.commandSendingStatus) TextView status;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase database;

    SharedPreferences sharedPrefs;



    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean sending_type = sharedPrefs.getBoolean(Constants.keys.recognitionType, false);

        if(sending_type){
            commandSenderImage.setVisibility(View.GONE);
            commandSenderLayout.setVisibility(View.VISIBLE);
        }else{
            PermissionManager permissionManager = new PermissionManager(getActivity(), getActivity());
            boolean status = permissionManager.permissionStatus(Manifest.permission.RECORD_AUDIO);
            if(!status){
                permissionManager.requestPermission(new Random().nextInt(100), Manifest.permission.RECORD_AUDIO);
            }
        }


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        String hello = getResources().getString(R.string.hello)+" "+mUser.getDisplayName()+" !";
        helloPlaceHolder.setText(hello);


        return view;
    }


    @OnClick(R.id.commandSenderBtn)
    public void onClick(View view){
        String command = commandInput.getText().toString();
        sendDataToDatabase(command);
    }


    @OnClick(R.id.main_fragment_mic)
    public void onMicrophoneClicked(){
        new SpeechToText(getActivity()).onSuccess(this).onFail(this).before(this).start();
    }


    private void sendDataToDatabase(String data){
        database.getReference("commands").child(format(mUser.getEmail())).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    String error = task.getException()==null?getResources().getString(R.string.unknownError):task.getException().getMessage();
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String format(String email) {
        if(email==null) return "";
        return email.replace(".", ",");
    }

    @Override
    public void onFailure(String err) {
        status.setText(err);
    }

    @Override
    public void onSuccess(final String recognizedText) {
        boolean warning = sharedPrefs.getBoolean(Constants.keys.warning, false);
        if(!warning){
            sendDataToDatabase(recognizedText);
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = getResources().getString(R.string.recognizedAs) + " " + recognizedText + getResources().getString(R.string.wantToContinue);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendDataToDatabase(recognizedText);
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();

    }

    @Override
    public void Before() {
        status.setText(R.string.startCommanding);
    }
}
