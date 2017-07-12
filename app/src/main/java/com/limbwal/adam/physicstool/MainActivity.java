package com.limbwal.adam.physicstool;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.limbwal.adam.physicstool.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button equation;
    Button settings;
    Button learn;
    Button help;
    Button convert;
    static int numDig = 8;
    int currentApiVersion;
    public static int unitNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        equation = (Button) findViewById(R.id.equation);
        settings = (Button) findViewById(R.id.settings);
        learn = (Button) findViewById(R.id.learn);
        help = (Button) findViewById(R.id.help);
        convert = (Button) findViewById(R.id.convertButton);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConvert();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHelp();
            }
        });
        final File file = getFileStreamPath("settings.txt");
        try {

            FileInputStream reader1 = openFileInput(file.getName());
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(reader1));
            String unitN = reader2.readLine();
            try {
                unitNumber = Integer.parseInt(unitN);
            }
            catch (NullPointerException | NumberFormatException e){
                unitNumber = 0;
            }
            unitN = reader2.readLine();
            try {
                numDig = Integer.parseInt(unitN);
            }
            catch (NullPointerException | NumberFormatException e){
                numDig = 8;
            }
        }
        catch (IOException e){}
        equation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToWorkspace();
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToLearn();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });
        currentApiVersion = Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    public void goToWorkspace() {
        Intent i = new Intent(this, workspace.class);
        startActivity(i);
    }
    public void goToLearn() {
        Intent i = new Intent(this, learn.class);
        startActivity(i);
    }
    public void goToSettings() {
        Intent i = new Intent(this, settings.class);
        startActivity(i);
    }
    public void goToHelp() {
        Intent i = new Intent(this, help.class);
        startActivity(i);
    }
    public void goToConvert() {
        Intent i = new Intent(this, conversions.class);
        startActivity(i);
    }

}

