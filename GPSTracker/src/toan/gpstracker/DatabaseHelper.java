package toan.gpstracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private Context context = null;
	private Boolean initialized;

	public boolean getIsInitialized() {
		return ((this.initialized != null) && this.initialized);
	}
	
	public void setInitialized(boolean isIntialized){
		this.initialized = isIntialized;
	}

	public DatabaseHelper(final Context context, final String fileName,
			final int version) {
		super(context, fileName, null, version);
		this.context = context;
	}

	private String generateDBCreateQuery() {
		StringBuilder s = new StringBuilder();

		s.append(LocationDAO.getCreateTableSQL());
		s.append("   ");
		s.append(HistoryDAO.getCreateTableSQL());

		return s.toString();
	}

	private String generateDBDropTableQuery() {
		StringBuilder s = new StringBuilder();

		s.append(" DROP TABLE IF EXISTS ");
		s.append(LocationDAO.TABLE_NAME);
		s.append(";  ");

		s.append(" DROP TABLE IF EXISTS ");
		s.append(HistoryDAO.TABLE_NAME);
		s.append(";  ");

		return s.toString();
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(generateDBCreateQuery());
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		db.execSQL(generateDBDropTableQuery());
		onCreate(db);
	}

}
