package com.xianwei.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xianwei li on 8/14/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News newsItem = newsList.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.author.setText(newsItem.getAuthor());
        holder.section.setText(newsItem.getSection());
        holder.date.setText(newsItem.getDate().substring(0,10));
        holder.urlString = newsItem.getWebUrl();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView author;
        public TextView section;
        public TextView date;
        public View layout;
        public String urlString;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            title = (TextView) itemView.findViewById(R.id.title_tv);
            author = (TextView) itemView.findViewById(R.id.author_tv);
            section = (TextView) itemView.findViewById(R.id.section_tv);
            date = (TextView) itemView.findViewById(R.id.date_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
