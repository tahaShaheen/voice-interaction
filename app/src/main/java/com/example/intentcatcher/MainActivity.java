package com.example.intentcatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.app.VoiceInteractor.PickOptionRequest;
import android.app.VoiceInteractor.PickOptionRequest.Option;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String TAG = "MainActivity";
    int iterator = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        Option option = new Option("chotu",0);
        option.addSynonym("hey chotu");
        option.addSynonym("hello chotu");
        option.addSynonym("ok chotu");
        option.addSynonym("okay chotu ");

        PickOptionRequest pickOptionRequest = new PickOptionRequest(
                new VoiceInteractor.Prompt("Talk to me"), new Option[] {option}, null){

            @Override
            public void onPickOptionResult(boolean finished, Option[] selections, Bundle result) {
                super.onPickOptionResult(finished, selections, result);

                if(finished && selections.length == 1){
                    iterator ++;
                    String string = "Chotu spoken x " + iterator + " times";
                    textView.setText(string);
                    Log.d(TAG, string);
                }
            }
        };

        Intent intent = getIntent();
        if(intent != null && isVoiceInteraction()) {
            textView.setText("Voice interaction intent caught");
            getVoiceInteractor().submitRequest(pickOptionRequest);}
        else
            textView.setText("no voice interaction intet received");

    }

}
