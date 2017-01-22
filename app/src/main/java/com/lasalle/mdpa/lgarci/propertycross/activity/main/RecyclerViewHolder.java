package com.lasalle.mdpa.lgarci.propertycross.activity.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lasalle.mdpa.lgarci.propertycross.R;

/**
 * Created by FurruPi on 22/1/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    //public ImageView imageView;
    public TextView textView;

    public RecyclerViewHolder(View view){
        super(view);
        textView = (TextView) view.findViewById(R.id.textView_title);
        //imageView = (ImageView) view.findViewById(R.id.imageView);
    }
}
