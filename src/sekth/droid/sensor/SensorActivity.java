package sekth.droid.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensorActivity extends Activity implements SensorEventListener {

	private SensorManager mSensor;
	private TextView tvSensorValue, tvVendorValue, tvVersionValue,
			tvPowerValue, tvValueX, tvValueY, tvValueZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);

		tvSensorValue = (TextView) findViewById(R.id.sensorNameValue);
		tvVendorValue = (TextView) findViewById(R.id.sensorVendorValue);
		tvVersionValue = (TextView) findViewById(R.id.sensorVersionValue);
		tvPowerValue = (TextView) findViewById(R.id.sensorPowerValue);

		tvValueX = (TextView) findViewById(R.id.valueX);
		tvValueY = (TextView) findViewById(R.id.valueY);
		tvValueZ = (TextView) findViewById(R.id.valueZ);

		mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor.registerListener(this,
				mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);

		mostrarInformacion();

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			refreshValues(event);
		}
	}

	private void mostrarInformacion() {
		// List<Sensor> sensorList =
		// mSensor.getSensorList(Sensor.TYPE_ACCELEROMETER);
		Sensor sensor = mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		tvSensorValue.setText(sensor.getName());
		tvVendorValue.setText(sensor.getVendor());
		tvVersionValue.setText(String.valueOf(sensor.getVersion()));
		tvPowerValue.setText(String.valueOf(sensor.getPower()));
	}

	private void refreshValues(SensorEvent event) {
		float values[] = event.values;

		float x = values[0];
		float y = values[1];
		float z = values[2];

		tvValueX.setText(String.valueOf(x));
		Log.i("refreshValues", "Valor del Eje X: " + String.valueOf(x));
		tvValueY.setText(String.valueOf(y));
		Log.i("refreshValues", "Valor del Eje Y: " + String.valueOf(y));
		tvValueZ.setText(String.valueOf(z));
		Log.i("refreshValues", "Valor del Eje Z: " + String.valueOf(z));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensor.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensor.registerListener(this,
				mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

}
