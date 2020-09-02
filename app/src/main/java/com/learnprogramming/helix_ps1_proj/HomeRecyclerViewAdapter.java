package com.learnprogramming.helix_ps1_proj;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {
    private static final String TAG = "HomeRecyclerViewAdapter";
    private ArrayList<String> streamNames = new ArrayList<>();
    private ArrayList<String> imageURLs = new ArrayList<>();
    private Context mContext;

    public HomeRecyclerViewAdapter(ArrayList<String> mstreamNames, ArrayList<String> mimageURL, Context Context) {
        this.streamNames = mstreamNames;
        this.imageURLs= mimageURL;
        this.mContext = Context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: OnCreateViewHolder Start");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_listitem,parent,false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .load(imageURLs.get(position))
                .into(holder.thumbnailButton);


        holder.nameOfStream.setText(streamNames.get(position));

        holder.thumbnailButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ceIntent = new Intent(mContext, ViewStream.class);
                mContext.startActivity(ceIntent);

            }
        });

        holder.cardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ceIntent = new Intent(mContext, ViewStream.class);
                mContext.startActivity(ceIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return streamNames.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnailButton;
        TextView nameOfStream;
        CardView cardView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailButton = itemView.findViewById(R.id.thumnailpic);
            nameOfStream = itemView.findViewById(R.id.streamName);
            cardView = itemView.findViewById(R.id.homepage_item_card);

        }
    }
}
