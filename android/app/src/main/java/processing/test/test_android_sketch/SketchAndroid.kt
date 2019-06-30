package processing.test.test_android_sketch

import processing.core.*

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class SketchAndroid : PApplet() {
    private var manager: SensorManager? = null
    private lateinit var sensor: Sensor
    private lateinit var listener: AccelerometerListener
    internal var ax: Float = 0.toFloat()
    internal var ay: Float = 0.toFloat()
    internal var az: Float = 0.toFloat()

    override fun setup() {
        manager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = manager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        listener = AccelerometerListener()
        manager!!.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)

        textFont(createFont("SansSerif", 30 * displayDensity))
    }

    override fun draw() {
        background(0)
        text("X: $ax\nY: $ay\nZ: $az", 0f, 0f, width.toFloat(), height.toFloat())
    }

    internal inner class AccelerometerListener : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            ax = event.values[0]
            ay = event.values[1]
            az = event.values[2]
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    override fun onResume() {
        super.onResume()
        if (manager != null) {
            manager!!.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        if (manager != null) {
            manager!!.unregisterListener(listener)
        }
    }

    override fun settings() {
        fullScreen()
    }
}
