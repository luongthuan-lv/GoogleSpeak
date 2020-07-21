package com.luongthuan.googlespeak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edtText;
    Button btnSpeak;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText=findViewById(R.id.edtText);
        btnSpeak=findViewById(R.id.btnSpeak);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Not sp");
                    }else {
                        btnSpeak.setEnabled(true);
                    }
                }else {
                    Log.e("TTS","Khởi tạo thất bại");
                }
            }
        });

       btnSpeak.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String text=edtText.getText().toString().trim();
               textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
           }
       });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}