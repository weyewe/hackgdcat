package com.cyrilmottier.android.gdcatalog;

import java.util.ArrayList;
import java.util.Iterator;

import com.willy.objectwrapper.Category;
import com.willy.objectwrapper.ItemMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import greendroid.app.GDListActivity;
import greendroid.widget.ItemAdapter;
import greendroid.widget.item.TextItem;

public class ItemListingActivity extends GDListActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * We have to extract the category_id 
		 */
		
		//get the Bundle out of the Intent...
		
		String category_id = savedInstanceState != null ? savedInstanceState.getString("selected_category_id") : null;
		
		if( category_id == null ) {
			Bundle extras = getIntent().getExtras();
	        //check to see if "myKey" is in the bundle, if so then assign it's value
	        // to mIntentString  if not, assign "nothing passed in" to mIntentString...
	         category_id = extras != null ? extras.getString("selected_category_id") : "nothing passed in";
			
		}
        
		System.out.println( "The category id is " + category_id );
        
	
        
        

		final ItemAdapter adapter = populateItemAdapter(
				( (CatalogApplication) getApplication())
				.getItemMenuWithCategoryId( Integer.parseInt( category_id ) )
		);

		setListAdapter(adapter);


	}
	

    private ItemAdapter populateItemAdapter(ArrayList<ItemMenu> itemMenuList){
    	ItemAdapter adapter = new ItemAdapter( this );
        Iterator<ItemMenu> it=itemMenuList.iterator();

        while(it.hasNext())
        {
          ItemMenu itemMenu=(ItemMenu)it.next();
          adapter.add(createTextItem(  itemMenu.name , itemMenu));
        }
        
    	return adapter;
    }
    
    private TextItem createTextItem(String title, ItemMenu itemMenu) {
        final TextItem textItem = new TextItem( title );
        textItem.setTag(itemMenu);
        return textItem;
    }
    
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	System.out.println("In ItemListingActivity, onListItemClick");
        final TextItem textItem = (TextItem) l.getAdapter().getItem(position);
//        Intent intent = new Intent( TweakedWithButtonActivity.this , (Class<?>) textItem.getTag());
        Intent intent = new Intent( ItemListingActivity.this , ItemOrderingActivity.class );
        ItemMenu selectedItemMenu = (ItemMenu)textItem.getTag();
        intent.putExtra(ActionBarActivity.GD_ACTION_BAR_TITLE, selectedItemMenu.name);
        intent.putExtra("selected_item_menu_id", Integer.toString(  selectedItemMenu.id ) );
        startActivity(intent);
    }
    
    
    
    
    
    
}
