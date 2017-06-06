package cn.zucc.qifeng.toheartbyexpress.PushTask;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.Bean.PurchaseItem;
import cn.zucc.qifeng.toheartbyexpress.R;

/**
 * Created by 80421 on 2017/6/6.
 */

public class RecyclerViewAdapterForPuchase extends RecyclerView.Adapter<RecyclerViewAdapterForPuchase.PurchaseViewHolder> {
    private List<PurchaseItem> list;
    private Context context;

    private static final String TAG="AdapterForPuchase";

    public RecyclerViewAdapterForPuchase(List<PurchaseItem> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.purchase_item, parent, false);
        PurchaseViewHolder purchaseViewHolder = new PurchaseViewHolder(view);
        return purchaseViewHolder;
    }

    @Override
    public void onBindViewHolder(final PurchaseViewHolder holder, final int position) {
        //holder.name.setText(list.get(position).getName());
        holder.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                //通过接口回调将数据传递到Activity中
                //mTextListener.onTextChanged(position, holder.name.getText().toString());
                list.get(position).setName(holder.name.getText().toString());
            }
        });
        //holder.details.setText(list.get(position).getDetails());
        holder.details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                    list.get(position).setDetails(holder.details.getText().toString());
            }
        });
        //holder.number.setText(list.get(position).getNumber());
        holder.number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String number=holder.number.getText().toString();
                Log.d(TAG,"number="+number);
                if (!"".equals(number))
                list.get(position).setNumber(Integer.parseInt(number));
            }
        });
        //holder.price.setText();
        holder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String price=holder.price.getText().toString();
                Log.d(TAG,"price="+price);
                if (!"".equals(price))
                list.get(position).setPrice(Double.valueOf(price));
            }
        });
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class PurchaseViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        EditText name;
        EditText details;
        EditText number;
        EditText price;

        public PurchaseViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.purchase_card);
            name = (EditText) itemView.findViewById(R.id.purchase_name);
            details = (EditText) itemView.findViewById(R.id.purchase_details);
            number = (EditText) itemView.findViewById(R.id.purchase_number);
            price = (EditText) itemView.findViewById(R.id.purchase_price);
        }
    }

}
