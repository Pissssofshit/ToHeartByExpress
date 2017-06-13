package cn.zucc.qifeng.toheartbyexpress.itemOfNear;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.zucc.qifeng.toheartbyexpress.MapFragment;
import cn.zucc.qifeng.toheartbyexpress.MyApplication;
import cn.zucc.qifeng.toheartbyexpress.R;

/**
 * Created by 80421 on 2017/6/11.
 */

public class RecyclerVIewAdapterForTask extends RecyclerView.Adapter<RecyclerVIewAdapterForTask.TaskViewHolder>  {
    private List<MapFragment.mapTask> tasklist;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public RecyclerVIewAdapterForTask(List<MapFragment.mapTask> tasklist, Context context) {
        this.tasklist = tasklist;
        this.context = context;
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private ImageView taskpublishuserimage;
        private TextView taskpublishname, taskstroename, taskdistance, tasksum, tasktime;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskpublishuserimage = (ImageView) itemView.findViewById(R.id.task_userimage);
            taskpublishname = (TextView) itemView.findViewById(R.id.task_publishusername);
            taskstroename = (TextView) itemView.findViewById(R.id.task_storename);
            taskdistance = (TextView) itemView.findViewById(R.id.task_distance);
            tasksum = (TextView) itemView.findViewById(R.id.task_sum);
            tasktime = (TextView) itemView.findViewById(R.id.task_time);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.task_item, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        //
//        view.setOnClickListener(this);
        return taskViewHolder;
    }



    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        holder.taskpublishuserimage.setImageResource(R.drawable.boss);
        holder.taskpublishname.setText(tasklist.get(position).getUsername());
        holder.taskstroename.setText(tasklist.get(position).getStorename());
        holder.taskdistance.setText(tasklist.get(position).getDistance() + "");
        holder.tasksum.setText(tasklist.get(position).getSum() + "");
        holder.tasktime.setText(tasklist.get(position).getDeadline() + "前");
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }


    @Override
    public int getItemCount() {

         return tasklist.size();
    }
}
