package com.limbwal.adam.physicstool;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.limbwal.adam.physicstool.R;

public class learn extends Activity{
    Button unit1, unit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_learn);
        unit1 = (Button) findViewById(R.id.button3);
        unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUnit1();
            }
        });
        unit2 = (Button) findViewById(R.id.button4);
    }
    public void goHome() {

        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
    }
    public void goToUnit1() {

        Intent a = new Intent(this, kinematics.class);
        startActivity(a);
    }
}
