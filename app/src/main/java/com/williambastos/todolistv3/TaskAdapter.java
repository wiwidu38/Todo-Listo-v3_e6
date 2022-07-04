package com.williambastos.todolistv3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> tasks;
    private String mText = "";

    public TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        if(task.getIsDone())
            holder.itemView.setBackgroundColor(Color.GREEN);
        else
            holder.itemView.setBackgroundColor(Color.RED);

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Edit task : " + holder.getAdapterPosition());

                EditText input = new EditText(view.getContext());
                input.setText(task.getTaskName());
                builder.setView(input);

                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(input.getText().toString().isEmpty()){
                            Toast.makeText(view.getContext(),"Your task name is empty", Toast.LENGTH_SHORT).show();
                        }else{
                            task.setTaskName(input.getText().toString());
                            notifyItemChanged(holder.getAdapterPosition());
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }

        });

        holder.check.setChecked(task.getIsDone());
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                    holder.itemView.setBackgroundColor(Color.GREEN);
                else
                    holder.itemView.setBackgroundColor(Color.RED);
                task.setIsDone(checked);

            }
        });
        holder.text.setText(task.getTaskName());
        holder.button.setOnClickListener(view -> {
            this.tasks.remove(position);
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(position,this.tasks.size());
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Initializing the Views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public CheckBox check;
        public ImageView button;

        public ViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.task_text);
            button = (ImageView) view.findViewById(R.id.delete);
            check = (CheckBox) view.findViewById(R.id.task_checkbox);
        }
    }
}
