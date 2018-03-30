package com.example.finkacho.premote_android.ui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finkacho.premote_android.R;

import butterknife.ButterKnife;


public class CommandsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commands, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
