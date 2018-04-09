package com.example.finkacho.premote_android.ui.activitys;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finkacho.premote_android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.reg_email) TextInputEditText mailInput;
    @BindView(R.id.reg_name) TextInputEditText nameInput;
    @BindView(R.id.reg_pass) TextInputEditText passInput;
    @BindView(R.id.reg_pass_repeat) TextInputEditText passRepeatInput;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

    }


    public void onSignUpClicked(View view){
        String mail = mailInput.getText().toString();
        String name = nameInput.getText().toString();
        String pass = passInput.getText().toString();
        String pass_repeat = passRepeatInput.getText().toString();

        boolean error = false;

        if(mail.isEmpty()){
            mailInput.setError(getString(R.string.enterMail));
            error = true;
        }else if(!mail.contains("@")){
            mailInput.setError(getString(R.string.wrongMailFormat));
            error = true;
        }

        if(name.isEmpty()){
            nameInput.setError(getString(R.string.enterName));
            error = true;
        }else if(name.length() < 4){
            nameInput.setError(getString(R.string.wrongNameFormat));
            error = true;
        }

        if(!pass.equals(pass_repeat)){
            passInput.setError(getString(R.string.passNotMatch));
            error = true;
        }else if(pass.length()<6){
            passInput.setError("Password is too short");
            error = true;
        }

        if(error) return;

        signUp(mail, pass, name);

    }


    private void signUp(String mail, String password, String displayName){
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
                        if(user!=null){
                            user.updateProfile(request);
                        }
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();
                    } else {
                        String error = task.getException()==null?getString(R.string.unknownError):task.getException().getLocalizedMessage();
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
