package com.example.albertfernie.m8_uf3_p01_sensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Gview gView=null;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gView = new Gview(this);
        setContentView(gView);
        StartSensor();
    }

    private void StartSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume(){//cuando la aplicación está en marcha
        super.onResume();
        // enviar los cambios del sensor a la vista Gview
        sensorManager.registerListener(gView, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause(){//cuando la aplicación está en pause (tapada)
        super.onPause();
        sensorManager.unregisterListener(gView); //Dejar de enviar los cambios del sensor a gview
    }
}
