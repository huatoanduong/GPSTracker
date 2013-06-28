package toan.gpstracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

public class LocationDAO {

	public static String TABLE_NAME = "LOCATION";

	public static String ID_CLM_NAME = "ID";
	public static String LOCATIONID_CLM_NAME = "LocationID";
	public static String LONGITUDE_CLM_NAME = "Longitude";
	public static String LATITUDE_CLM_NAME = "Latitude";
	public static String LOCNAME_CLM_NAME = "LocationName";
	public static String ISUPDATED_CLM_NAME = "isUpdated";

	public static String ID_CLM_TYPE = "INTEGER";
	public static String LOCATIONID_CLM_TYPE = "TEXT";
	public static String LONGITUDE_CLM_TYPE = "REAL";
	public static String LATITUDE_CLM_TYPE = "REAL";
	public static String LOCNAME_CLM_TYPE = "TEXT";
	public static String ISUPDATED_CLM_TYPE = "BOOLEAN";

	public static String INSERT_SQL;
	public static String SELECT_BY_COORDINATES;
	public static String SELECT_ALL_SQL;

	public SQLiteDatabase sqliteDatabase;

	public LocationDAO() {

		initSQLQuery();
		this.sqliteDatabase = null;
	}

	public LocationDAO(SQLiteDatabase db) {

		initSQLQuery();
		this.sqliteDatabase = db;
	}

	/**
	 * Tạo câu Query CREATE TABLE
	 * 
	 * @return
	 */
	public static String getCreateTableSQL() {
		StringBuilder s = new StringBuilder();

		s.append(" CREATE TABLE LOCATION ( ");
		s.append(" ID           INTEGER PRIMARY KEY AUTOINCREMENT ");
		s.append(" 						NOT NULL, ");
		s.append(" LocationID   TEXT    NOT NULL, ");
		s.append(" Longitude    REAL    NOT NULL, ");
		s.append(" Latitude     REAL    NOT NULL, ");
		s.append(" LocationName TEXT    NOT NULL, ");
		s.append("                     DEFAULT ( '0' ) ");
		s.append(" isUpdated    BOOLEAN NOT NULL ");
		s.append("                     DEFAULT ( 0 ) ");
		s.append("); ");

		return s.toString();
	}

	/**
	 * Init các câu query
	 */
	public void initSQLQuery() {
		StringBuilder s;

		s = new StringBuilder();
		s.append(" INSERT INTO ");
		s.append(LocationDAO.TABLE_NAME);
		s.append(" ( ");
		s.append("  " + LocationDAO.LOCATIONID_CLM_NAME);
		s.append(" ," + LocationDAO.LONGITUDE_CLM_NAME);
		s.append(" ," + LocationDAO.LATITUDE_CLM_NAME);
		s.append(" ," + LocationDAO.LOCNAME_CLM_NAME);
		s.append(" ," + LocationDAO.ISUPDATED_CLM_NAME);
		s.append(" ) VALUES (");
		s.append("  ?");
		s.append(" ,?");
		s.append(" ,?");
		s.append(" ,?");
		s.append(" ,?");
		s.append(" ); ");
		INSERT_SQL = s.toString();

		s = new StringBuilder();
		s.append(" SELECT * FROM");
		s.append(" " + LocationDAO.TABLE_NAME);
		s.append(" WHERE ");
		s.append(" (");
		s.append(" " + LocationDAO.LONGITUDE_CLM_NAME + " = ? ");
		s.append(" AND ");
		s.append(" " + LocationDAO.LATITUDE_CLM_NAME + " = ? ");
		s.append(" );");
		SELECT_BY_COORDINATES = s.toString();

		s = new StringBuilder();
		s.append(" SELECT * FROM ");
		s.append(" " + LocationDAO.TABLE_NAME);
		s.append(";");
		SELECT_ALL_SQL = s.toString();
	}

	/**
	 * Đọc dữ liệu từ Cursor
	 * 
	 * @param cursor
	 * @return
	 */
	public static LocationItem readData(Cursor cursor) {
		LocationItem item = new LocationItem();

		item.setID(cursor.getInt(cursor.getColumnIndex(ID_CLM_NAME)));
		item.setLocationID(cursor.getString(cursor
				.getColumnIndex(LOCATIONID_CLM_NAME)));

		item.setLongitude(cursor.getDouble(cursor
				.getColumnIndex(LONGITUDE_CLM_NAME)));

		item.setLatitude(cursor.getDouble(cursor
				.getColumnIndex(LATITUDE_CLM_NAME)));

		item.setLocationName(cursor.getString(cursor
				.getColumnIndex(LOCNAME_CLM_NAME)));

		int i = cursor.getInt(cursor.getColumnIndex(ISUPDATED_CLM_NAME));
		item.setUpdated(i == 1);

		return item;
	}
}
