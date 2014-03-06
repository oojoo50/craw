package com.example.crewdbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final int REQUEST_CODE_CREW_ITEM = 1001;
	public static final int REQUEST_CODE_MEAL_TIME = 1002;
	public static final int REQUEST_CODE_CREW_TIME = 1003;
	public static final int REQUEST_CODE_CONFIG_ITEM = 1004;
	
	public static final int REQUEST_CODE_CREW_LIST = 1005;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView listContent = (TextView)findViewById(R.id.listContent);
		
		Button crewItemBtn = (Button)findViewById(R.id.crewItemBtn);
		Button mealTimeBtn = (Button)findViewById(R.id.mealTimeBtn);
		Button crewTimeBtn = (Button)findViewById(R.id.crewTimeBtn);
		Button configBtn = (Button)findViewById(R.id.configBtn);
		
		Button crewItemList = (Button)findViewById(R.id.crewItemList);
		
		crewItemBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent crewItemIntent = new Intent(getBaseContext(), CrewItemActivity.class);
				startActivityForResult(crewItemIntent, REQUEST_CODE_CREW_ITEM);
			}
		});
		
		mealTimeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent crewItemIntent = new Intent(getBaseContext(), MealTimeActivity.class);
				startActivityForResult(crewItemIntent, REQUEST_CODE_MEAL_TIME);
			}
		});
		
		crewTimeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		configBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		crewItemList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent crewItemIntent = new Intent(getBaseContext(), CrewItemListActivity.class);
				startActivityForResult(crewItemIntent, REQUEST_CODE_CREW_LIST);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);		
		if(requestCode == REQUEST_CODE_CREW_ITEM){
			Toast.makeText(getBaseContext(), "씹기 아이탬등록", Toast.LENGTH_LONG).show();
		}else if(requestCode == REQUEST_CODE_MEAL_TIME){
			
		}else if(requestCode == REQUEST_CODE_CREW_TIME){
			
		}else if(requestCode == REQUEST_CODE_CONFIG_ITEM){
			
		}
	}

}
