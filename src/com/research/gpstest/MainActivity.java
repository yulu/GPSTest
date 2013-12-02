package com.research.gpstest;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{
	private LocationManager mLocationManager;
	private TextView longView;
	private TextView latView;
	private TextView timeView;
	
	private long timing;
	private boolean setTime = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	
	    longView = (TextView)findViewById(R.id.longitude);
	    latView = (TextView)findViewById(R.id.latitude);
	    timeView = (TextView)findViewById(R.id.time);
	    
	    timing = System.currentTimeMillis();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		timing = System.currentTimeMillis();
		setTime = false;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {

	    float latitude = (float) (location.getLatitude());
	    float longitude = (float) (location.getLongitude());
	    
		longView.setText(String.valueOf(longitude));
		latView.setText(String.valueOf(latitude));
		
		if(!setTime){
			setTime = true;
			long new_timing = System.currentTimeMillis();
			timing = (new_timing - timing)/(long)1000.0;
			timeView.setText(String.valueOf(timing)+" s");
		}
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
