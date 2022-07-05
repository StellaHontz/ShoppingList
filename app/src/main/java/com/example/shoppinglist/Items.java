package com.example.shoppinglist;

public class Items {
    String item_name;
    boolean checked;

    public Items(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
