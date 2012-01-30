package com.willy.objectwrapper;

public class ItemMenu {
	
	public String name;
	public int category_id;
	public int id; 

	
	
	public ItemMenu(int category_id, int id, String name){
		this.name = name;
		this.category_id = category_id;
		this.id = id;
	}
	

}
