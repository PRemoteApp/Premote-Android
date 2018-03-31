package com.example.finkacho.premote_android.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.widget.Toast;

import com.example.finkacho.premote_android.Constants;
import com.example.finkacho.premote_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends PreferenceFragment {

    SharedPreferences sharedPref;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        EditTextPreference mail = (EditTextPreference) findPreference(Constants.keys.mail);
        mail.setText(mUser.getEmail());
        mail.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String newMail = (String) newValue;
                mUser.updateEmail(newMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), getResources().getString(R.string.mail_change_success), Toast.LENGTH_SHORT).show();
                        }else{
                            String error = task.getException()==null?getResources().getString(R.string.unknownError):task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                return true;
            }
        });

        EditTextPreference password = (EditTextPreference) findPreference(Constants.keys.password);
        password.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String newPass = (String) newValue;
                mUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), getResources().getString(R.string.pass_change_success), Toast.LENGTH_SHORT).show();
                        }else{
                            String error = task.getException()==null?getResources().getString(R.string.unknownError):task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }
        });

    }
}
