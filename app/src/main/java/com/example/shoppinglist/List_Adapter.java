package com.example.shoppinglist;

import android.content.ClipData;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class List_Adapter extends RecyclerView.Adapter<List_Adapter.ViewHolder> {
    ArrayList<Items> items_list;
    Context context;

    public List_Adapter(ArrayList<Items> items_list, Context context) {
        this.items_list = items_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item= items_list.get(position).getItem_name();
        holder.editText.setText(item);
        holder.edit.setTag("edit"); //to check whether it is editable or not

        //check if checked
        if(items_list.get(position).isChecked()){
            Glide.with(this.context).load(R.drawable.checked).into(holder.check);
            Check.strikeThroughText(holder.editText);
        }
        else if(!items_list.get(position).isChecked()) Glide.with(this.context).load(R.drawable.unchecked).into(holder.check);

        //edit text with buttons
        holder.edit.setOnClickListener(v->{
            if(v.getTag().equals("edit") && !items_list.get(position).isChecked()){ //if is not checked and in edit mode

                String temp_item= holder.editText.getText().toString(); //save text temporarily if not saved
                Glide.with(this.context).load(R.drawable.doneedit).into(holder.edit);

                //enable edit text and set on focus
                holder.editText.setEnabled(true);
                holder.editText.setFocusableInTouchMode(true);
                holder.editText.requestFocus();
                holder.edit.setTag("done");

                //if user doesn't save and focus elsewhere keep previous text
                holder.editText.setOnFocusChangeListener((view, isfocused) -> {
                    if(!isfocused){
                        holder.edit.setTag("edit");
                        holder.editText.setText(temp_item);
                        holder.editText.setEnabled(false);
                        Glide.with(this.context).load(R.drawable.edit).into((ImageView)v);
                    }
                });
            }
            //if in save mode and text ok for saving
            else if(v.getTag().equals("done") && Check.checkText(holder.editText.getText().toString(),context)){
                items_list.get(position).setItem_name(holder.editText.getText().toString()); //change obj's value
                v.setTag("edit");
                holder.editText.setEnabled(false);
                Glide.with(this.context).load(R.drawable.edit).into((ImageView)v);
                notifyItemChanged(position); //update ui
            }
        });

        //remove item
        holder.remove.setOnClickListener(v-> {
            Glide.with(this.context).load(R.drawable.unchecked).into(holder.check);
            holder.edit.setTag("edit");
            items_list.remove(position);
            this.notifyDataSetChanged();

        });

        //checkbox
        holder.check.setOnClickListener(v->{
            if(!items_list.get(position).isChecked()){ //check status
                Glide.with(this.context).load(R.drawable.checked).into(holder.check);
                Check.strikeThroughText(holder.editText);
                items_list.get(position).setChecked(true);
            }
            else if(items_list.get(position).isChecked()){
                Glide.with(this.context).load(R.drawable.unchecked).into(holder.check);
                holder.editText.setText(items_list.get(position).getItem_name());
                items_list.get(position).setChecked(false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView check, edit, remove;
        EditText editText;

        public ViewHolder(View itemView){
            super(itemView);
            check= (ImageView) itemView.findViewById(R.id.checkbox);
            edit= (ImageView) itemView.findViewById(R.id.edit);
            remove= (ImageView) itemView.findViewById(R.id.remove);
            editText= (EditText) itemView.findViewById(R.id.edit_Text);
        }

    }


}
