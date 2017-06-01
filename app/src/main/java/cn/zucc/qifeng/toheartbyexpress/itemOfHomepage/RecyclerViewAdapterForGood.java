package cn.zucc.qifeng.toheartbyexpress.itemOfHomepage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.MyApplication;
import cn.zucc.qifeng.toheartbyexpress.R;

/**
 * Created by 80421 on 2017/5/22.
 */

public class RecyclerViewAdapterForGood extends RecyclerView.Adapter<RecyclerViewAdapterForGood.GoodsViewHolder> {
    private List<Goods> goodses;
    private Context context;


    public RecyclerViewAdapterForGood(List<Goods> goodses, Context context) {
        this.goodses = goodses;
        this.context = context;

    }
    static class GoodsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView goods_photo;
        TextView goods_title;
        TextView goods_details;

        public GoodsViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.goods_card);
            goods_photo = (ImageView) itemView.findViewById(R.id.goods_photo);
            goods_title = (TextView) itemView.findViewById(R.id.goods_title);
            goods_details = (TextView) itemView.findViewById(R.id.goods_details);
            goods_title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.goods_item, parent, false);
        GoodsViewHolder goodsViewHolder = new GoodsViewHolder(view);
        return goodsViewHolder;
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
        holder.goods_photo.setImageResource(goodses.get(position).getPhotoId());
        holder.goods_title.setText(goodses.get(position).getTitle());
        holder.goods_details.setText(goodses.get(position).getDetails());


    }

    @Override
    public int getItemCount() {
        return goodses.size();
    }
}
