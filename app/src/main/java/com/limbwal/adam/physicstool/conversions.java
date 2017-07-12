package com.limbwal.adam.physicstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.limbwal.adam.physicstool.R;

public class conversions extends AppCompatActivity {
    Button distance;
    Button mass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_conversions);
        distance = (Button) findViewById(R.id.distance3);
        mass = (Button) findViewById(R.id.mass);
        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDistance();
            }
        });
        mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMass();
            }
        });
    }
    public void goToDistance() {
        Intent i = new Intent(this, distance.class);
        startActivity(i);
    }
    public void goToMass() {
        Intent i = new Intent(this, mass.class);
        startActivity(i);
    }
}
