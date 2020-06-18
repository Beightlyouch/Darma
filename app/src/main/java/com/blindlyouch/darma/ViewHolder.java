package com.blindlyouch.darma;

import android.net.Uri;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView darma_image;
    public TextView darma_text;

    public ViewHolder(View itemView){
        super(itemView);
        darma_text=itemView.findViewById(R.id.darma_text);
        darma_image= itemView.findViewById(R.id.darma_image);
        itemView.setBackgroundColor(00000000);
    }

    public TextView getDarma_text(){return this.darma_text;}

}