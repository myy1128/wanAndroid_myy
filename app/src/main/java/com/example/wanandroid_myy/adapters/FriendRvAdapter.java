package com.example.wanandroid_myy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.util.ColorUtils;
import com.example.wanandroid_myy.util.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FriendRvAdapter extends RecyclerView.Adapter<FriendRvAdapter.ViewHolder> {
    private ArrayList<FriendBean> list;
    private Context context;

    public FriendRvAdapter(ArrayList<FriendBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<FriendBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.item_navigation, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        if (list != null) {
            for (int j = 0; j < list.size(); j++) {
                TextView view = (TextView) View.inflate(context,R.layout.iten_flow,null);
                view.setText(list.get(j).getName());
                view.setTextColor(ColorUtils.getRandomColor());
                viewHolder.item_navigation_flow_layout.addView(view);
                final int finalJ = j;
                final int finalJ1 = j;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.OnItemClick(finalJ,list.get(finalJ1));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FlowLayout item_navigation_flow_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_navigation_flow_layout = itemView.findViewById(R.id.item_navigation_flow_layout);
        }
    }
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnItemClick(int p, FriendBean dataBean);
    }
}
