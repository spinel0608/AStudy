package com.samsung.astudy;

/**
 * Created by 심성보 on 2017-01-08.
 */
class studyMyDailyWordDTO {
    private int num;
    private String word;
    private String mean;
    public studyMyDailyWordDTO() {
    }
    public studyMyDailyWordDTO(int num, String word, String mean) {
        this.num = num;
        this.word = word;
        this.mean = mean;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMean() {
        return mean;
    }
    public void setMean(String mean) {
        this.mean = mean;
    }
}
