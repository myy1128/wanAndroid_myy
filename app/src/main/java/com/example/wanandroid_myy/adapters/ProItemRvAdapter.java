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
import com.example.wanandroid_myy.bean.ProItemBean;

import java.util.ArrayList;

public class ProItemRvAdapter extends RecyclerView.Adapter<ProItemRvAdapter.ViewHolder> {
    private ArrayList<ProItemBean.DatasBean> list;
    private Context context;

    public ProItemRvAdapter(ArrayList<ProItemBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.pro_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getEnvelopePic()).into(viewHolder.item_project_list_iv);
        viewHolder.item_project_list_title_tv.setText(list.get(i).getTitle());
        viewHolder.item_project_list_content_tv.setText(list.get(i).getDesc());
        viewHolder.item_project_list_time_tv.setText(list.get(i).getNiceDate());
        viewHolder.item_project_list_author_tv.setText(list.get(i).getAuthor());
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

        private final ImageView item_project_list_iv;
        private final TextView item_project_list_title_tv;
        private final TextView item_project_list_content_tv;
        private final TextView item_project_list_time_tv;
        private final TextView item_project_list_author_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_project_list_iv = itemView.findViewById(R.id.item_project_list_iv);
            item_project_list_title_tv = itemView.findViewById(R.id.item_project_list_title_tv);
            item_project_list_content_tv = itemView.findViewById(R.id.item_project_list_content_tv);
            item_project_list_time_tv = itemView.findViewById(R.id.item_project_list_time_tv);
            item_project_list_author_tv = itemView.findViewById(R.id.item_project_list_author_tv);
        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void OnItemClick(int p, ProItemBean.DatasBean datasBean);
    }
}
