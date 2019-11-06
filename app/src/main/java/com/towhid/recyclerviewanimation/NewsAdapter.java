package com.towhid.recyclerviewanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>  implements Filterable{

    Context mContext;
    List<NewsItem> mData;
    List<NewsItem> mDataFiltered;
    boolean isDark = false;


    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;

    }

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false);


        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder newsViewHolder, int position) {


        newsViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
        newsViewHolder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        // bind data
        newsViewHolder.tv_title.setText(mDataFiltered.get(position).getTitle());
        newsViewHolder.tv_date.setText(mDataFiltered.get(position).getDate());
        newsViewHolder.tv_description.setText(mDataFiltered.get(position).getContent());
        newsViewHolder.img_user.setImageResource(mDataFiltered.get(position).getUserPhoto());


    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    mDataFiltered = mData;
                } else {
                    List<NewsItem> lstFiltered = new ArrayList<>();
                    for (NewsItem row : mData) {
                        if (row.getTitle().toLowerCase().contains(Key.toLowerCase())) {

                            lstFiltered.add(row);
                        }
                    }

                    mDataFiltered = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;


            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFiltered = (List<NewsItem>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder  {

        TextView tv_title;
        TextView tv_description;
        TextView tv_date;
        ImageView img_user;
        RelativeLayout relativeLayout;


        public NewsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            img_user = (ImageView) itemView.findViewById(R.id.img_user);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

            if (isDark) {
                setDarkTheme();
            }


        }


        private void setDarkTheme() {
            relativeLayout.setBackgroundResource(R.drawable.card_back_dark);
        }

    }

}
