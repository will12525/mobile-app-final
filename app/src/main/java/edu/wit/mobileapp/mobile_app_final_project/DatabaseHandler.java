package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lawrencew on 3/9/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "characterStore";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createCharacter(String pcName, int pcLevel, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
        SQLiteDatabase db = getWritableDatabase();
        String newChar = "CREAT TABLE ";

        db.execSQL(newChar);

    }
}
