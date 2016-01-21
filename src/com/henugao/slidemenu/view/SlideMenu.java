package com.henugao.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SlideMenu extends ViewGroup {
	
	private View menu_view; //�˵�
	private View main_view; //��ҳ��
	
	private int screenHeight; //
	private int screenWidth;

	/**
	 * �ڲ����ļ���ʹ�øÿؼ���ʱ�����
	 * @param context
	 * @param attrs
	 */
	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * �ڴ�����ʹ��SlideMenu��ʱ�����
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
		 * l:��ǰ��view������ڸ�view�е�x����
		 * t:��ǰ��view�Ķ����ڸ�view�е�y����
		 */
		menu_view.layout(-menu_view.getWidth(), 0, 0, menu_view.getHeight());
		main_view.layout(0, 0, main_view.getWidth(), main_view.getHeight());
	}
	
	/**
	 * ����view��ʾ����Ļ�ϵĿ��
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//����������view�Ŀ��
		//ͨ��getLayoutParams()�����ܻ�ò����ļ���ָ���ؼ��Ŀ��
		menu_view.measure(menu_view.getLayoutParams().width, menu_view.getLayoutParams().height);
		main_view.measure(main_view.getLayoutParams().width, main_view.getLayoutParams().height);
	}
	
	/**
	 * �����е���view������ϣ��ͻ���ø÷���������������ʼ����view������
	 * ע�������޷���ȡ��view�Ŀ��
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		menu_view = getChildAt(0);
		main_view = getChildAt(1);
	}

}
