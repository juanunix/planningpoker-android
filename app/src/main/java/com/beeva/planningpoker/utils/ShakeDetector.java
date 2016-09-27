package com.beeva.planningpoker.utils;

/**
 * Created by david.gonzalez on 27/9/16.
 */

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {

  private static final float SHAKE_THRESHOLD_GRAVITY = 2.1F;
  private static final int SHAKE_SLOP_TIME_MS = 500;

  private OnShakeListener mListener;

  public void setOnShakeListener(OnShakeListener listener) {
    this.mListener = listener;
  }

  @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
    // Do nothing
  }

  @Override public void onSensorChanged(SensorEvent event) {

    if (mListener != null) {
      float x = event.values[0];
      float y = event.values[1];
      float z = event.values[2];

      float gX = x / SensorManager.GRAVITY_EARTH;
      float gY = y / SensorManager.GRAVITY_EARTH;
      float gZ = z / SensorManager.GRAVITY_EARTH;

      // gForce will be close to 1 when there is no movement.
      double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

      if (gForce > SHAKE_THRESHOLD_GRAVITY) {
        final long now = System.currentTimeMillis();
        if (SHAKE_SLOP_TIME_MS > now) {
          return;
        }

        mListener.onShake();
      }
    }
  }

  public interface OnShakeListener {
    void onShake();
  }
}
