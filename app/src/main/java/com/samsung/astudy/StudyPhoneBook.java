package com.samsung.astudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

public class StudyPhoneBook extends Activity {

    private Context mContext;
    private ListView mListView;
    private StudyPhoneBookAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_phonebook);

        mContext = getApplicationContext();
        mListView = (ListView) findViewById(R.id.phonebook_list);
        mAdapter = new StudyPhoneBookAdapter(mContext);
        mListView.setAdapter(mAdapter);

        mAdapter.addList(new PersonData(true, "studyname1", "name1", "telephone1"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));




    }
}
