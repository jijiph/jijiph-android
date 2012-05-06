package com.jiji.base;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class JijiActivity extends MapActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);  
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
        JijiItemizedOverlay itemizedoverlay = new JijiItemizedOverlay(drawable, this);
        
        GeoPoint point = new GeoPoint(14556658, 121023856);
        OverlayItem overlayitem = new OverlayItem(point, "Hello, World!", "I'm in Makati!");
        
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}