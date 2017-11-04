package com.example.messcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Histoty extends AppCompatActivity {
    public TextView messlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoty);

        messlog = (TextView) findViewById(R.id.messlog);
        String logtxt="";
        File file=getApplicationContext().getFileStreamPath("Log.txt");
        if(file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("Log.txt")));
                while ((logtxt=br.readLine())!=null){
                    messlog.setText( messlog.getText()+"\n"+logtxt);
                }
            }catch (IOException e){}
        }
    }
}
