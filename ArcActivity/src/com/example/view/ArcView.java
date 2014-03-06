package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ArcView extends View {
	
	Paint paint;
	boolean useCenter = true;
	RectF over;
	
	private float mStart;
    private float mSweep;
    
    private double SWEEP_INC = 6;
    private static final float START_INC = 0;
    
    static final String TAG = "ActView";
    
    CountDownTimer countDownTimer; 

	public ArcView(Context context) {
		super(context);
		init();
	}
	
	public ArcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
    
	private void init(){
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		this.paint.setStyle(Paint.Style.STROKE);
		this.paint.setStrokeWidth(4);
		this.paint.setColor(0xff880000);
		
		int width = this.getWidth() - 20;
		int height = this.getHeight() - 20;

		this.useCenter = true;
		
		
		
		/*Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i(TAG, ".............");
				postInvalidate();
			}
			
		}, 0, 1000);*/	

		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
	    
	    over = new RectF( 20, 20,  parentWidth - 20, parentWidth - 20);
	    
	    this.setMeasuredDimension(parentWidth, parentHeight);
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void drawArcs(Canvas canvas, RectF oval, boolean useCenter, Paint paint) {
		canvas.drawArc(oval, mStart-90, mSweep, useCenter, paint);
	}
	
	@Override 
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);

		drawArcs(canvas, over, useCenter, paint);
		
//		Log.i(TAG, "mSweep:" + mSweep);
		
		
		//invalidate();
    }
	
	public void startTimer(int totalMealTime, int crewTime, Object mainActivity){
		
		Log.i(TAG, mainActivity.getClass().getName());
		
		crewTime = crewTime * 1000;
		SWEEP_INC = 360/(crewTime/1000);
		countDownTimer = new CountDownTimer(crewTime + 1000, 1000){
			int count = 0;
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Log.i(TAG, "finish");
			}

			@Override
			public void onTick(long arg0) {
				Log.i(TAG, "count:" +(++count));
				mSweep += SWEEP_INC;
				Log.i(TAG, "mSweep:" + mSweep);
				if (mSweep > 360) {
					mSweep -= 360;
					mStart += START_INC;
					if (mStart >= 360) {
						mStart -= 360;
					}
				}
				postInvalidate();
			}
			
		};
		
		Log.i(TAG, "start..");
		countDownTimer.start();
	}
	
	public void arcDraw(float sweep){
		mSweep = sweep;
		if (mSweep > 360) {
			mSweep -= 360;
			mStart += START_INC;
			if (mStart >= 360) {
				mStart -= 360;
			}
		}
		postInvalidate();
	}
	
	public void stopTime(){
		countDownTimer.onFinish();
	}
}
