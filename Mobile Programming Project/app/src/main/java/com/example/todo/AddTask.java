package com.example.todo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todo.Model.ToDoModel;
import com.example.todo.Utils.DatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddTask  extends BottomSheetDialogFragment {
    public static final String TAG="AddTask";

    private EditText mEditText;
    private Button mSaveButton;

    private DatabaseHelper db;
    public static AddTask newInstance(){
        return new AddTask();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.add_task, container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText=view.findViewById(R.id.editText);
        mSaveButton=view.findViewById(R.id.button_save);

        db=new DatabaseHelper(getActivity());


        boolean update=false;
        Bundle bundle=getArguments();

        if(bundle !=null){
            update=true;
            String task=bundle.getString("task");
            mEditText.setText(task);

            if(task.length()>0){
                mSaveButton.setEnabled(false);
            }
        }
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.gray));

                }else{
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(getResources().getColor(R.color.aqua));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final boolean finalupdate=update;
        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text=mEditText.getText().toString();

                if(finalupdate){
                    db.updateTask(bundle.getInt("id"),text);
                }else{
                    ToDoModel item=new ToDoModel();
                    item.setTask(text);
                    item.setStatus(0);
                    db.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity=getActivity();
        if(activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).OnDialogClose(dialog);
        }
    }
}
