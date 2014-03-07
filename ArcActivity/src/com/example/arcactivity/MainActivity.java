package com.example.arcactivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.view.ArcView;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	String crewMinuteStr;
	String totalMealTimeStr;
	String extraTimeStr;
	
	ArcView arcView;
	ArcView arcView2;
	EditText crewMinute;
	EditText totalMealTime;
	TextView extraTime;
	TextView extraTotalTime;
	Button crewStartBtn;	
	Button crewStopBtn;
	
	CountDownTimer countDownTimer;
	
	boolean isStarted = false;
	
	int totalMealTimeInt = 0;
	
	int timesGone = 0;
	
	
	int crewMinitInt = 0;		
	int totalTime = 0;
	float divition = 0;
	
	int count = 0;	
	float crewSweep = 0;
	float crewSweep2 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if ( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {	
			setContentView(R.layout.activity_main_landscape);
			arcView = (ArcView)findViewById(R.id.laArcView);	
			arcView2 = (ArcView)findViewById(R.id.laArcView2);	
			crewMinute = (EditText)findViewById(R.id.laCrewMinute);
			totalMealTime = (EditText)findViewById(R.id.laTotalMealTime);
			extraTime = (TextView)findViewById(R.id.laExtraTime);
			extraTotalTime = (TextView)findViewById(R.id.laExtraTotalTime);		
			crewStartBtn = (Button)findViewById(R.id.laCrewStartBtn);		
			crewStopBtn = (Button)findViewById(R.id.laCrewStopBtn);
		}else{
			setContentView(R.layout.activity_main);
			arcView = (ArcView)findViewById(R.id.arcView);	
			arcView2 = (ArcView)findViewById(R.id.arcView2);	
			crewMinute = (EditText)findViewById(R.id.crewMinute);
			totalMealTime = (EditText)findViewById(R.id.totalMealTime);
			extraTime = (TextView)findViewById(R.id.extraTime);
			extraTotalTime = (TextView)findViewById(R.id.extraTotalTime);		
			crewStartBtn = (Button)findViewById(R.id.crewStartBtn);		
			crewStopBtn = (Button)findViewById(R.id.crewStopBtn);
		}
		
		if(isStarted){
			
			crewMinute.setText(crewMinuteStr);
			totalMealTime.setText(totalMealTimeStr);
			changeOrientation();			
		}
		
		crewStartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				crewMinuteStr = crewMinute.getText().toString();
				totalMealTimeStr = totalMealTime.getText().toString();
				extraTimeStr = extraTime.getText().toString();
				
				if(crewMinuteStr == null || "".equals(crewMinuteStr.trim())){
					Toast.makeText(getApplicationContext(), "1회 씹는 시간을 입력하세요.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if(totalMealTimeStr == null || "".equals(totalMealTimeStr.trim())){
					Toast.makeText(getApplicationContext(), "식사시간을 입력하세요.", Toast.LENGTH_LONG).show();
					return;
				}
				
				totalMealTimeInt = Integer.parseInt(totalMealTimeStr);
				
				crewMinitInt = Integer.parseInt(crewMinuteStr);		
				totalTime = totalMealTimeInt * 60;
				divition = totalTime / crewMinitInt;
				
				count = 0;	
				crewSweep = 0;
				crewSweep2 = 0;
				
				isStarted = true;				

				
				countDownTimer = new CountDownTimer((totalMealTimeInt * 60 * 1000) + 1000, 1000){
					
					@Override
					public void onFinish() {
						extraTime.setText("종료");
						extraTotalTime.setText("");
						timesGone = 0;
						arcView.arcDraw(0);
						arcView2.arcDraw(0);
						crewStartBtn.setEnabled(true);
						isStarted = false;
					}

					@Override
					public void onTick(long arg0) {
						
						changeOrientation();						

					}
				};
				
				countDownTimer.start();
				crewStartBtn.setEnabled(false);
			}
		});			

		crewStopBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isStarted = false;
				if(countDownTimer != null){
					extraTime.setText("");
					extraTotalTime.setText("");
					countDownTimer.cancel();
					arcView.arcDraw(0);
					arcView2.arcDraw(0);
					timesGone = 0;
					crewStartBtn.setEnabled(true);
				}
			}
		});
		// 현재 화면 안 꺼지게..
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	public void changeOrientation(){
		count++;
		timesGone++;
		crewSweep += (float)360/crewMinitInt;
		if(count == crewMinitInt){
			
			crewSweep2 += (float)360/divition;
			arcView2.arcDraw(crewSweep2);
			
			count = 0;
			crewSweep = 0;
		}
		
		int remainingTime = totalTime - timesGone;
		
		arcView.arcDraw(crewSweep);
		extraTime.setText(String.valueOf(crewMinitInt - count) + "초");
		//Log.i(TAG, String.format("sweep:%s, %s", crewSweep, count));
		if(remainingTime > 0){
			extraTotalTime.setText(
					(remainingTime/60 > 0 ? ((int)remainingTime/60) + "분" : "")
					+ (remainingTime%60 > 0 ? ((int)remainingTime%60) + "초" : "")
			);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
