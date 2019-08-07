package com.example.wanandroid.adapers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.bean.ListData;
import com.example.wanandroid.bean.TopListBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HomeListBean.DatasBean>  list  = new ArrayList<>();
    List<BannerBean.DataBean>  beanList  = new ArrayList<>();
    List<TopListBean.DataBean>  toplist = new ArrayList<>();
    Context context;

    public HomeAdaper(Context context) {
        this.context = context;
    }

    public void getlistdata(List<HomeListBean.DatasBean>  list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void getbannerList( List<BannerBean.DataBean>  beanList){
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }
    public void getTopList(List<TopListBean.DataBean>  toplist){
        this.toplist.addAll(toplist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            View inflate = View.inflate(context, R.layout.home_banner_item_layout, null);
            return new ViewHolder1(inflate);
        }else {
           // View inflate = View.inflate(context, R.layout.home_item_list_layout, null);
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_list_layout, viewGroup, false);
            return new ViewHolder2(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type==1){
            ViewHolder1 holder1 = (ViewHolder1) viewHolder;
            holder1.head_banner.setBannerAnimation(Transformer.Accordion);
            ArrayList<String> bannertitle = new ArrayList<>();
            for (int j = 0; j < beanList.size(); j++) {
                bannertitle.add(beanList.get(j).getTitle());
            }
            holder1.head_banner.setBannerTitles(bannertitle);
            holder1.head_banner.setBannerStyle(Gravity.LEFT);
            holder1.head_banner.setImages(beanList).setImageLoader(new MyImg()).start();
        }else{

            if (beanList.size()>0){
                i=i-1;
            }

            ViewHolder2 holder2 = (ViewHolder2) viewHolder;
            holder2.tv_article_author.setText(list.get(i).getAuthor());
            holder2.tv_article_chapterName.setText(list.get(i).getChapterName());
            holder2.tv_article_niceDate.setText(list.get(i).getNiceDate());
            holder2.tv_article_title.setText(list.get(i).getTitle());

        }
    }

    @Override
    public int getItemCount() {
        if (beanList.size()>0){
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (beanList.size()>0 && position==0){
            return 1;
        }else {
            return 2;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        Banner head_banner;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            head_banner=  itemView.findViewById(R.id.head_banner);
        }
    }



    class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView tv_article_title;
        TextView tv_article_author;
        TextView tv_article_chapterName;
        TextView tv_article_niceDate;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            tv_article_title=   itemView.findViewById(R.id.tv_article_title);
            tv_article_author=  itemView.findViewById(R.id.tv_article_author);
            tv_article_chapterName=   itemView.findViewById(R.id.tv_article_chapterName);
            itemView.findViewById(R.id.iv_article_like);
            tv_article_niceDate=  itemView.findViewById(R.id.tv_article_niceDate);
        }
    }

    class MyImg extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            BannerBean.DataBean bean = (BannerBean.DataBean) path;
            Glide.with(context).load(bean.getImagePath()).into(imageView);
        }
    }

}
