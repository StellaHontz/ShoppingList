package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.logo);
        animation=AnimationUtils.loadAnimation(this, R.anim.fade_anim);
        imageView.setAnimation(animation);
        Intent intent=new Intent(this,ShoppingListView.class);

        //redirect with delay
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Do something after 3s = 3000ms
            startActivity(intent);
            this.finish();
        }, 2500);




    }
}