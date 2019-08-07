package com.example.wanandroid_myy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.bean.PubItemBean;

import java.util.ArrayList;

public class PubItemRvAdapter extends RecyclerView.Adapter<PubItemRvAdapter.ViewHolder> {
    private ArrayList<PubItemBean.DatasBean> list;
    private Context context;

    public PubItemRvAdapter(ArrayList<PubItemBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_article_list, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.article_author.setText(list.get(i).getAuthor());
        viewHolder.article_title.setText(list.get(i).getTitle());
        viewHolder.article_niceDate.setText(list.get(i).getNiceDate());
        viewHolder.article_chapterName.setText(list.get(i).getSuperChapterName()+"/"+list.get(i).getChapterName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnItemClick(i, list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView article_title;
        private final TextView article_author;
        private final TextView article_niceDate;
        private final TextView article_chapterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_title = itemView.findViewById(R.id.tv_article_title);
            article_author = itemView.findViewById(R.id.tv_article_author);
            article_niceDate = itemView.findViewById(R.id.tv_article_niceDate);
            article_chapterName = itemView.findViewById(R.id.tv_article_chapterName);
        }
    }
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnItemClick(int p,PubItemBean.DatasBean datasBean);
    }
}
