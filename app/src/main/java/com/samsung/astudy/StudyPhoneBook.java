package com.samsung.astudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class StudyPhoneBook extends Activity {

    private Context mContext;
    private ListView mListView;
    private StudyPhoneBookAdapter mAdapter;
    private LayoutInflater mInflater;
    private View mHeaderView;
    private ImageView mAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_phonebook);

        mContext = getApplicationContext();
        mListView = (ListView) findViewById(R.id.phonebook_list);
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeaderView = mInflater.inflate(R.layout.study_phonebook_header, null, false);
        mAdapter = new StudyPhoneBookAdapter(mContext);
        mListView.setAdapter(mAdapter);
        mListView.addHeaderView(mHeaderView);

        mAddBtn = (ImageView) mHeaderView.findViewById(R.id.phonebook_add_btn);

        mAdapter.addList(new PersonData(true, "studyname1", "name1", "telephone1"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));




    }
}
