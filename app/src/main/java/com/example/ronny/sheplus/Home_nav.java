package com.example.ronny.sheplus;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by RONNY on 10/13/17.
 */

public class Home_nav extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.content_she_plus);
        Toast.makeText(this, "Your are welcome to our news feed", Toast.LENGTH_SHORT).show();
    }
}
