package com.samsung.astudy;

public class PersonData {
    private String mStudyName;
    private String mName;
    private String mTelNum;
    private boolean mIsWoman;
    private long longitude;
    private long latitude;

    public PersonData(boolean mIsWoman, String mStudyName, String mName, String mTelNum) {
        this.mStudyName = mStudyName;
        this.mName = mName;
        this.mTelNum = mTelNum;
        this.mIsWoman = mIsWoman;
    }

    public String getmStudyName() {
        return mStudyName;
    }

    public void setmStudyName(String mStudyName) {
        this.mStudyName = mStudyName;
    }

    public String getmTelNum() {
        return mTelNum;
    }

    public void setmTelNum(String mTelNum) {
        this.mTelNum = mTelNum;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean ismIsWoman() {
        return mIsWoman;
    }

    public void setmIsWoman(boolean mIsWoman) {
        this.mIsWoman = mIsWoman;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

}
