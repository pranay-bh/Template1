package com.solvedunsloved.template1.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solvedunsloved.template1.R;
import com.solvedunsloved.template1.data.color;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.ViewHolder> {

    private Context context;
    private List<color> colorList;

    public RVadapter(Context context, List<color> colorList) {
        this.context = context;
        this.colorList = colorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        color color1 = colorList.get(position);
        String imageurl=color1.getUrl();
        holder.title.setText("Title : " + color1.getTitle());
        holder.id.setText("ID    : " + color1.getID());

        Picasso.get()
                .load(imageurl)
                .placeholder(android.R.drawable.ic_popup_sync)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView id;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.imageView);
            id = itemView.findViewById(R.id.ID);

        }
    }
}
