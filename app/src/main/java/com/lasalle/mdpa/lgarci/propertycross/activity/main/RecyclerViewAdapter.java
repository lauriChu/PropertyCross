package com.lasalle.mdpa.lgarci.propertycross.activity.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lasalle.mdpa.lgarci.propertycross.R;

/**
 * Created by FurruPi on 22/1/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup current = (ViewGroup) mLayoutInflater.inflate(R.layout.card_view, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(current);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.textView.setText("position " + String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
