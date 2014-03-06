package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class InvaliDateView extends View {
	 
	    /** onDraw에서 한번만 그리기를 제어하기 위한 변수 */
	    int count = 0;
	     
	    Paint mPaint;
	     
	    public InvaliDateView(Context context) {
	        super(context);
	         
	        mPaint = new Paint();
	        // 계단 현상을 감소시킨다.
	        mPaint.setAntiAlias(true);
	        mPaint.setColor(Color.RED);
	    }
	    
	     
	    public InvaliDateView(Context context, AttributeSet attrs) {
	        super(context);
	         
	        mPaint = new Paint();
	        // 계단 현상을 감소시킨다.
	        mPaint.setAntiAlias(true);
	        mPaint.setColor(Color.RED);
	    }
	     
	    /**
	     * <pre>     * onDraw가 호출이 되면 화면은 다시 아무것도 없는 상태로 초기화가 된다.
	     * 때문에 count의 값이 홀수가 되어버리면 원을 그리지 않아 View에는
	     * 아무것도 나타나지 않게 된다.
	     * </pre>
	     */
	    @Override
	    protected void onDraw(Canvas canvas) {
	        if (count % 2 == 0) {
	            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, mPaint);
	        }
	         
	        count++;
	    }
	 
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	         
	        // 터치 up이 되었을 때 화면을 갱신한다.
	        switch (event.getAction()) {
	        case MotionEvent.ACTION_UP :
	            // invalidate()을 호출하면 화면을 갱신한다.
	            invalidate();
	            break;
	        }
	         
	        return true;
	    }
	     
	
}
