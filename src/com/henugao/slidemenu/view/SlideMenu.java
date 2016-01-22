package com.henugao.slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SlideMenu extends ViewGroup {

	private View menu_view; // 菜单
	private View main_view; // 主页面

	private int menuWidth; // 菜单的宽度
	private int downX;

	/**
	 * 在布局文件中使用该控件的时候调用
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 在代码中使用SlideMenu的时候调用
	 * 
	 * @param context
	 */
	public SlideMenu(Context context) {
		super(context);
	}

	/**
	 * 设置子view如何摆放
	 * 
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		/**
		 * l:当前子view的左边在父view中的x坐标 t:当前子view的顶边在父view中的y坐标
		 */
		menuWidth = menu_view.getLayoutParams().width;
		menu_view.layout(-menuWidth, 0, 0, b);
		main_view.layout(0, 0, r, b);
	}

	/**
	 * 设置view显示在屏幕上的宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 测量所有子view的宽高
		// 通过getLayoutParams()方法能获得布局文件中指定控件的宽高
		menu_view.measure(menuWidth,
				menu_view.getLayoutParams().height);
		main_view.measure(widthMeasureSpec,
				heightMeasureSpec);
	}

	/**
	 * 当所有的子view加载完毕，就会调用该方法，可以用来初始化子view的引用 注意这里无法获取子view的宽高
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
			//getScrollX:相当于屏幕的左上角的坐标的偏移量
			int moveX = (int) event.getX();
			int deltaX = (int) (moveX - downX);
			System.out.println("getScroolX:"+getScrollX());
			int newScrollX = getScrollX() - deltaX;
			
			//防止主页面向左滑
			if (newScrollX > 0) {
				newScrollX = 0;
			}
			//防止menu菜单显示的部分多余menu菜单的宽度
			if (newScrollX < -menuWidth) {
				newScrollX = -menuWidth;
			}
			scrollTo(newScrollX, 0);
			downX = moveX;
			break;
		case MotionEvent.ACTION_UP:
			//让view在一段时间内移动到某个位置
			//第一种方法：使用自定义动画
			ScrollAnimation scrollAnimation ;
			if (getScrollX() > -menuWidth/2) {
				//关闭菜单
				scrollAnimation = new ScrollAnimation(this, 0);
			}else{
				//打开菜单
				scrollAnimation = new ScrollAnimation(this, -menuWidth);
			}
			startAnimation(scrollAnimation);
			//第二种方法：使用scroller
			break;
		default:
			break;
		}
		return true;
	}
	


}
