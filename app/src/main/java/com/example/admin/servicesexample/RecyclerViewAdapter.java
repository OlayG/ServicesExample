package com.example.admin.servicesexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/14/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<String> stringList = new ArrayList<>();

    public RecyclerViewAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDisplayString;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDisplayString = (TextView) itemView.findViewById(R.id.tvDisplayString);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String str = stringList.get(position);

        holder.tvDisplayString.setText(str);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
