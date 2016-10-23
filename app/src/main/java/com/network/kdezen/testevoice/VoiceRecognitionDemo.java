package com.network.kdezen.testevoice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdezen on 21/05/16.
 */
public class VoiceRecognitionDemo extends Activity{

    private static final int REQUEST_CODE = 1234;
    private ListView wordsList;
    private  Button speakButton;
    private String teste;

    @SuppressLint("MissingSuperCall")
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);

         speakButton = (Button) findViewById(R.id.speakButton);

        wordsList = (ListView) findViewById(R.id.list);

        PackageManager pm= getPackageManager();

        //String teste=" ";
       // teste=wordsList.getAdapter().toString();



        List<ResolveInfo> activities=pm.queryIntentActivities( new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0);
            if(activities.size()==0){

                speakButton.setEnabled(false);
                speakButton.setText("Recognizer not present");
            }

    }

    public void speakButtonClicked(View v){
        startVoiceRecognitionActivity();
    }

    private void  startVoiceRecognitionActivity(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Voice recognition Demo...");
        startActivityForResult(intent,REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            ArrayList<String>matches=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            wordsList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,matches));
        }

        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK && null != data) {

            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            teste=result.get(0);

            speakButton.setText(teste);


        }



        }


    }

