package com.example.sqlite_news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<NewsModel> news;

    public NewsAdapter(Context context, List<NewsModel> news) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);

//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reader, parent, false);
//        return new ViewHolder(parent.getContext(),itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsModel news = this.news.get(position);
//        Log.i("12312322", "onCreate: 123" );
        holder.name.setText(news.name);
        holder.description.setText(news.description);
        holder.date.setText(news.date);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, description, date;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.txtNewsNameCard);
            description = view.findViewById(R.id.txtNewsDescriptionCard);
            date = view.findViewById(R.id.txtDateCard);

            cardView = view.findViewById(R.id.cardView);
        }

        public TextView getName() {
            return name;
        }

        public TextView getDescription() {
            return description;
        }

        public TextView getDate() {
            return date;
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setCardView(CardView cardView) {
            this.cardView = cardView;
        }
    }
}
