package com.example.weatherapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//import com.example.weatherapp.json.JSON;

public class MainActivity extends AppCompatActivity {

    public static MainActivity MAIN;

    boolean isLandscape()
    {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MAIN = this;

        setContentView(isLandscape() ? R.layout.horizontal_layout : R.layout.vertical_layout);
        //JSON.Object json = (JSON.Object) JSON.parse(getResources().getString(R.string.json_simple_object_example));

        TextView view = findViewById(R.id.string_display);

        if (view != null)
        {
            //view.setText(json.keySet().toString());
        }
    }
}