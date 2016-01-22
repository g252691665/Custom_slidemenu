package com.henugao.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SlideMenu extends ViewGroup {

	private View menu_view; // �˵�
	private View main_view; // ��ҳ��

	private int menuWidth; // �˵��Ŀ��
	private int downX;

	/**
	 * �ڲ����ļ���ʹ�øÿؼ���ʱ�����
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * �ڴ�����ʹ��SlideMenu��ʱ�����
	 * 
	 * @param context
	 */
	public SlideMenu(Context context) {
		super(context);
	}

	/**
	 * ������view��ΰڷ�
	 * 
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		/**
		 * l:��ǰ��view������ڸ�view�е�x���� t:��ǰ��view�Ķ����ڸ�view�е�y����
		 */
		menuWidth = menu_view.getLayoutParams().width;
		menu_view.layout(-menuWidth, 0, 0, b);
		main_view.layout(0, 0, r, b);
	}

	/**
	 * ����view��ʾ����Ļ�ϵĿ��
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ����������view�Ŀ��
		// ͨ��getLayoutParams()�����ܻ�ò����ļ���ָ���ؼ��Ŀ��
		menu_view.measure(menuWidth,
				menu_view.getLayoutParams().height);
		main_view.measure(widthMeasureSpec,
				heightMeasureSpec);
	}

	/**
	 * �����е���view������ϣ��ͻ���ø÷���������������ʼ����view������ ע�������޷���ȡ��view�Ŀ��
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		menu_view = getChildAt(0);
		main_view = getChildAt(1);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			//getScrollX:�൱����Ļ�����Ͻǵ������ƫ����
			int moveX = (int) event.getX();
			int deltaX = (int) (moveX - downX);
			System.out.println("getScroolX:"+getScrollX());
			int newScrollX = getScrollX() - deltaX;
			
			//��ֹ��ҳ������
			if (newScrollX > 0) {
				newScrollX = 0;
			}
			//��ֹmenu�˵���ʾ�Ĳ��ֶ���menu�˵��Ŀ��
			if (newScrollX < -menuWidth) {
				newScrollX = -menuWidth;
			}
			scrollTo(newScrollX, 0);
			downX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			//��view��һ��ʱ�����ƶ���ĳ��λ��
			//��һ�ַ�����ʹ���Զ��嶯��
			ScrollAnimation scrollAnimation ;
			if (getScrollX() > -menuWidth/2) {
				//�رղ˵�
				scrollAnimation = new ScrollAnimation(this, 0);
			}else{
				//�򿪲˵�
				scrollAnimation = new ScrollAnimation(this, -menuWidth);
			}
			startAnimation(scrollAnimation);
			//�ڶ��ַ�����ʹ��scroller
			break;
		default:
			break;
		}
		return true;
	}
	


}
