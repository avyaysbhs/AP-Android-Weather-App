package com.example.weatherapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String COUNTER_KEY = "count";
    public static MainActivity MAIN;
    int counter;

    boolean isLandscape()
    {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            counter = savedInstanceState.getInt(COUNTER_KEY);
        Log.d("TAG", "Create");

        MAIN = this;

        setContentView(isLandscape() ? R.layout.horizontal_layout : R.layout.vertical_layout);
        //JSON.Object json = (JSON.Object) JSON.parse(getResources().getString(R.string.json_simple_object_example));

        TextView view = findViewById(R.id.string_display);

        if (view != null) {
            setup(view);
        } else {
            setup((TextView) findViewById(R.id.textView_horizontal));
        }
    }

    private void setup(final TextView view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                view.setText("Count: " + counter);
            }
        });
        view.setText("Count: " + counter);
    }

    protected void onResume()
    {
        super.onResume();
        Log.d("TAG", "Resume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("count", counter);
        super.onSaveInstanceState(outState);
    }
}