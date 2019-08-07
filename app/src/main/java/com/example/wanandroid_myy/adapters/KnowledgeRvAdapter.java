package com.example.wanandroid_myy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.fragments.ProFragment;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeRvAdapter extends RecyclerView.Adapter<KnowledgeRvAdapter.ViewHolder> {
    private ArrayList<KnowledgeTreeData> list;
    private Context context;

    public KnowledgeRvAdapter(ArrayList<KnowledgeTreeData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_know_1, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.knowledge_title.setText(list.get(i).getName());
        final List<KnowledgeTreeData.ChildrenBean> childrenBeans = list.get(i).getChildren();
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < childrenBeans.size(); j++) {
            sb.append(childrenBeans.get(j).getName()+"    ");
        }
        viewHolder.title_child.setText(sb.toString());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.OnItemClick(i,list.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView knowledge_title;
        private final TextView title_child;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            knowledge_title = itemView.findViewById(R.id.knowledge_title);
            title_child = itemView.findViewById(R.id.title_child);
        }
    }
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnItemClick(int p,KnowledgeTreeData knowledgeTreeData);
    }
}
