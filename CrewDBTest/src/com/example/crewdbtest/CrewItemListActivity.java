package com.example.crewdbtest;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CrewItemListActivity extends Activity {
	
	private static final String TAG = "CrewItemListActivity";
	
	private SQLiteAdapter mySQLiteAdapter;
	private SimpleCursorAdapter adapter;

	ListView crewItemList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crew_item_list);
		
		crewItemList = (ListView)findViewById(R.id.crewItemList);
		
		Button crewItemListBtn = (Button)findViewById(R.id.crewItemListBtn);
		
		crewItemListBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        /*
		         *  Open the same SQLite database
		         *  and read all it's content.
		         */
		        mySQLiteAdapter = new SQLiteAdapter(getApplicationContext());
		        mySQLiteAdapter.openToRead();

		        String[] selectColumns = {
		        	"name"
		        	, "total_time"
	        		, "chewing_time"
	        		, "prohibited_food"
		        	, "2 _id"
		        };
		        
		        Cursor cursor = mySQLiteAdapter.select(false, selectColumns, "del_yn = 'N'", null, null, "create_dt desc", SQLiteAdapter.CREW_ITEMS_TB);
		        if(cursor != null){
		        	
		        	String[] colums = {
		        		"name"
		        		, "total_time"
		        		, "chewing_time"
		        		, "prohibited_food"
		        	};
		        	
		        	int[] toRes = {
		        		R.id.listCrewItemName
		        		, R.id.listCrewItemTotal
		        		, R.id.listCrewingTime
		        		, R.id.listProhibitedFood
		        	};
		        	
		        	adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, cursor, colums, toRes, 0);
		        	
		        	crewItemList.setAdapter(adapter);
		        	
		        	crewItemList.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
							Cursor cursor = (Cursor) listView.getItemAtPosition(position);

							String countryCode = cursor.getString(cursor.getColumnIndexOrThrow("total_time"));
							Toast.makeText(getApplicationContext(),countryCode, Toast.LENGTH_SHORT).show();

						}
		        	});
		        }
		        mySQLiteAdapter.close();     
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crew_item_list, menu);
		return true;
	}
	
	public Cursor queryList(){
		return null;
	}

}
