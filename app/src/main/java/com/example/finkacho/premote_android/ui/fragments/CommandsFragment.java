package com.example.finkacho.premote_android.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finkacho.premote_android.R;
import com.example.finkacho.premote_android.data.Command;
import com.example.finkacho.premote_android.helpers.PremoteService;
import com.example.finkacho.premote_android.ui.adapters.CommandsListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommandsFragment extends Fragment implements TextWatcher {

    @BindView(R.id.mCommandsList)
    RecyclerView mRecyclerView;
    @BindView(R.id.commandSearchBar)
    EditText searchBar;
    CommandsListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commands, container, false);
        ButterKnife.bind(this, view);

        searchBar.addTextChangedListener(this);

        // I KNOW this is not a good way but it will work to show off as a prototype
        adapter = new CommandsListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);


        List<Command> commands = new ArrayList<>();
        commands.add(new Command("open { social network }","this opens up social network on your pc"));
        commands.add(new Command("play music", "This command will play random music from your music folder"));
        commands.add(new Command("google { query word }", "this command will show you results of your googling"));
        commands.add(new Command("Hello", "this will appreaciate you and give you hello back"));

        adapter.addItems(commands);


//        PremoteService premoteService = PremoteService.retrofit.create(PremoteService.class);
//        Call<List<Command>> call = premoteService.getCommands();
//        call.enqueue(new Callback<List<Command>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Command>> call, @NonNull Response<List<Command>> response) {
//                List<Command> listOfCommands = response.body();
//                if(listOfCommands != null){
//                    adapter.addItems(listOfCommands);
//                }
//            }
//
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onFailure(@NonNull Call<List<Command>> call, @NonNull Throwable t) {
//                Log.d("Can't handle errors for now, i have no time: ", t.getMessage());
//            }
//        });

        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
