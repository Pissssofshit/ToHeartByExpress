package cn.zucc.qifeng.toheartbyexpress.itemofMessagepage;

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
 * Created by 80421 on 2017/5/30.
 */

public class RecyclerViewAdapterForMessage extends RecyclerView.Adapter<RecyclerViewAdapterForMessage.MessageViewHolder> {
    private List<Message> messages;
    private Context context;

    public RecyclerViewAdapterForMessage(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView Message_photo;
        TextView Message_title;
        TextView Message_details;

        public MessageViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.message_crad);
            Message_photo = (ImageView) itemView.findViewById(R.id.message_photo);
            Message_title = (TextView) itemView.findViewById(R.id.message_title);
            Message_details = (TextView) itemView.findViewById(R.id.message_details);
            Message_title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.message_item, parent, false);
        MessageViewHolder goodsViewHolder = new MessageViewHolder(view);
        return goodsViewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.Message_photo.setImageResource(messages.get(position).getPhotoId());
        holder.Message_title.setText(messages.get(position).getTitle());
        holder.Message_details.setText(messages.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
