package edu.wit.mobileapp.mobile_app_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        String characterTable = "CREATE TABLE IF NOT EXISTS player_sheets (name TEXT PRIMARY KEY, class TEXT, race TEXT, alignment TEXT, exp INTEGER, strength INTEGER, dexterity INTEGER, constitution INTEGER, intelligence INTEGER, wisdom INTEGER, charisma INTEGER)";
        db.execSQL(characterTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createCharacter(String characterName, String chosenClass, String race, String alignment, int pcLevel, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",characterName);
        values.put("class", chosenClass);
        values.put("race", race);
        values.put("alignment", alignment);
        values.put("exp", pcLevel);
        values.put("strength", strength);
        values.put("dexterity", dexterity);
        values.put("constitution", constitution);
        values.put("intelligence", intelligence);
        values.put("wisdom", wisdom);
        values.put("charisma", charisma);


        long rowId = db.insert("player_sheets", null, values);
        Log.v("New Character added", characterName+", "+rowId);
    }

    public List<CharacterItem> getAllCharacters(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM player_sheets",null);

        List<CharacterItem> list = new ArrayList<>();
     //   Cursor cursor = db.query("player_sheets", null, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do{
                String name = cursor.getString(0);
                String data = "";
                for(int x = 1; x < cursor.getColumnCount();x++){
                    data = data + cursor.getString(x) + ",";
                }
                list.add(new CharacterItem(name, data));
            }while(cursor.moveToNext());
        }



      //  Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    public void deleteChar(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("player_sheets", "name=?" , new String[]{name});
    }



}
