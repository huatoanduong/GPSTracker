package toan.gpstracker;

import java.util.ArrayList;
import java.util.List;

import test.gpstracker.Util;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HistoryDAO {

	public static String TABLE_NAME = "HISTORY";

	public static String HISTORYID_CLM_NAME = "HistoryID";
	public static String LOCATIONID_CLM_NAME = "LocationID";
	public static String CHECKINTIME_CLM_NAME = "CheckinTIme";

	public static String HISTORYID_CLM_TYPE = "INTEGER";
	public static String LOCATIONID_CLM_TYPE = "INTEGER";
	public static String CHECKINTIME_CLM_TYPE = "DATETIME";

	public static String INSERT_SQL;
	public static String SELECT_ALL_SQL;

	public SQLiteDatabase sqliteDatabase;

	public HistoryDAO() {

		initSQLQuery();
		sqliteDatabase = null;
	}

	public HistoryDAO(SQLiteDatabase db) {

		initSQLQuery();
		this.sqliteDatabase = db;
	}

	/**
	 * Init các câu query
	 */
	public void initSQLQuery() {
		StringBuilder s;

		s = new StringBuilder();
		s.append(" INSERT INTO ");
		s.append(HistoryDAO.TABLE_NAME);
		s.append(" ( ");
		s.append(" " + HistoryDAO.LOCATIONID_CLM_NAME);
		s.append(" ," + HistoryDAO.CHECKINTIME_CLM_NAME);
		s.append(" ) VALUES (");
		s.append(" ?");
		s.append(" ,?");
		s.append(" ); ");
		INSERT_SQL = s.toString();

		s = new StringBuilder();
		s.append(" SELECT H.* FROM ");
		s.append(" " + HistoryDAO.TABLE_NAME + " H ");
		s.append(" ," + LocationDAO.TABLE_NAME + " L ");
		s.append(" WHERE H.LocationID = L.ID; ");
		SELECT_ALL_SQL = s.toString();
	}

	public List<HistoryItem> getAllHistory() {
		List<HistoryItem> list = new ArrayList<HistoryItem>();

		Cursor cursor = this.sqliteDatabase.rawQuery(SELECT_ALL_SQL, null);
		
		cursor.moveToFirst();
		

	}

	/**
	 * Đọc dữ liệu từ Cursor
	 * 
	 * @param cursor
	 * @return
	 */
	public static HistoryItem readData(Cursor cursor) {
		HistoryItem item = new HistoryItem();
		LocationItem locItem;

		item.setHIstoryId(cursor.getInt(cursor
				.getColumnIndex(HISTORYID_CLM_NAME)));
		item.setCheckinTime(Util.string2date(cursor.getString(cursor
				.getColumnIndex(CHECKINTIME_CLM_NAME))));

		// Đọc dữ liệu từ Cursor
		locItem = LocationDAO.readData(cursor);

		item.setLocation(locItem);

		return item;
	}

	/**
	 * Tạo câu Query CREATE TABLE
	 * 
	 * @return
	 */
	public static String getCreateTableSQL() {

		StringBuilder s = new StringBuilder();
		s.append("	CREATE TABLE HISTORY (");
		s.append("	HistoryID   INTEGER  PRIMARY KEY AUTOINCREMENT ");
		s.append("						 NOT NULL,");
		s.append("	LocationID  INTEGER  NOT NULL ");
		s.append("			REFERENCES LOCATION ( ID ) ON DELETE CASCADE,");
		s.append("	CheckinTime DATETIME NOT NULL");
		s.append(" );");

		return s.toString();
	}
}
