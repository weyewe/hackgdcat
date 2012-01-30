package com.cyrilmottier.android.gdcatalog.widget;

import com.cyrilmottier.android.gdcatalog.R;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonTextItemView extends LinearLayout implements ItemView{

	private TextView elementTitle;
	private Button infoButton;



	public ButtonTextItemView(Context context) {
		this(context, null);
	}

	public ButtonTextItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}



	@Override
	public void prepareItemView() {
		// TODO Auto-generated method stub
		elementTitle = (TextView) findViewById(R.id.gd_separator_text);
		infoButton = (Button) findViewById(R.id.gd_text);
		
		/* 
		 * set onClickListener. True, if we want to show a Dialog
		 * with facility to add /edit/ cancel quantity. The host for Dialog is the context (activity)
		 * It means that we should get hte context with getContext()
		 * 
		 * How should we go with that ?
		 * OK, set the click listener for later
		 */
        

	}

	@Override
	public void setObject(Item item) {
		// TODO Auto-generated method stub

	}

}
