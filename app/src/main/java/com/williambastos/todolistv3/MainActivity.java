package com.williambastos.todolistv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements BottomNewTask.OnInputListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    private ArrayList<Task> tasks = new ArrayList<>();
    public Task t;
    public static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIClient();

        if(getIntent().getSerializableExtra("tasks_list") != null){
            this.tasks.clear();
            this.tasks.addAll((ArrayList<Task>)getIntent().getSerializableExtra("task_list"));
        }
        FloatingActionButton addNewTask = findViewById(R.id.floating_button);

        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNewTask.newInstance().show(getSupportFragmentManager() , BottomNewTask.TAG);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        this.lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        this.adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void sendInput(Task newTask) {
        tasks.add(0,newTask);
        refresh();
    }

    private void refresh() {
        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
    }

    public void APIClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Api api = retrofit.create(Api.class);
        api.getTodos().enqueue(new Callback<ArrayList<Task>>() {
            @Override
            public void onResponse(Call<ArrayList<Task>> call, Response<ArrayList<Task>> response) {
                if(response.isSuccessful()){
                    tasks.clear();
                    tasks = response.body();
                    fill(tasks);
                    Collections.reverse(tasks);
                    refresh();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Task>> call, Throwable t) {
            }
        });
    }

    private void fill(ArrayList<Task> t) {
        t.add(new Task("Sacar el perro."));
        t.add(new Task("Comprar el pan."));
        t.add(new Task("Hacer ejercicio."));
        t.add(new Task("Preparar reuniones del dia"));
        t.add(new Task("Revisar el correo de la Salle."));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}