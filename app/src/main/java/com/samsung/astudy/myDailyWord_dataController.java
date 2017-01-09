package com.samsung.astudy;

/**
 * Created by 심성보 on 2017-01-08.
 */
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class studyMydailyWordDAO {

    public static void insertSampleData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO english VALUES(null,'house','집');");
        db.execSQL("INSERT INTO english VALUES(null,'facebook','얼굴책');");
        db.execSQL("INSERT INTO english VALUES(null,'mom','엄마');");
        db.close();
    }

    public static ArrayList<studyMyDailyWordDTO> getList(SQLiteDatabase db) {

        ArrayList<studyMyDailyWordDTO> list = new ArrayList<studyMyDailyWordDTO>();
        Cursor result = db.rawQuery("SELECT * FROM english", null);
        while (result.moveToNext()) {
            studyMyDailyWordDTO dto = new studyMyDailyWordDTO();
            dto.setNum(result.getInt(0));
            dto.setWord(result.getString(1));
            dto.setMean(result.getString(2));
            list.add(dto);
        }
        result.close();
        db.close();
        return list;
    }

    public static void insert(SQLiteDatabase db, studyMyDailyWordDTO dto) {
        String word = dto.getWord();
        String mean = dto.getMean();
        String sql = "INSERT INTO english VALUES" +
                "(null,'" + word + "','" + mean + "');";
        db.execSQL(sql);
        db.close();
    }

    public static void update(SQLiteDatabase db, studyMyDailyWordDTO dto) {
        String sql = "UPDATE english SET word='" +
                dto.getWord() + "',mean='" +
                dto.getMean() + "' WHERE num=" +
                dto.getNum() + ";";

        db.execSQL(sql);
        db.close();
    }

    public static void deleteAll(SQLiteDatabase db, int nums[]) {
        for (int tmp : nums) {
            String sql = "DELETE FROM english WHERE num=" + tmp + ";";
            db.execSQL(sql);
        }
        db.close();
    }
}
