package com.example.shop;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DashboardActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dashboard_layout);

        final Context mContext = this;

//        final Button btnShopping = (Button) findViewById(R.id.dashboard_btn_shopping);
//        btnShopping.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // по нажатию на кнопку dashboard'a запускаем наши другие Activity
//                Log.d("myshop", "Click on Shopping");
//            }
//        });

        final Button btnBasket = (Button) findViewById(R.id.dashboard_btn_basket);
        btnBasket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("myshop", "Click on Basket");
            }
        });

        final Button btnCheckout = (Button) findViewById(R.id.dashboard_btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("myshop", "Click on Checkout");
            }
        });

        final Button btnDelivery = (Button) findViewById(R.id.dashboard_btn_delivery);
        btnDelivery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("myshop", "Click on Delivery");
            }
        });

    }

    public void onClickShopping (View v) {
        Log.d("myshop", "Click on Shoppingggg");
        //startActivity (new Intent(getApplicationContext(), AboutActivity.class));
    }
}