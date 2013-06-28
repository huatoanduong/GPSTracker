package test.gpstracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SortedHistory {
	String date;

	
	public SortedHistory(String date) {
		this.date = date;
		listHistoryItems = new ArrayList<HistoryItem>();
		listLocationItems = new ArrayList<LocationItem>();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	List<HistoryItem> listHistoryItems;
	
	public List<HistoryItem> getListHistoryItems() {
		return listHistoryItems;
	}
	public void setListHistoryItems(List<HistoryItem> historyItems) {
		this.listHistoryItems = historyItems;
	}
	List<LocationItem> listLocationItems;
	public List<LocationItem> getListLocationItems() {
		return listLocationItems;
	}
	public void setListLocationItems(List<LocationItem> listLocationItems) {
		this.listLocationItems = listLocationItems;
	}
	
	public void addLocation(LocationItem locationItem) {
		listLocationItems.add(locationItem);
	}

}
