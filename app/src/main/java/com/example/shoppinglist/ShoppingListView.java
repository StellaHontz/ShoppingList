package com.example.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

public class ShoppingListView extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    List_Adapter list_adapter;
    ArrayList<Items> shopping_list;
    EditText editText;
    ImageView add_button, clear_all_button;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_view);
        editText= findViewById(R.id.editTextAddItem);
        add_button= findViewById(R.id.add);
        clear_all_button= findViewById(R.id.clear_all);
        recyclerView= findViewById(R.id.recyclerView);
        linearLayoutManager= new LinearLayoutManager(this);
        shopping_list = new ArrayList<>();
        newList();
    }

    public void addItem(View view){
        String item = editText.getText().toString();
        if(Check.checkText(item,this)){
            Items itemobj= new Items(item);
            itemobj.setChecked(false);
            shopping_list.add(itemobj);
            list_adapter.notifyItemInserted(shopping_list.size()-1);
            editText.setText("");
        }
    }
    public void clear_All_items(View view){
        if(!shopping_list.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Clear List");
            builder.setMessage("Are you sure you want to clear all list items?");

            //Positive answer
            builder.setPositiveButton("YES", (dialog, which) -> {
                shopping_list.clear();
                list_adapter.notifyDataSetChanged();
                newList();
            });

            //Negative answer
            builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

            //show dialog
            builder.show();
        }
    }
    public void newList(){
        list_adapter = new List_Adapter(shopping_list,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(list_adapter);
    }

}