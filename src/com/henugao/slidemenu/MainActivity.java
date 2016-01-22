package com.henugao.slidemenu;

import com.henugao.slidemenu.view.SlideMenu;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.app.Activity;

public class MainActivity extends Activity {

	private SlideMenu slideMenu;
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
		back = (ImageView) findViewById(R.id.menu_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("========>");
				slideMenu.switchMenu();
			}
		});
	}

}
