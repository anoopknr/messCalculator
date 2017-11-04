package com.example.messcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    public Double akx, anx, arx , ashx , asx, antx ;
    public TextView textView ;
    public CheckBox ak;
    public CheckBox an ;
    public CheckBox ar;
    public CheckBox ash;
    public CheckBox as;
    public CheckBox ant;
    public Button addMoney;
    ImageButton history;
    public EditText inputMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         akx = 0.0; anx = 0.0; arx = 0.0; ashx = 0.0; asx = 0.0; antx = 0.0;
        textView = (TextView) findViewById(R.id.textView);
        ak = (CheckBox) findViewById(R.id.ak);
        an = (CheckBox) findViewById(R.id.an);
        ar = (CheckBox) findViewById(R.id.ar);
        ash = (CheckBox) findViewById(R.id.ash);
        as = (CheckBox) findViewById(R.id.as);
        ant = (CheckBox) findViewById(R.id.ant);
        addMoney = (Button) findViewById(R.id.addMoney);
        history = (ImageButton) findViewById(R.id.history);
        inputMoney = (EditText) findViewById(R.id.inputMoney);
        loadData();
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent his=new Intent(getApplicationContext(),Histoty.class);
                startActivity(his);
            }
        });

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                String logtxt="",fileline;
                Double Amount = Double.parseDouble(inputMoney.getText().toString());

                if (ak.isChecked()) {
                    count++;
                    logtxt+="Ahkil ";
                }
                if (an.isChecked()) {
                    count++;
                    logtxt+="Anoop ";
                }
                if (ar.isChecked()) {
                    count++;
                    logtxt+="Arun ";
                }
                if (ash.isChecked()) {
                    count++;
                    logtxt+="Ashish ";
                }
                if (as.isChecked()) {
                    count++;
                    logtxt+="Aswin ";
                }
                if (ant.isChecked()) {
                    count++;
                    logtxt+="Anto ";
                }
                logtxt+=" : "+Amount;
                Amount = Amount / count;

                if (ak.isChecked()) {
                    akx+=Amount;
                }
                if (an.isChecked()) {
                    anx+=Amount;
                }
                if (ar.isChecked()) {
                    arx+=Amount;
                }
                if (ash.isChecked()) {
                    ashx+=Amount;
                }
                if (as.isChecked()) {
                    asx+=Amount;
                }
                if (ant.isChecked()) {
                    antx+=Amount;
                }
                saveData();
                loadData();
                textView.setText(textView.getText()+"\nAmount Per Person :" + Amount);
                logtxt+="  / "+Amount;
                File file=getApplicationContext().getFileStreamPath("Log.txt");
                if(file.exists()) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("Log.txt")));
                        int i=20;
                        while ((fileline=br.readLine())!=null&&i>0){
                            logtxt+="\n"+fileline;
                            i--;
                        }
                    }catch (IOException e){}
                }
                try {
                    FileOutputStream logFile= openFileOutput("Log.txt",MODE_PRIVATE);
                    OutputStreamWriter outlogfile= new OutputStreamWriter(logFile);
                    outlogfile.write(logtxt);
                    outlogfile.flush();
                    outlogfile.close();
                    Toast.makeText(MainActivity.this,"Log Write Sucessfull",Toast.LENGTH_SHORT).show();
                }catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Log Write Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void  saveData()
    {
        String AmountText=akx+"\n"+anx+"\n"+arx+"\n"+ashx+"\n"+asx+"\n"+antx;
        try {
            FileOutputStream file= openFileOutput("Data.txt",MODE_PRIVATE);
            OutputStreamWriter outfile= new OutputStreamWriter(file);
            outfile.write(AmountText);
            outfile.flush();
            outfile.close();
            Toast.makeText(MainActivity.this,"File Write Sucessfull",Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            Toast.makeText(MainActivity.this,"File Write Failed",Toast.LENGTH_SHORT).show();
        }
    }
    public  void loadData()
    {
        String lineFromFile="";
        File file=getApplicationContext().getFileStreamPath("Data.txt");
        if(file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
                akx=Double.parseDouble(br.readLine());
                anx=Double.parseDouble(br.readLine());
                arx=Double.parseDouble(br.readLine());
                ashx=Double.parseDouble(br.readLine());
                asx=Double.parseDouble(br.readLine());
                antx=Double.parseDouble(br.readLine());
            }catch (IOException e){}
            String AmountText=" Akhil : "+akx+"\n Anoop : "+anx+"\n Arun : "+arx+"\n Ashish : "+ashx+"\n Aswin : "+asx+"\n Anto :"+antx;
            textView.setText(AmountText);
        }
    }
}
