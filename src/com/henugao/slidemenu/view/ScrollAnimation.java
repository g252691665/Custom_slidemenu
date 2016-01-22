package com.henugao.slidemenu.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 让指定view在制定的时间内scroll到指定的位置
 * @author henugao
 *
 */
public class ScrollAnimation extends Animation {
	
	private  View view;
	private int targetScrollX;
	private int startScrollX;
	private int totalValue;
	
	
	public ScrollAnimation(View view, int targetScrollX) {
		super();
		this.view = view;
		this.targetScrollX = targetScrollX;
		startScrollX = this.view.getScrollX();
		totalValue = this.targetScrollX - this.startScrollX;
		
		//设置动画时间
		setDuration(400);
	}



	/**
	 * 在指定时间内一直执行该方法，直到动画结束
	 * interpolatedTime 0-1标示动画执行的进度或者百分比
	 * time :0-0.5-1
	 * value:10-60-110
	 * 当前的value等于起始值+总的差值*interpolatedTime
	 */
	@Override
	protected void applyTransformation(float interpolatedTime,
			Transformation t) {
		// TODO Auto-generated method stub
		super.applyTransformation(interpolatedTime, t);
		int currentScrollX = (int) (startScrollX + totalValue *interpolatedTime);
		view.scrollTo(currentScrollX, 0);
		
	}
}
