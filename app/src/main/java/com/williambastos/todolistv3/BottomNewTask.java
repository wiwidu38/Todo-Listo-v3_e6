package com.williambastos.todolistv3;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "todolist";
    private EditText editTextAdd;
    private Button buttonAdd;



    public static BottomNewTask newInstance(){
        return new BottomNewTask();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_task , container , false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextAdd = view.findViewById(R.id.addTaskEditText);
        buttonAdd = view.findViewById(R.id.addTaskButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextAdd.getText().toString();
                if(taskName.isEmpty()){
                    Toast.makeText(view.getContext(),"Your task name is empty, retry !", Toast.LENGTH_SHORT).show();
                }else{
                    Task item = new Task(taskName);
                    dismiss();
                }
            }

        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
