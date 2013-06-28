package test.gpstracker;

import java.util.Date;

public class HistoryItem {

	private int historyID;
	private int locationID;
	private Date checkinTime;

	public HistoryItem(){
		checkinTime = new Date();
	}

	public HistoryItem(int historyID, int locationID, Date checkinTime)
	{
		this.historyID = historyID;
		this.locationID = locationID;
		this.checkinTime = checkinTime;
	}
	
	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public int getHistoryID() {
		return historyID;
	}

	public void setId(int id) {
		this.historyID = id;
	}

	public Date getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

}
