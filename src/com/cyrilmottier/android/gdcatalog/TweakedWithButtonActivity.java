package com.cyrilmottier.android.gdcatalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cyrilmottier.android.gdcatalog.widget.ButtonTextItem;
import com.cyrilmottier.android.gdcatalog.widget.HeadedTextItem;
import com.willy.objectwrapper.Category;
import com.willy.objectwrapper.ItemMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import greendroid.app.GDListActivity;
import greendroid.widget.ItemAdapter;
import greendroid.widget.item.DescriptionItem;
import greendroid.widget.item.DrawableItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;
import greendroid.widget.item.TextItem;
import greendroid.widget.item.ThumbnailItem;

public class TweakedWithButtonActivity extends GDListActivity  {
	private static final ButtonTextItem CHEESES[] = {
	new ButtonTextItem("Abbaye de Belloc"), new ButtonTextItem("Abbaye du Mont des Cats"),
    new ButtonTextItem("Abertam"), new ButtonTextItem("Abondance"), new ButtonTextItem("Ackawi"),
    new ButtonTextItem("Acorn"), new ButtonTextItem("Adelost"), new ButtonTextItem("Affidelice au Chablis"),
    new ButtonTextItem("Afuega'l Pitu"), new ButtonTextItem("Airag"), new ButtonTextItem("Airedale"),
    new ButtonTextItem("Aisy Cendre"), new ButtonTextItem("Allgauer Emmentaler") };
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ItemAdapter adapter = populateItemAdapter(
        		( (CatalogApplication) getApplication()).getCategoryMenu()
        );
        
        setListAdapter(adapter);
        
        
    }
    
    private ItemAdapter populateItemAdapter( Category[] category_list){
    	ItemAdapter adapter = new ItemAdapter( this );
    	
    	for(int i=0; i < category_list.length; i++){
    		Category category = category_list[i];
    		adapter.add(createTextItem(  category.name , category));
    	}
//        Iterator<Category> it=category_list.iterator();
//
//        while(it.hasNext())
//        {
//          Category itemMenu=(Category)it.next();
//
//        }
        
    	return adapter;
    }
    
    private TextItem createTextItem(String title, Category category) {
        final TextItem textItem = new TextItem( title );
        textItem.setTag(category);
        return textItem;
    }
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final TextItem textItem = (TextItem) l.getAdapter().getItem(position);
//        Intent intent = new Intent( TweakedWithButtonActivity.this , (Class<?>) textItem.getTag());
        Intent intent = new Intent( TweakedWithButtonActivity.this , ItemListingActivity.class );
        Category selectedCategory = (Category)textItem.getTag();
        intent.putExtra(ActionBarActivity.GD_ACTION_BAR_TITLE, selectedCategory.name);
        intent.putExtra("selected_category_id", Integer.toString(  selectedCategory.id ) );
        startActivity(intent);
    }
    
    

}
