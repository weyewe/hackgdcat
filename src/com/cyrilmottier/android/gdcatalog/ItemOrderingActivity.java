package com.cyrilmottier.android.gdcatalog;

import com.willy.objectwrapper.Order;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import greendroid.app.GDActivity;
import greendroid.widget.SegmentedHost;

public class ItemOrderingActivity extends GDActivity{
	private TextView selectedItemName;
	private EditText orderQuantity; 
	private Button saveButton;
	private Integer selected_item_menu_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setActionBarContentView(R.layout.item_ordering); // << we need to make our layout


		String item_menu_id = savedInstanceState != null ? savedInstanceState.getString("selected_item_menu_id") : null;

		if( item_menu_id == null ) {
			Bundle extras = getIntent().getExtras();
			//check to see if "myKey" is in the bundle, if so then assign it's value
			// to mIntentString  if not, assign "nothing passed in" to mIntentString...
			item_menu_id = extras != null ? extras.getString("selected_item_menu_id") : "nothing passed in";

		}

		System.out.println("In the ItemOrderingActivity");
		System.out.println( "The item_menu id is " + item_menu_id );
		
		
		selected_item_menu_id = Integer.parseInt( item_menu_id );
		selectedItemName = (TextView) findViewById( R.id.selected_item_name);
		orderQuantity = (EditText) findViewById( R.id.order_quantity);
		saveButton = (Button) findViewById( R.id.save_order_button);


		orderQuantity.setText( getOrderQuantityValue() , TextView.BufferType.EDITABLE);
		saveButton.setText("Save Order");

		/*
		 * set listener for saveButton
		 */

		this.saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// in the long run
				// show Dialog, saying that it has been added
				// give option to go to the item list , or edit the number 


				// for now, we just need to get the shit working
				// 1. save to the order
				// 2. show toast, that's all.. the user will need to click the back button
				// get the current quantity
				System.out.println("Yay, I am saved!! hahaha");
				Integer quantity = Integer.parseInt(       orderQuantity.getText().toString()   );
				System.out.println("The quantity is " + quantity );
				
				Order currentOrder = ( (CatalogApplication) getApplication())
							.getOrderWithItemId( selected_item_menu_id ) ; 
				
				
				System.out.println("Before createOrUpdateOrderList");
				
				( (CatalogApplication) getApplication())
					.createOrUpdateOrderList( selected_item_menu_id, quantity.intValue() );
		
		
				Toast.makeText(getApplicationContext(), "Save Successful", Toast.LENGTH_SHORT).show();
//				finish();
			}
		});

		

	}


	private String getOrderQuantityValue(){
		int total_quantity = ( (CatalogApplication) getApplication())
								.findOrderQuantityWithItemId( selected_item_menu_id  );
		
		return Integer.toString( total_quantity );
	}
	
	private void setOrderQuantityValue(){
		// get the order from Application, change it. 
	}

}
