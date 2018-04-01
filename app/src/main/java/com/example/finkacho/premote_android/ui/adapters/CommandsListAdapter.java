package com.example.finkacho.premote_android.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.finkacho.premote_android.R;
import com.example.finkacho.premote_android.data.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommandsListAdapter extends RecyclerView.Adapter<CommandsListAdapter.ViewHolder> implements Filterable {
    List<Command> data = new ArrayList<>();
    List<Command> original_data = new ArrayList<>();

    @Override
    public Filter getFilter() {
        data = original_data;
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString();
                if(query.isEmpty()){
                    data = original_data;
                }
                List<Command> filteredList = new ArrayList<>();

                for(Command cd:data){
                    if(cd.getTitle().toLowerCase().contains(query.toLowerCase()) || cd.getDescripition().toLowerCase().contains(query.toLowerCase())){
                        filteredList.add(cd);
                    }
                }

                FilterResults fr = new FilterResults();
                fr.values = filteredList;

                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (List<Command>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.command_title)
        TextView title;
        @BindView(R.id.command_desc)
        TextView desc;
        @BindView(R.id.command_layout)
        RelativeLayout layout;
        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void bind(Command command){
            title.setText(command.getTitle());
            desc.setText(command.getDescripition());
            // TODO layout click listener
        }

    }

    @NonNull
    @Override
    public CommandsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commands_list_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandsListAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItems(List<Command> data){
        if(this.data.equals(original_data)){
            this.data.addAll(data);
        }
        this.original_data.addAll(data);
        notifyDataSetChanged();
    }
}
