package com.henugao.slidemenu.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * ��ָ��view���ƶ���ʱ����scroll��ָ����λ��
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
		
		//���ö���ʱ��
		setDuration(400);
	}



	/**
	 * ��ָ��ʱ����һֱִ�и÷�����ֱ����������
	 * interpolatedTime 0-1��ʾ����ִ�еĽ��Ȼ��߰ٷֱ�
	 * time :0-0.5-1
	 * value:10-60-110
	 * ��ǰ��value������ʼֵ+�ܵĲ�ֵ*interpolatedTime
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
