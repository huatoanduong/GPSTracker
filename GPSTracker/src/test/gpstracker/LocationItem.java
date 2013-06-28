package test.gpstracker;

import android.R.bool;
import android.R.integer;
import android.R.string;

public class LocationItem {

	private int locationID;
	private double longtitude;
	private double latitude;
	private String locationName;
	private boolean isUpdated;

	public LocationItem() {

		locationID = 0;
		longtitude = 0;
		latitude = 0;
		locationName = ""; 
		isUpdated = false;
	}

	public LocationItem(int locationID, double longtitude, double latitude, String locationName, boolean isUpdated) {
		this.locationID = locationID;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.locationName = locationName;
		this.isUpdated = isUpdated;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
