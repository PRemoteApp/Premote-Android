package com.example.finkacho.premote_android.ui.activitys;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.finkacho.premote_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordRecoveryActivity extends AppCompatActivity {

    @BindView(R.id.recover_mail)
    TextInputEditText mailEntry;

    FirebaseAuth mAuth;

    Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        shake = AnimationUtils.loadAnimation(this, R.anim.shake_anim);;

    }


    @OnClick(R.id.passRecoverBtn)
    public void onRecover(){

        String mail = mailEntry.getText().toString();

        if(mail.isEmpty()){
            mailEntry.setError(getResources().getString(R.string.enterMail));
            mailEntry.startAnimation(shake);
        }else if(!mail.contains("@") || mail.length() < 5){
            mailEntry.setError(getResources().getString(R.string.wrongMailFormat));
            mailEntry.startAnimation(shake);
        }else{
            mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(PasswordRecoveryActivity.this, R.string.password_reset_sent, Toast.LENGTH_SHORT).show();
                }else{
                    String error = task.getException() == null?getString(R.string.unknownError):task.getException().getMessage();
                    Toast.makeText(PasswordRecoveryActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
