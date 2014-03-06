package com.example.crewdbtest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrewItemActivity extends Activity {

	private SQLiteAdapter mySQLiteAdapter;
	EditText name;
	EditText totalTime;
	EditText crewingTime;
	EditText prohibitedFood;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crew_item);
		
		Button itemSaveBtn = (Button)findViewById(R.id.itemSaveBtn);
		Button itemCancelBtn = (Button)findViewById(R.id.itemCancelBtn);
		
		name = (EditText)findViewById(R.id.name);
		totalTime = (EditText)findViewById(R.id.totalTime);
		crewingTime = (EditText)findViewById(R.id.crewingTime);
		prohibitedFood = (EditText)findViewById(R.id.prohibitedFood);
		
		itemSaveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", name.getText().toString());
				contentValues.put("total_time", totalTime.getText().toString());		
				contentValues.put("chewing_time", crewingTime.getText().toString());	
				contentValues.put("prohibited_food", prohibitedFood.getText().toString());	
				mySQLiteAdapter = new SQLiteAdapter(getApplicationContext());
				mySQLiteAdapter.openToWrite();
				long result = mySQLiteAdapter.insert(contentValues, SQLiteAdapter.CREW_ITEM_FLAG);
				if(0 < result){
					Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getApplicationContext(), "실패했습니다.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		itemCancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent(getBaseContext(), MainActivity.class);
				resultIntent.putExtra("activityFlag", MainActivity.REQUEST_CODE_CREW_ITEM);
				resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(resultIntent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crew_item, menu);
		return true;
	}

}
