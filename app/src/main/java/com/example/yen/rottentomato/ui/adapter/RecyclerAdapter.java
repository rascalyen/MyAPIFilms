package com.example.yen.rottentomato.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.rottentomato.R;
import com.example.yen.rottentomato.data.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yenhuang on 2/11/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    @Inject Picasso picasso;
    private List<Movie> movieList;
    private Context mContext;
    private static ClickListener clickListener;

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.iv_icon)         ImageView icon;
        @Bind(R.id.tv_first)        TextView first;
        @Bind(R.id.tv_second)       TextView second;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }


    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int i) {
        Movie movie = movieList.get(i);

        picasso.load(movie.getPoster().getThumbnail()).resize(216, 288).centerCrop().into(holder.icon);
        holder.first.setText(movie.getTitle());
        holder.second.setText(movie.getMpaaRating());
    }

    @Override
    public int getItemCount() {
        return (null != movieList ? movieList.size() : 0);
    }

    public void addAll(List list){
        if (!movieList.isEmpty()) {
            movieList.clear();
            movieList.addAll(list);
        }
        else {
            movieList = list;
        }
        notifyDataSetChanged();
    }

    public Movie getItem(int position) {
        if (!movieList.isEmpty())
            return movieList.get(position);

        return null;
    }

}