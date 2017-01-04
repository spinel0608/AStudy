package com.samsung.astudy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class StudyPhoneBookInputDialog extends Dialog {

    private Context mContext;
    private AlertDialog.Builder mBuilder;

    public StudyPhoneBookInputDialog(Context context, boolean isStudyPerson) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_phonebook_input_dialog);
    }
}
