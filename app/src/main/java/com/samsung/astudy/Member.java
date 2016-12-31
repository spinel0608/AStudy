package com.samsung.astudy;

/**
 * Created by Song on 2016-12-31.
 */

public class Member {
    private String name;
    private String phoneNum;

    public Member(String name, String phoneNum){
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
