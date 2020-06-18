package com.blindlyouch.darma;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.RealmResults;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private RealmResults<Darma> rResults;
    private Context context;
    private ArrayList<Integer> idArray;

    public CustomRecyclerViewAdapter(){}

    public CustomRecyclerViewAdapter(RealmResults<Darma> realmResults){
        rResults = realmResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
       View view = LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.one_darma_card ,parent,false);
       ViewHolder viewHolder = new ViewHolder(view);
       this.context = parent.getContext();
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Darma darma = rResults.get(position);
        final int id = darma.getDarma_id();
        final int pos = position;
        holder.darma_text.setText(darma.getDarma_wish());
        holder.darma_image.setImageURI(setUri(darma.getDarma_eye_num()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EyeActivity.class);
                intent.putExtra("darma_wish",darma.getDarma_wish());
                intent.putExtra("darma_eye_num",darma.getDarma_eye_num());
                intent.putExtra("id", id);
                view.getContext().startActivity(intent);
            }
        });

    }

    /*
    public void getselected(int position){
        final Darma darma = rResults.get(position);
        final int id = darma.getDarma_id();
        darma.setClicked(true);
    }*/

    public Uri setUri(int eye){
        Uri uri = Uri.parse("");

        switch(eye){
            case 0:
                uri=  Uri.parse("android.resource://com.blindlyouch.darma/drawable/darma_red");
                break;

            case 1:
                uri=  Uri.parse("android.resource://com.blindlyouch.darma/drawable/one_eye");
                break;

            case 2:
                uri = Uri.parse("android.resource://com.blindlyouch.darma/drawable/both_eyes");
                break;
        }
        return uri;
    }

    @Override
    public int getItemCount() {
        return rResults.size();
    }

}