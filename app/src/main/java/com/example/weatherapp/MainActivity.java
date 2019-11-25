package com.example.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> names = new ArrayAdapter<>(
            this,
            R.layout.activity_main, R.id.textView,
            getResources().getStringArray(R.array.test_array_2)
        );

        listView.setAdapter(names);
    }

    public void onClick(View v)
    {
        Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }

}