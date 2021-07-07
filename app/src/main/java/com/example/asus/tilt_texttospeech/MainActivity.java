package com.example.asus.tilt_texttospeech;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextToSpeech ts;
    SensorManager sm;
    Sensor s;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);




    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];
        int x1=(int)x;
        int y1=(int)y;
        int z1=(int)z;

        String s1 = Integer.toString(x1);
        String s2 = Integer.toString(y1);
        String s3 = Integer.toString(z1);
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);

        ts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ts.setLanguage(Locale.ENGLISH);
                ts.setSpeechRate(0.9f);

            }
        });

        if(x1!=0){
            String s="Wrong Way";
            ts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
        }
        else{
            String ss="Correct";
            ts.speak(ss,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
