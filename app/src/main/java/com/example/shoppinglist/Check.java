package com.example.shoppinglist;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.EditText;
import android.widget.Toast;

public class Check {
    static boolean text_ok;

    //check Text
    public static boolean checkText(String text, Context context){
        if(text.isEmpty()){
            Toast.makeText(context,"Item is Empty!",Toast.LENGTH_LONG).show();
            text_ok=false;
        }
        else if(text.startsWith(" ") || text.endsWith(" ")){
            Toast.makeText(context,"Wrong Text Format! Please remove spaces from the start/end.",Toast.LENGTH_LONG).show();
            text_ok=false;
        }
        else if(text.startsWith("\n") || text.startsWith("\t") || text.endsWith("\n") || text.endsWith("\t") ){
            Toast.makeText(context,"Wrong Text Format! Please remove empty lines/or tabs.",Toast.LENGTH_LONG).show();
            text_ok=false;
        }
        else text_ok=true;
        return text_ok;
    }

    //Strike trough text
    public static void strikeThroughText(EditText editText){
        final StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();
        Spannable spannable = (Spannable) editText.getText();
        spannable.setSpan(STRIKE_THROUGH_SPAN, 0, editText.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

}
