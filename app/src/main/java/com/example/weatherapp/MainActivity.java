package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.weatherapp.json.JSON;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSON.Array array = new JSON.Array();
        array.add(new JSON.String("12345"));
        array.add(new JSON.String("nani"));

        JSON.Object object = new JSON.Object();
        object.put("Gtiotm", new JSON.String("Gabealingam"));

        array.add(object);
        TextView textView = findViewById(R.id.test);
        textView.setText(array.toString());
    }
}
