package com.limbwal.adam.physicstool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.limbwal.adam.physicstool.R;

public class kinematics extends AppCompatActivity {
    Button lesson1, lesson2, lesson3, lesson4, lesson5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_kinematics);
        lesson1 = (Button) findViewById(R.id.lesson1);
        lesson2 = (Button) findViewById(R.id.lesson2);
        lesson3 = (Button) findViewById(R.id.lesson3);
        lesson4 = (Button) findViewById(R.id.lesson4);
        lesson5 = (Button) findViewById(R.id.lesson5);
        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson1();
            }
        });
        lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson2();
            }
        });
        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson3();
            }
        });
        lesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson4();
            }
        });
        lesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson5();
            }
        });

    }
    public void goToLesson1() {
        Intent a = new Intent(this, lesson1u1.class);
        startActivity(a);
    }

    public void goToLesson2() {
        Intent a = new Intent(this, lesson1u2.class);
        startActivity(a);
    }
    public void goToLesson3() {
        Intent a = new Intent(this, lesson1u3.class);
        startActivity(a);
    }
    public void goToLesson4() {
        Intent a = new Intent(this, lesson1u4.class);
        startActivity(a);
    }
    public void goToLesson5() {
        Intent a = new Intent(this, lesson1u5.class);
        startActivity(a);
    }
}
