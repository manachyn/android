package com.example.shop;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class Shop extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //System.out.println(12345);
        Log.d("myshop", String.valueOf(Build.VERSION.SDK_INT));
        Log.d("myshop", String.valueOf(Build.VERSION_CODES.HONEYCOMB));
        Log.d("myshop", "Test");
    }

//    private void setUpActionBar() {
//        // Make sure we're running on Honeycomb or higher (Android 3.0 (API level 11)) to use ActionBar APIs
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getActionBar();
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
}
