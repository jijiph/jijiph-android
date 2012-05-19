package com.jiji.base;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class JijiActivity extends MapActivity implements LocationListener{
	
	// default coordinates, harrison mansion	
	private int latitude = 14546821; 
	
	private int longitude = 120993837;
	
	// display current coordinates
	private EditText locEditText = null;
	
	private MapController mapController = null;
	
	private GeoPoint point = null;
	
	final static float DISTANCE = 10.0f; // 10 meters
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		// display latitude and longitude
        locEditText = (EditText) findViewById(R.id.locText);
		String currentLocation = "Lat: " + latitude + " Lng: " + longitude;
		locEditText.setText(currentLocation);
		
		// init map
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);  

        // init location
        point = new GeoPoint(latitude, longitude); 
        mapController = mapView.getController();
        mapController.setCenter(point);
        mapController.setZoom(14);

        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        
        JijiItemizedOverlay itemizedOverlay = new JijiItemizedOverlay(drawable, this);
        
        OverlayItem overlayitem = new OverlayItem(point, "Hello, World!", "I'm home!");
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
		// Change location in map if distance is greater than DISTANCE
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, DISTANCE, this);
		
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onLocationChanged(Location location) {
		
		if (location != null) {
		
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			
			String currentLocation = "Lat: " + lat + " Lng: " + lng;
			
			locEditText.setText(currentLocation);
			point = new GeoPoint((int) lat * 1000000, (int) lng * 1000000);
			mapController.animateTo(point);
		}
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}