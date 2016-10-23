package com.network.kdezen.testevoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abre();

    }

    public void abre(){
        Intent theVoice=new Intent(this,VoiceRecognitionDemo.class);

        startActivity(theVoice);
    }
}
