package com.example.periodicworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Context;
import android.os.Bundle;

import com.example.periodicworkmanager.Worker.UploadTask;
import com.example.periodicworkmanager.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize()
    {
        context = this;

        UploadTask.context=MainActivity.this;
        PeriodicWorkRequest periodicWorkRequest=new PeriodicWorkRequest.Builder(UploadTask.class,15, TimeUnit.SECONDS, 5, TimeUnit.SECONDS).build();
        WorkManager.getInstance(context).enqueue(periodicWorkRequest);
    }
}