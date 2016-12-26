package com.samsung.astudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loading Image
        startActivity(new Intent(this, Intro.class));

        setContentView(R.layout.activity_main);
    }
}
