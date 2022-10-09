package com.example.schedulebud.main_activity_fragments.schedule;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulebud.NotificationUtils;
import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;
import com.google.gson.Gson;

import java.util.Calendar;

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ViewHolder> {

    private ToDoTaskList mData;
    private LayoutInflater mInflater;
    private Context context;
    private ToDoFragment toDoFragment;

    public ToDoRecyclerViewAdapter(ToDoFragment toDoFragment, Context context, ToDoTaskList data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.toDoFragment = toDoFragment;
    }

    @Override
    public ToDoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.todorecyclerview_row, parent, false);
        return new ToDoRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoRecyclerViewAdapter.ViewHolder holder, int position) {
        ToDoTask toDoTask = mData.getTasks().get(position);
        holder.toDoTitle.setText(toDoTask.getName());
        if (toDoTask.isHasDeadline()) {
            String deadline = prefConfig.makeDateTimeString(toDoTask.getDeadline().get(Calendar.DAY_OF_MONTH),
                    toDoTask.getDeadline().get(Calendar.MONTH), toDoTask.getDeadline().get(Calendar.YEAR),
                    toDoTask.getDeadline().get(Calendar.HOUR_OF_DAY), toDoTask.getDeadline().get(Calendar.MINUTE));
            holder.toDoDeadline.setText(deadline);
            updateNotifyButton(toDoTask, holder);
        } else {
            holder.toDoDeadline.setText("-");
            holder.toDoNotifyBtn.setImageResource(R.drawable.ic_baseline_alarm_off_24);
        }
        holder.toDoDetails.setText(toDoTask.getRemarks());
        if (toDoTask.isImportant()) {
            holder.toDoImportantImageView.setVisibility(View.VISIBLE);
        } else {
            holder.toDoImportantImageView.setVisibility(View.GONE);
        }
        holder.toDoEditBtn.setOnClickListener(view -> {
            Intent intent = new Intent(context, AddEditToDoTaskActivity.class);
            intent.putExtra("add", false);
            Gson gson = new Gson();
            String json = gson.toJson(toDoTask);
            intent.putExtra("toDoTask", json);
            context.startActivity(intent);
        });
        holder.toDoDeleteBtn.setOnClickListener(view -> {
            mData.getTasks().remove(toDoTask);
            prefConfig.saveToDoTaskListPref(context, mData);
            toDoFragment.updateToDoRecyclerView();
        });
    }

    @Override
    public int getItemCount() {
        return mData.getTasks().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView toDoTitle, toDoDeadline, toDoDetails;
        ImageView toDoImportantImageView;
        ImageButton toDoNotifyBtn, toDoEditBtn, toDoDeleteBtn;

        ViewHolder(View itemView) {
            super(itemView);
            toDoTitle = itemView.findViewById(R.id.toDoTitle);
            toDoDeadline = itemView.findViewById(R.id.toDoDeadline);
            toDoDetails = itemView.findViewById(R.id.toDoDetails);
            toDoImportantImageView = itemView.findViewById(R.id.toDoImportantImageView);
            toDoNotifyBtn = itemView.findViewById(R.id.toDoNotifyBtn);
            toDoEditBtn = itemView.findViewById(R.id.toDoEditBtn);
            toDoDeleteBtn = itemView.findViewById(R.id.toDoDeleteBtn);
        }
    }

    private void updateNotifyButton(ToDoTask toDoTask, ViewHolder holder) {
        if (toDoTask.isNotify()) {
            holder.toDoNotifyBtn.setImageResource(R.drawable.ic_baseline_alarm_on_24);
            holder.toDoNotifyBtn.setOnClickListener(view -> {
                holder.toDoNotifyBtn.setImageResource(R.drawable.ic_baseline_alarm_off_24);
                toDoTask.setNotify(false);
                prefConfig.saveToDoTaskListPref(context, mData);
                updateNotifyButton(toDoTask, holder);
                Toast.makeText(context, "Reminder cancelled", Toast.LENGTH_SHORT).show();
                NotificationUtils _notificationUtils = new NotificationUtils(context);
                long toDoTaskTime = toDoTask.getDeadline().getTimeInMillis();
                long oneHourBefore = toDoTaskTime - 1000*60*60;
                long twelveHoursBefore = toDoTaskTime - 1000*60*60*12;
                long oneDayBefore = toDoTaskTime - 1000*60*60*24;
                _notificationUtils.setReminder(toDoTaskTime, toDoTask.getName()+" NOW", toDoTask.getRemarks(), toDoTask.getCounterNum());
                _notificationUtils.cancelReminder(oneHourBefore, toDoTask.getName()+" 1h", toDoTask.getRemarks(), toDoTask.getCounterNum()+1);
                _notificationUtils.cancelReminder(twelveHoursBefore, toDoTask.getName()+" 12h", toDoTask.getRemarks(), toDoTask.getCounterNum()+2);
                _notificationUtils.cancelReminder(oneDayBefore, toDoTask.getName()+" 1d", toDoTask.getRemarks(), toDoTask.getCounterNum()+3);
            });
        } else {
            holder.toDoNotifyBtn.setImageResource(R.drawable.ic_baseline_alarm_off_24);
            holder.toDoNotifyBtn.setOnClickListener(view -> {
                holder.toDoNotifyBtn.setImageResource(R.drawable.ic_baseline_alarm_on_24);
                toDoTask.setNotify(true);
                prefConfig.saveToDoTaskListPref(context, mData);
                updateNotifyButton(toDoTask, holder);
                Toast.makeText(context, "Reminder set", Toast.LENGTH_SHORT).show();
                NotificationUtils _notificationUtils = new NotificationUtils(context);
                long toDoTaskTime = toDoTask.getDeadline().getTimeInMillis();
                long oneHourBefore = toDoTaskTime - 1000*60*60;
                long twelveHoursBefore = toDoTaskTime - 1000*60*60*12;
                long oneDayBefore = toDoTaskTime - 1000*60*60*24;
                _notificationUtils.setReminder(toDoTaskTime, toDoTask.getName()+" NOW", toDoTask.getRemarks(), toDoTask.getCounterNum());
                _notificationUtils.setReminder(oneHourBefore, toDoTask.getName()+" 1h", toDoTask.getRemarks(), toDoTask.getCounterNum()+1);
                _notificationUtils.setReminder(twelveHoursBefore, toDoTask.getName()+" 12h", toDoTask.getRemarks(), toDoTask.getCounterNum()+2);
                _notificationUtils.setReminder(oneDayBefore, toDoTask.getName()+" 1d", toDoTask.getRemarks(), toDoTask.getCounterNum()+3);
            });
        }
    }
}
