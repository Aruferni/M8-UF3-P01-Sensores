package com.example.albertfernie.m8_uf3_p01_sensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by albertfernie on 02/03/2017.
 */
public class Gview extends View implements SensorEventListener {

    float posX, posY, posZ, posX0, posY0, posZ0;
    Paint paint = new Paint();
    DecimalFormat df = new DecimalFormat("#.##");

    public Gview(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        // posicion del texto
        int xText=100, yText=100;
        canvas.drawColor(Color.BLACK);
        paint.setColor(Color.WHITE);
        canvas.drawText("x="+convertToFormat(posX), xText, yText, paint);
        canvas.drawText("y="+convertToFormat(posY), xText, yText+100, paint);
        canvas.drawText("z="+convertToFormat(posZ), xText, yText+200, paint);
        paint.setTextSize(100);
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        posX = se.values[0];
        posY = se.values[1];
        posZ = se.values[2];
        this.invalidate();
        mejorarDatos1(se);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private String convertToFormat(double value){
        return df.format(value);
    }

    private void mejorarDatos1(SensorEvent sen){
        float delta=0.40f;
        posX = sen.values[0];
        posY = sen.values[1];
        posZ = sen.values[2];
        if(Math.abs(posX - posX0) > delta || Math.abs(posY - posY0) > delta || Math.abs(posZ - posZ0) > delta) {
            this.invalidate();
            posX0=posX;
            posY0=posY;
            posZ0=posZ;
        }
    }
}
