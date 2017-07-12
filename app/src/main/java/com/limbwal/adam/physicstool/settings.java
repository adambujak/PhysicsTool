package com.limbwal.adam.physicstool;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.limbwal.adam.physicstool.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class settings  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView textView;
    Spinner units, sigDigs;
    Button saveButton;
    ImageView back;
    public static int unitNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textView = (TextView) findViewById(R.id.title);
        units = (Spinner) findViewById(R.id.untSetting);
        sigDigs = (Spinner) findViewById(R.id.sigDigs);
        back = (ImageView) findViewById(R.id.backS);
        final String[] unitItems = {"Choose Default Units", "Metric", "Imperial"};
        final String[] digItems = {"Choose Default Number of Decimals", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        saveButton = (Button) findViewById(R.id.saveButton);

        final File file = getFileStreamPath("settings.txt");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    System.out.println(unitNumber);
                    FileOutputStream writer1 = openFileOutput(file.getName(), Context.MODE_PRIVATE);
                    String a = "" + unitNumber;


                    String b = "" + MainActivity.numDig;
                    String saving = a + "\n"  +b;
                    writer1.write(saving.getBytes());
                    writer1.flush();
                    writer1.close();
                }
                catch (IOException e) {}
                try {
                    FileInputStream reader1 = openFileInput(file.getName());
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(reader1));
                    String unitN = reader2.readLine();
                    MainActivity.unitNumber = Integer.parseInt(unitN);
                    unitN = reader2.readLine();
                    MainActivity.numDig = Integer.parseInt(unitN);

                }
                catch (IOException e){}
                Toast.makeText(getApplicationContext(), "Preferences Saved!", Toast.LENGTH_SHORT).show();
            }


        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        dataConversion(unitItems, units);
        dataConversion(digItems, sigDigs);

    }
    public void dataConversion(String[] array, Spinner spinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {

                View v = super.getDropDownView(position, null, parent);
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> spinNum, View v, int position, long id) {
        switch (spinNum.getId()) {

            case R.id.untSetting:
                switch (position) {

                    case 1:
                        unitNumber = 0;
                        break;
                    case 2:
                        unitNumber = 1;
                        break;
                }
                break;
            case R.id.sigDigs:
                switch (position) {
                    case 0:
                        break;
                   default:
                       MainActivity.numDig = position;
                       System.out.println(position);

                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // doNothing
    }
    public void goHome() {

        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
    }
}

