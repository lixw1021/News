package com.xianwei.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.xianwei.news.R;
import com.xianwei.news.WebActivity;
import com.xianwei.news.models.News;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 8/14/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String URL_STRING = "urlString";
    private List<News> newsList;
    Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News newsItem = newsList.get(position);

        if (!TextUtils.isEmpty(newsItem.getImageUrlString())) {
            Picasso.with(context)
                    .load(newsItem.getImageUrlString())
                    .resize(1920, 1080)
                    .onlyScaleDown()
                    .error(R.drawable.ic_broken_image)
                    .into(holder.imageView);
        }

        holder.title.setText(newsItem.getTitle());
        holder.description.setText(newsItem.getDescription());
        String date = newsItem.getDate();
        if (date != null && date.length() > 9) {
            holder.date.setText(newsItem.getDate().substring(0, 10));
        }
        holder.urlString = newsItem.getUrlString();
    }

    @Override
    public int getItemCount() {
        if (newsList == null) {
            return 0;
        }
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_cover_iv)
        ImageView imageView;
        @BindView(R.id.item_title_tv)
        TextView title;
        @BindView(R.id.item_description_tv)
        TextView description;
        @BindView(R.id.item_date_tv)
        TextView date;
        @BindView(R.id.item_share_ib)
        ImageButton shareButton;
        String urlString;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), WebActivity.class);
                    intent.putExtra(URL_STRING, urlString);
                    v.getContext().startActivity(intent);
                }
            });

            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "news");
                    intent.putExtra(Intent.EXTRA_TEXT, urlString);

                    v.getContext().startActivity(Intent.createChooser(intent, "Share news"));
                }
            });
        }
    }

    public void setNewsData(List<News> newData) {
        newsList = newData;
        notifyDataSetChanged();
    }
}
