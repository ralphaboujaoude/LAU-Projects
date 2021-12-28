package com.example.todo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.todo.AddTask;
import com.example.todo.MainActivity;
import com.example.todo.Model.ToDoModel;
import com.example.todo.R;
import com.example.todo.Utils.DatabaseHelper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    private List<ToDoModel> mList;
    private MainActivity activity;
    private DatabaseHelper db;

    public ToDoAdapter(DatabaseHelper db, MainActivity activity){
        this.activity=activity;
        this.db=db;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new MyViewHolder(v);
    }
    public Context getContext(){
        return activity;
    }

    public void setTasks(List<ToDoModel> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position){
        ToDoModel item=mList.get(position);
        db.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editTask(int position){
        ToDoModel item=mList.get(position);

        Bundle bundle=new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task",item.getTask());

        AddTask task= new AddTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(),task.getTag());

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoModel item= mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBool(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   db.updateStatus(item.getId(),1);
               }else{
                   db.updateStatus(item.getId(),0);
               }
            }
        });

    }
    public boolean toBool(int num){
        return num!=0;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox=itemView.findViewById(R.id.mcheckbox);
        }
    }
}
