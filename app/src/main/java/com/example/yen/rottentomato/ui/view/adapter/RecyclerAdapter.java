package com.example.yen.rottentomato.ui.view.adapter;

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
    private ClickListener clickListener;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_icon)         ImageView icon;
        @Bind(R.id.tv_first)        TextView first;
        @Bind(R.id.tv_second)       TextView second;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void addListener(final Movie movie, final ClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });
        }
    }


    public interface ClickListener {
        void onItemClick(Movie movie);
    }


    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.mContext = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
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
        holder.addListener(movieList.get(i), clickListener);
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