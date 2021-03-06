package com.samsung.astudy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class StudyPhoneBook extends Activity implements View.OnClickListener {

    private Context mContext;
    private ListView mListView;
    private StudyPhoneBookAdapter mAdapter;
    private LayoutInflater mInflater;
    private View mHeaderView;
    private View mFooterView;
    private ImageView mAddBtn;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_phonebook);

        mContext = getApplicationContext();
        mListView = (ListView) findViewById(R.id.phonebook_list);
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeaderView = mInflater.inflate(R.layout.study_phonebook_header, null, false);
        mFooterView = mInflater.inflate(R.layout.study_phonebook_footer, null, false);
        mAdapter = new StudyPhoneBookAdapter(mContext);
        mListView.setAdapter(mAdapter);
        mListView.addHeaderView(mHeaderView);
        mListView.addFooterView(mFooterView);

        RelativeLayout addPersonLayout = (RelativeLayout) mFooterView.findViewById(R.id.phonebook_add_person_btn);
        addPersonLayout.setOnClickListener(this);

        int addBtnColor = mContext.getResources().getColor(R.color.phonebook_add_icon_bgcolor, null);
        mAddBtn = (ImageView) findViewById(R.id.phonebook_add_btn);
        mAddBtn.getDrawable().setTint(addBtnColor);

        mAdapter.addList(new PersonData(true, "studyname1", "name1", "telephone1"));
        mAdapter.addList(new PersonData(true, "studyname1", "name2", "telephone2"));




    }

    @Override
    public void onClick(View v) {
        StudyPhoneBookInputDialog inputDialog = null;
        switch (v.getId()) {
            case R.id.phonebook_add_person_btn:
                // footer. add person
                inputDialog = new StudyPhoneBookInputDialog(this, true);
                break;
            case R.id.phonebook_add_btn:
                // floating. add study
                inputDialog = new StudyPhoneBookInputDialog(this, false);
                break;
            default:
                break;
        }
        if (inputDialog != null) {
            inputDialog.show();
        }
    }
}
