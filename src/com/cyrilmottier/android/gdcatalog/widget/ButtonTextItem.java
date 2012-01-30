package com.cyrilmottier.android.gdcatalog.widget;

import greendroid.widget.item.TextItem;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.cyrilmottier.android.gdcatalog.R;

public class ButtonTextItem extends TextItem {

	private final String DEFAULT_BUTTON_TEXT = "No Text";
    public String buttonText;
    
    public ButtonTextItem(String text) {
        this(text, null);
    }
    
    public ButtonTextItem(String text, String bText) {
        super(text);
        if( bText == null ){
        	buttonText  = DEFAULT_BUTTON_TEXT;
        }else{
        	buttonText = bText;
        }
    }
    
 

    @Override
    public ItemView newView(Context context, ViewGroup parent) {
        return createCellFromXml(context, R.layout.button_text_item_view, parent);
    }
    
    
    /*
     * How can I set the onClickListener for the button? wtf jesus.. 
     */
}
