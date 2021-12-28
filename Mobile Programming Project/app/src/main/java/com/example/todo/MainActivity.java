package com.example.todo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.ToDoAdapter;
import com.example.todo.Model.ToDoModel;
import com.example.todo.Utils.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity  extends AppCompatActivity implements OnDialogCloseListener{

    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DatabaseHelper db;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.fab);
        db=new DatabaseHelper(MainActivity.this);
        mList=new ArrayList<>();
        adapter = new ToDoAdapter(db, MainActivity.this);

        mList=db.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager(),AddTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void OnDialogClose(DialogInterface dialogInterface) {
        mList=db.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();
    }
}
