package com.samsung.astudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loading Image
        //startActivity(new Intent(this, Intro.class));
        setContentView(R.layout.activity_main);

        LinearLayout fisrtLayout = (LinearLayout) findViewById(R.id.first_container);
        LinearLayout secondLayout = (LinearLayout) findViewById(R.id.second_container);
        LinearLayout thirdLayout = (LinearLayout) findViewById(R.id.third_container);

        fisrtLayout.setOnClickListener(this);
        secondLayout.setOnClickListener(this);
        thirdLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_container:
                Intent phoneBook = new Intent(this, StudyPhoneBook.class);
                startActivity(phoneBook);
                break;
            case R.id.second_container:
                break;
            case R.id.third_container:
                break;
            default:
                break;
        }
    }
}
