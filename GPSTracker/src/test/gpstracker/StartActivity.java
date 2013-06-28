package test.gpstracker;

import java.util.ArrayList;
import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ExpandableListView;

public class StartActivity extends Activity {

	ExpandableListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		listView = (ExpandableListView)findViewById(R.id.expandableListView1);
		
//		testexpand();
		testexpand2();
	}

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub

		try {
			DBUtil.getInstance(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		DBUtil.insertLocation(new LocationItem());
//		List<LocationItem> aaItems= DBUtil.getAllLocation();
//		Log.e("yenn", "yen" +aaItems.size());
		

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	void testexpand()
	{

		LocationItem aItem = new LocationItem(1, 12, 12, "a", true);
		SortedHistory data = new SortedHistory("aaa");
		data.addLocation(aItem);
		data.setDate("dgdfg");
		List<SortedHistory> list = new ArrayList<SortedHistory>();
		list.add(data);
		GPSExpandableAdapter adapter = new GPSExpandableAdapter(this, this, list);
		listView.setAdapter(adapter);
		
		
	}
	
	void testexpand2()
	{

		LocationItem aItem = new LocationItem(1, 12, 12, "a", true);
		SortedHistory data = new SortedHistory("aaa");
		data.addLocation(aItem);
		data.setDate("dgdfg");
		List<SortedHistory> list = new ArrayList<SortedHistory>();
		list.add(data);
		SampleExpandableListAdapter adapter = new SampleExpandableListAdapter(this, this, list);
		listView.setAdapter(adapter);
		
		
	}
}

