package com.gainwise.interfacevideo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button1;
    EditText et;
    Button buttoncrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("videoKey", "onCreate");

        tv = findViewById(R.id.tv);
        button1 = findViewById(R.id.button1);
        buttoncrash = findViewById(R.id.button2);


        updateTextViewFromPrefs();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnotherValueForTV();
            }
        });

        buttoncrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String word = et.getText().toString();
            }
        });




    }

    private void updateTextViewFromPrefs() {
        SharedPreferences prefs = getSharedPreferences("VIDEO", Context.MODE_PRIVATE);
        String value = prefs.getString("tvValue", "initial Value, hello again world!");
        tv.setText(value);
    }

    private void initAnotherValueForTV() {
        SharedPreferences.Editor editor = getSharedPreferences("VIDEO", Context.MODE_PRIVATE).edit();
        editor.putString("tvValue", "YET ANOTHER VALUE FROM THE PREFS");
        editor.apply();

        recreate();

    }





    @Override
    protected void onResume() {
        super.onResume();
        Log.i("videoKey", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("videoKey", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("videoKey", "onDestroy");
    }


    CrashHandler newHandler = new CrashHandler(new HandleOnExecute());

    class HandleOnExecute implements CrashHandler.Crashable{
        @Override
        public void executeOnCrash() {
            SharedPreferences.Editor editor = getSharedPreferences("VIDEO", Context.MODE_PRIVATE).edit();
            editor.putString("tvValue", "A CRASH HAPPENED ON NO CRUEL WORLD");
            editor.apply();
        }
    }





}
