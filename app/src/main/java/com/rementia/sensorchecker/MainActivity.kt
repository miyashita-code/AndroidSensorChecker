package com.rementia.sensorchecker

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.rementia.sensorchecker.R

class MainActivity : AppCompatActivity() {
    private var sensorManager: SensorManager? = null
    private var textView: TextView? = null

    // センサーの型ID一覧
    private val sensorList = intArrayOf(
        Sensor.TYPE_ACCELEROMETER,
        Sensor.TYPE_ACCELEROMETER_UNCALIBRATED,
        Sensor.TYPE_AMBIENT_TEMPERATURE,
        Sensor.TYPE_DEVICE_PRIVATE_BASE,
        Sensor.TYPE_GAME_ROTATION_VECTOR,    // 5
        Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
        Sensor.TYPE_GRAVITY,
        Sensor.TYPE_GYROSCOPE,
        Sensor.TYPE_GYROSCOPE_UNCALIBRATED,
        Sensor.TYPE_HEART_BEAT,              // 10
        Sensor.TYPE_HEART_RATE,
        Sensor.TYPE_LIGHT,
        Sensor.TYPE_LINEAR_ACCELERATION,
        Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT,
        Sensor.TYPE_MAGNETIC_FIELD,          // 15
        Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
        Sensor.TYPE_MOTION_DETECT,
        Sensor.TYPE_POSE_6DOF,
        Sensor.TYPE_PRESSURE,
        Sensor.TYPE_PROXIMITY,               // 20
        Sensor.TYPE_RELATIVE_HUMIDITY,
        Sensor.TYPE_ROTATION_VECTOR,
        Sensor.TYPE_SIGNIFICANT_MOTION,
        Sensor.TYPE_STATIONARY_DETECT,
        Sensor.TYPE_STEP_COUNTER,            // 25
        Sensor.TYPE_STEP_DETECTOR
    )

    // 上記IDに対応するセンサー名称
    private val sensorNameList = arrayOf(
        "ACCELEROMETER",
        "ACCELEROMETER_UNCALIBRATED",
        "AMBIENT_TEMPERATURE",
        "DEVICE_PRIVATE_BASE",
        "GAME_ROTATION_VECTOR",
        "GEOMAGNETIC_ROTATION_VECTOR",
        "GRAVITY",
        "GYROSCOPE",
        "GYROSCOPE_UNCALIBRATED",
        "HEART_BEAT",
        "HEART_RATE",
        "LIGHT",
        "LINEAR_ACCELERATION",
        "LOW_LATENCY_OFFBODY_DETECT",
        "MAGNETIC_FIELD",
        "MAGNETIC_FIELD_UNCALIBRATED",
        "MOTION_DETECT",
        "POSE_6DOF",
        "PRESSURE",
        "PROXIMITY",
        "RELATIVE_HUMIDITY",
        "ROTATION_VECTOR",
        "SIGNIFICANT_MOTION",
        "STATIONARY_DETECT",
        "STEP_COUNTER",
        "STEP_DETECTOR"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 上で定義したレイアウトをセット
        setContentView(R.layout.activity_main)

        // レイアウト内のテキストビューを取得
        textView = findViewById(R.id.text_view)

        // センサーマネージャの取得
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        // ここで表示モードを切り替える例
        // flg=true -> 端末で使用可能なセンサー名をすべて表示
        // flg=false -> sensorListを順にチェックし、利用可能かどうか判定
        val flg = false
        if (flg) {
            checkSensors()
        } else {
            checkSensorsEach()
        }
    }

    /**
     * すべてのセンサーを列挙し、そのnameを表示
     */
    private fun checkSensors() {
        val sensors = sensorManager?.getSensorList(Sensor.TYPE_ALL)
        val strListbuf = StringBuilder("Sensor List:\n\n")

        var count = 0
        sensors?.forEach { sensor ->
            count++
            strListbuf.append("$count: ${sensor.name}\n")
        }

        textView?.text = strListbuf.toString()
    }

    /**
     * あらかじめ定義した sensorList に従い、
     * 端末がサポートしているかどうかを○×(O/X)で表示
     */
    private fun checkSensorsEach() {
        val strbuf = StringBuilder("Sensor List:\n\n")

        for (i in sensorList.indices) {
            val sensor = sensorManager?.getDefaultSensor(sensorList[i])
            val available = if (sensor != null) "O" else "X"
            strbuf.append("${i + 1}: ${sensorNameList[i]}: $available\n")
        }

        textView?.text = strbuf.toString()
    }
}
