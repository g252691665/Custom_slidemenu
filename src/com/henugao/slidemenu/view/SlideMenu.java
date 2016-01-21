package com.henugao.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SlideMenu extends ViewGroup {

	private View menu_view; // �˵�
	private View main_view; // ��ҳ��

	private int screenHeight; //
	private int screenWidth;
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
		menu_view.layout(-menu_view.getLayoutParams().width, 0, 0, b);
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
		menu_view.measure(menu_view.getLayoutParams().width,
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
			int deltaX = (int) (moveX + downX);
			System.out.println("getScroolX:"+getScrollX());
			int newScrollX = getScrollX() + deltaX;
			System.out.println("newScrool:"+newScrollX);
			//��ֹ��ҳ������
			if (newScrollX > 0) {
				newScrollX = 0;
			}
			//��ֹmenu�˵���ʾ�Ĳ��ֶ���menu�˵��Ŀ��
			if (newScrollX < -menu_view.getWidth()) {
				newScrollX = -menu_view.getWidth();
			}
			scrollTo(newScrollX, 0);
			downX = moveX;
			break;
		case MotionEvent.ACTION_UP:

			break;
		default:
			break;
		}
		return true;
	}

}
