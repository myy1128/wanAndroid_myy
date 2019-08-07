package com.example.wanandroid_myy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ArticleListData.DatasBean> itemList;
    private ArrayList<HomeBanBean> banList;
    private Context context;

    public HomeRvAdapter(ArrayList<ArticleListData.DatasBean> itemList, ArrayList<HomeBanBean> banList, Context context) {
        this.itemList = itemList;
        this.banList = banList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View inflate = View.inflate(context, R.layout.item_ban, null);
            BanViewHolder banViewHolder = new BanViewHolder(inflate);
            return banViewHolder;
        } else {
            View inflate = View.inflate(context, R.layout.item_article_list, null);
            ItemViewHolder itemViewHolder = new ItemViewHolder(inflate);
            return itemViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.article_author.setText(itemList.get(i - 1).getAuthor());
            holder.article_title.setText(itemList.get(i - 1).getTitle());
            holder.article_chapterName.setText(itemList.get(i - 1).getSuperChapterName() + "/" + itemList.get(i - 1).getChapterName());
            holder.article_niceDate.setText(itemList.get(i - 1).getNiceDate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myonClickListener.OnItemClick(i-1, itemList.get(i-1));
                }
            });
        } else if (viewHolder instanceof BanViewHolder) {
            BanViewHolder holder = (BanViewHolder) viewHolder;
            ArrayList<String> title = new ArrayList<>();
            for (int j = 0; j < banList.size(); j++) {
                HomeBanBean dataBean = banList.get(j);
                title.add(dataBean.getTitle());
            }

            Banner start = holder.head_banner.setImages(banList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBanBean dataBean = (HomeBanBean) path;
                    Glide.with(context).load(dataBean.getImagePath()).into(imageView);
                }
            }).setBannerAnimation(Transformer.Accordion)
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setBannerTitles(title)
                    .start();

        }
    }

    @Override
    public int getItemCount() {
        if (banList != null) {
            return itemList.size() + 1;
        } else {
            return itemList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public class BanViewHolder extends RecyclerView.ViewHolder {

        private final Banner head_banner;

        public BanViewHolder(@NonNull View itemView) {
            super(itemView);
            head_banner = itemView.findViewById(R.id.head_banner);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView article_title;
        private final TextView article_author;
        private final TextView article_niceDate;
        private final TextView article_chapterName;
        private  LinearLayout linearLayout;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.item_search_pager_group);
            article_title = itemView.findViewById(R.id.tv_article_title);
            article_author = itemView.findViewById(R.id.tv_article_author);
            article_niceDate = itemView.findViewById(R.id.tv_article_niceDate);
            article_chapterName = itemView.findViewById(R.id.tv_article_chapterName);
        }
    }

    private MyOnClickListener myonClickListener;

    public void setMyOnClickListener(MyOnClickListener myonClickListener) {
        this.myonClickListener = myonClickListener;
    }

    public interface MyOnClickListener {
        void OnItemClick(int p, ArticleListData.DatasBean datasBean);
    }
}
