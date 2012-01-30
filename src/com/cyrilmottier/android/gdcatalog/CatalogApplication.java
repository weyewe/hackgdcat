/*
 * Copyright (C) 2010 Cyril Mottier (http://www.cyrilmottier.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyrilmottier.android.gdcatalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.willy.objectwrapper.Category;
import com.willy.objectwrapper.ItemMenu;
import com.willy.objectwrapper.Order;

import android.content.Intent;
import android.net.Uri;
import greendroid.app.GDApplication;

public class CatalogApplication extends GDApplication {
	private static final Category category_list[] = {
		new Category( 1, "Appetizer"),
		new Category( 2, "Brunch"),
		new Category( 3, "Main Course"),
		new Category( 4, "Dessert"),
		new Category( 5, "Beer"),
		new Category( 6, "Mocktail"),
		new Category( 7, "Cocktail"),
		new Category( 8, "Wine")
	};
	
	private static final ItemMenu item_list[] = {
		/*  Appetizer */
		new ItemMenu( 1, 1, "Caesar Salad"),
		new ItemMenu( 1, 2, "Fruit Salad"),
		new ItemMenu( 1, 3, "Grilled Pineapple"),
		new ItemMenu( 1, 4, "Seafood Salad"),
		/*  Brunch */
		new ItemMenu( 2, 5, "Bread Butter Eggs"),
		new ItemMenu( 2, 6, "Jambon Sandwich"),
		new ItemMenu( 2, 7, "Pineapple Tart"),
		/*  Main Course */
		new ItemMenu( 3, 8, "Salmon Steak "),
		new ItemMenu( 3, 9, "Barracuda YinYang"),
		new ItemMenu( 3, 10, "Tombstone Driver"), 
		new ItemMenu( 3, 11, "Great Wagyu"),
		/*  Dessert */
		new ItemMenu( 4, 12, "Tiramisu Coffee Cream"),
		new ItemMenu( 4, 13, "Exotic Fruits"),
		new ItemMenu( 4, 14, "Lava Cake"),
		new ItemMenu( 4, 15, "Space Cake"),
		/*  Beer */
		new ItemMenu( 5, 16, "Paulaner"),
		new ItemMenu( 5, 17, "Bintang"),
		new ItemMenu( 5, 18, "Leffe"),
		new ItemMenu( 5, 19, "Hobgoblin"),
		/*  Mocktail */
		new ItemMenu( 6, 20, "Vodka Wodka"),
		new ItemMenu( 6, 21, "Mickey Mouse"),
		new ItemMenu( 6, 22, "Woody Woodpecker"),
		new ItemMenu( 6, 23, "Wombat X"),
		/*  Cocktail */
		new ItemMenu( 7, 24, "B-52"),
		new ItemMenu( 7, 25, "Big Ferrari"),
		new ItemMenu( 7, 26, "Yellow Savanah"),
		new ItemMenu( 7, 27, "Jagger X"),
		/*  Wine */
		new ItemMenu( 8, 28, "B-52"),
		new ItemMenu( 8, 29, "Big Ferrari"),
		new ItemMenu( 8, 30, "Yellow Savanah"),
		new ItemMenu( 8, 31, "Jagger X")
	};
	
//	private static ArrayList<Order> order_list = new ArrayList<Order>();
	private static Map<Integer, Order> order_hash_map = new HashMap<Integer, Order>();
	
	public ItemMenu[] getItemMenu(){
		return item_list;
	}
	
	public HashMap<Integer,Order> getOrderList(){
		return (HashMap<Integer,Order> )  order_hash_map; 
	}
	
	public Order getOrderWithItemId( Integer item_id ){

		System.out.println("In getOrderWithItemId");
		
		if( getOrderList().containsKey(item_id) ){
			System.out.println("There is the shit");
			Order currentOrder = getOrderList().get( item_id );
			return  currentOrder  ; 
		} else {
			return null;
		}
	}
	
	public int findOrderQuantityWithItemId( int id ){
		Integer item_id = new Integer( id );
		
		if( getOrderList().containsKey(item_id) ){
			Order currentOrder = getOrderList().get( item_id );
			return  currentOrder.quantity ; 
		} else {
			return 0;
		}
		
	}
	
	
	public void createOrUpdateOrderList(Integer item_id, int quantity){
		if( getOrderWithItemId(item_id) == null ){
			Order currentOrder = new Order( item_id.intValue(), quantity);
			createOrUpdateOrderList( currentOrder );
		}
	}
	
	
	public void createOrUpdateOrderList(Order order){
		if( order == null ) {
			System.out.println("The order is null");
		}else{
			System.out.println("The order is not null");
		}
		
		Integer item_id = new Integer( order.item_id );
		System.out.println("before check containsKey");
		if( getOrderList().containsKey(item_id)  ) {
			
			if( order.quantity == 0 ){
				getOrderList().remove( item_id);
			}else{
				Order currentOrder = getOrderList().get( item_id );
				currentOrder.quantity = order.quantity; 
			}
			
			
		} else {
			getOrderList().put(item_id,  order );
		} 
		
	}
	
	
	
	public ArrayList<ItemMenu> getItemMenuWithCategoryId(int category_id){
 		ArrayList<ItemMenu> list = new ArrayList<ItemMenu>(); // we have to know the length in advance
		
		for(int i = 0 ; i < item_list.length; i++){
			ItemMenu current_item_menu = item_list[i];
			
			if( current_item_menu.category_id == category_id  ){
				list.add( current_item_menu );
			}
		}
		
		return (ArrayList<ItemMenu>) list;
	}
	
	
	
	public Category[] getCategoryMenu(){
		return category_list;
	}

    @Override
    public Class<?> getHomeActivityClass() {
        return CatalogActivity.class;
    }
    
    @Override
    public Intent getMainApplicationIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_url)));
    }
    
    

}
