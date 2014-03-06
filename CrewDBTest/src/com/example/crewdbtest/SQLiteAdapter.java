package com.example.crewdbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAdapter {
	
	public static final int CREW_ITEM_FLAG = 0;
	public static final int MEAL_TIME_FLAG = 1;
	public static final int CREW_PHOTO_FLAG = 2;
	public static final int CONFIG_FLAG = 3;
	
	public static final String DB_NAME = "CREW_DB";
	public static final String CREW_ITEMS_TB = "crew_items";
	public static final String MEAL_TIME_TB = "meal_time";
	public static final String CREW_PHOTO_TB = "crew_photo";
	public static final String CONFIG_TB = "config";
	public static final int MYDATABASE_VERSION = 1;
	public static final String KEY_CONTENT = "Content";

	// create table MY_DATABASE (ID integer primary key, Content text not null);
	private static final String CREW_ITEMS_TB_SCRIPT = "CREATE  TABLE " + CREW_ITEMS_TB + " ("
			 + "	'item_id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE "
			 + "	, 'name' VARCHAR NOT NULL "
			 + "	, 'total_time' INTEGER NOT NULL  DEFAULT 0"
			 + "	, 'chewing_time' INTEGER NOT NULL  DEFAULT 0"
			 + "	, 'del_yn' CHAR NOT NULL  DEFAULT 'N'"
			 + "	, 'prohibited_food' VARCHAR"
			 + "	, 'create_dt' DATETIME DEFAULT CURRENT_DATE)";
	
	private static final String MEAL_TIME_TB_SCRIPT = "CREATE  TABLE " + MEAL_TIME_TB + " ("
			 + "	'log_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE "
			 + "	, 'total_time' INTEGER NOT NULL  DEFAULT 0"
			 + "	, 'log_date' DATETIME DEFAULT CURRENT_DATE"
			 + "	, 'crew_count' INTEGER DEFAULT 0"
			 + "	, 'meno' TEXT"
			 + "	, 'photo_id' INTEGER)";
	
	private static final String CREW_PHOTO_TB_SCRIPT = "CREATE  TABLE " + CREW_PHOTO_TB + " ("
			 + "	'photo_id' INTEGER NOT NULL "
			 + "	, 'photo_sql' INTEGER NOT NULL "
			 + "	, 'image' BLOB"
			 + "	, PRIMARY KEY ('photo_id', 'photo_sql'))";
	
	private static final String CONFIG_TB_SCRIPT = "CREATE  TABLE " + CONFIG_TB + " ("
			 + "	'key' VARCHAR PRIMARY KEY  NOT NULL  UNIQUE "
			 + "	, 'value' VARCHAR)";

	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;

	public SQLiteAdapter(Context c) {
		context = c;
	}

	public SQLiteAdapter openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, DB_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;
	}

	public SQLiteAdapter openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, DB_NAME, null,
				MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		sqLiteHelper.close();
	}

	public long insert(ContentValues contentValues, int flag) {
		if(flag == CREW_ITEM_FLAG){
			return sqLiteDatabase.insert(CREW_ITEMS_TB, null, contentValues);
		}else if(flag == MEAL_TIME_FLAG){
			return sqLiteDatabase.insert(MEAL_TIME_TB, null, contentValues);
		}else if(flag == CREW_PHOTO_FLAG){
			return sqLiteDatabase.insert(CREW_PHOTO_TB, null, contentValues);
		}else{
			return sqLiteDatabase.insert(CONFIG_TB, null, contentValues);
		}
	}

	public int deleteAll() {
		return 0; //sqLiteDatabase.delete(CREW_ITEMS_TB, null, null);
	}

	public Cursor select( boolean distinct, String[] columns, String selection, String[] selectionArgs, String groupBy, String orderBy, String tableName) {
		return sqLiteDatabase.query(distinct, tableName, columns, selection, selectionArgs, groupBy, null, orderBy, null);
	}
	

	public Cursor select(String sql, String[] selectionArgs) {
		return sqLiteDatabase.rawQuery(sql, selectionArgs);
	}

	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREW_ITEMS_TB_SCRIPT);
			db.execSQL(MEAL_TIME_TB_SCRIPT);
			db.execSQL(CREW_PHOTO_TB_SCRIPT);
			db.execSQL(CONFIG_TB_SCRIPT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}
}
