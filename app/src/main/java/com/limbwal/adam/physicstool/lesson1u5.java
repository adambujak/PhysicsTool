package com.limbwal.adam.physicstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.limbwal.adam.physicstool.R;

public class lesson1u5 extends AppCompatActivity {
    Button simulate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lesson1u5);
        simulate = (Button) findViewById(R.id.simButton);
        simulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSim();
            }
        });
    }
    public void goToSim() {
        Intent a = new Intent(this, sim.class);
        startActivity(a);
    }

}
