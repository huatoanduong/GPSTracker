package test.gpstracker;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.R.bool;
import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBUtil {
	private static DBUtil util = new DBUtil();
	private static DatabaseHelper helper = null;
	private static Context context = null;
	

	private static final String DATABASE_CREATE = "create table Locations (locationID integer primary key autoincrement, longtitude real not null, latitude real not null, locationName text,isUpdated integer default 0);" +
			"create table History (historyID integer primary key autoincrement, locationID integer not null, checlinTime text not null);";
	private static final String GET_ALL_LOCATION_SQL = "select * from Locations";
	private static final String GET_HISTORY_SQL = "select * from History";
	
	public static void initialize(final Context context) throws Exception {
		DBUtil.context = context;
		if (helper == null) {
			
			final String dbName = context.getString(R.string.db_filename);
			int version = 1;

			try {
				version = Integer.parseInt(context
						.getString(R.string.db_version));
			} catch (final Exception e) {

			}

			helper = util.new DatabaseHelper(context,dbName, version);
			try {
				helper.getWritableDatabase();
			} catch (Exception e) {
				close();
				helperDestroy();
				throw e;
			}

			if (helper.initialized == null) {
				helper.initialized = true;
			}
		}
	}
	
	
	public static void close() {
		if (helper != null) {
			try {
				helper.close();
			} catch (Exception ignore) {
			}
		}
	}
	
	public static void helperDestroy() {
		helper = null;
	}
	
	
	
	public static long insertLocation(LocationItem item) {
		if (helper != null) {
			final SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues inititalValues = new ContentValues();
			inititalValues.put("longtitude", item.getLongtitude());
			inititalValues.put("latitude", item.getLatitude());
			inititalValues.put("locationName", item.getLocationName());
			inititalValues.put("isUpdated", item.isUpdated()==true? 1:0);
			long ID =db.insert("Locations", null, inititalValues); 
			return ID;

		}
		return -1;
	}
	
	public static List<LocationItem> getAllLocation() {
		List<LocationItem> list = new ArrayList<LocationItem>();
		if (helper != null) {
			try {
				final SQLiteDatabase db = helper.getReadableDatabase();
				final Cursor cursor = db.rawQuery(GET_ALL_LOCATION_SQL, null);
				cursor.moveToFirst();
				for (int r = 0; r < cursor.getCount(); r++) {
					LocationItem item = new LocationItem(cursor.getInt(0),cursor.getDouble(1),cursor.getDouble(2), cursor.getString(3),cursor.getInt(4)==1?true:false);
					list.add(item);
					cursor.moveToNext();
				}
				cursor.close();
			} catch (final Exception e) {
				Log.e(DBUtil.class.getName(), "## " + e.getMessage(), e);
			}
		}
		return list;
	}
	
	public static boolean insertHistory(HistoryItem item) {
		if (helper != null) {
			final SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues inititalValues = new ContentValues();
			inititalValues.put("historyID", item.getHistoryID());
			inititalValues.put("locationID", item.getLocationID());
			inititalValues.put("checkinTime",Util.date2string(item.getCheckinTime()));

			db.insert("History", null, inititalValues);
			return true;
		}
		return false;
	}
	
	public static List<HistoryItem> getHistory() {
		List<HistoryItem> list = new ArrayList<HistoryItem>();
		if (helper != null) {
			try {
				final SQLiteDatabase db = helper.getReadableDatabase();
				final Cursor cursor = db.rawQuery(GET_HISTORY_SQL, null);
				cursor.moveToFirst();
				for (int r = 0; r < cursor.getCount(); r++) {
					HistoryItem item = new HistoryItem(cursor.getInt(0),cursor.getInt(1),Util.string2date(cursor.getString(2)));
					list.add(item);
					cursor.moveToNext();
				}
				cursor.close();
			} catch (final Exception e) {
				Log.e(DBUtil.class.getName(), "## " + e.getMessage(), e);
			}
		}
		return list;
	}
	
	public static SQLiteDatabase getInstance(final Context context)
			throws Exception {
		initialize(context);
		return helper.getWritableDatabase();
	}
	
	
	class DatabaseHelper extends SQLiteOpenHelper {
		private Context context = null;
		private Boolean initialized;

		public boolean isInitialized() {
			return ((this.initialized != null) && this.initialized);
		}

		

		public DatabaseHelper(final Context context, final String fileName,
				final int version) {
			super(context, fileName, null, version);
			this.context = context;
		}

		@Override
		public void onCreate(final SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
			
		}

		@Override
		public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
				final int newVersion) {  
			db.execSQL("DROP TABLE IF EXISTS Locations; DROP TABLE IF EXISTS H;");
			onCreate(db);
		}
		

	}


}
