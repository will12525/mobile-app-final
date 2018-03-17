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

    SQLiteDatabase db;

    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        String characterTable = "CREATE TABLE IF NOT EXISTS player_sheets (name TEXT PRIMARY KEY, selected INTEGER, class TEXT, race TEXT, alignment TEXT, proficiencies TEXT, exp INTEGER, inventory INTEGER, strength INTEGER, dexterity INTEGER, constitution INTEGER, intelligence INTEGER, wisdom INTEGER, charisma INTEGER, speed INTEGER, initiative INTEGER, hitPoints INTEGER)";
        db = getWritableDatabase();
        db.execSQL(characterTable);
        //clearDatabase();
        //hp, initiative, ac,
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Add "selected" value, inventory table id
        //create table of characters inventory
            // name, type, damage, notes
      //  String characterTable = "CREATE TABLE IF NOT EXISTS player_sheets (name TEXT PRIMARY KEY, selected INTEGER, class TEXT, race TEXT, alignment TEXT, proficiencies TEXT, exp INTEGER, inventory INTEGER, strength INTEGER, dexterity INTEGER, constitution INTEGER, intelligence INTEGER, wisdom INTEGER, charisma INTEGER, speed INTEGER, initiative INTEGER, hitPoints INTEGER)";
      //  db.execSQL(characterTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void clearDatabase(){
        //db.delete("player_sheets",null,null);
        db.execSQL("DROP TABLE IF EXISTS player_sheets");
    }

    public void deleteCharacter(String name){
        db.delete("player_sheets", "name=?" , new String[]{name});
    }

    boolean createCharacter(String characterName, String chosenClass, String race, String alignment, String proficiencies, int pcLevel, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int speed, int initiative, int hitPoints){

        boolean characterExists = updateSelected(characterName);
        if(characterExists){
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("name",characterName);
        values.put("selected",1);
        values.put("class", chosenClass);
        values.put("race", race);
        values.put("alignment", alignment);
        values.put("exp", pcLevel);
        values.put("inventory",0);
        values.put("strength", strength);
        values.put("dexterity", dexterity);
        values.put("constitution", constitution);
        values.put("intelligence", intelligence);
        values.put("wisdom", wisdom);
        values.put("charisma", charisma);
        values.put("speed", speed);
        values.put("proficiencies", proficiencies);
        values.put("initiative", initiative);
        values.put("hitPoints", hitPoints);

        long rowId = db.insert("player_sheets", null, values);
        Log.v("New Character added", characterName+", "+rowId);

        return true;
    }

    List<CharacterItem> getAllCharacters(){
        Cursor cursor = db.rawQuery("SELECT * FROM player_sheets",null);

        List<CharacterItem> list = new ArrayList<>();
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do{
                String name = cursor.getString(0);
                int position = cursor.getInt(1);
                String data = position+"";
                for(int x = 2; x < cursor.getColumnCount();x++){
                    data = data + ", "+cursor.getString(x);
                }
                list.add(new CharacterItem(name, data));
            }while(cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    public CharacterItem getSelectedCharacter(){
        CharacterItem characterItem = new CharacterItem();
        String query = "SELECT * FROM player_sheets WHERE selected=1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();

            characterItem.setName(cursor.getString(0));
            characterItem.setSelected(true);
            characterItem.setCharClass(cursor.getString(2));
            characterItem.setRace(cursor.getString(3));
            characterItem.setAlignment(cursor.getString(4));
            characterItem.setExp(cursor.getInt(5));
            characterItem.setInventorySlot(cursor.getInt(6));
            characterItem.setStrength(cursor.getInt(7));
            characterItem.setDexterity(cursor.getInt(8));
            characterItem.setConstitution(cursor.getInt(9));
            characterItem.setIntelligence(cursor.getInt(10));
            characterItem.setWisdom(cursor.getInt(11));
            characterItem.setCharisma(cursor.getInt(12));
            characterItem.setSpeed(cursor.getInt(13));
            //set proficiencies?


           /* for (int x = 1; x < cursor.getColumnCount(); x++) {
                data = data + cursor.getString(x) + ",";
            }*/

           // characterItem = new CharacterItem(name, data);


        }
        cursor.close();
        return characterItem;
    }


    int getStatValue(String stat){
        String queryStat = stat;

        if(stat.equals("acrobatics")||stat.equals("sleight_of_hand")||stat.equals("stealth")){
            queryStat = "dexterity";
        } else if(stat.equals("animal_handling")||stat.equals("insight")||stat.equals("medicine")||stat.equals("perception")||stat.equals("survival")){
            queryStat = "wisdom";
        } else if(stat.equals("arcana")||stat.equals("history")||stat.equals("investigation")||stat.equals("nature")||stat.equals("religion")) {
            queryStat = "intelligence";
        } else if(stat.equals("athletics")) {
            queryStat = "strength";
        } else if(stat.equals("deception")||stat.equals("intimidation")||stat.equals("performance")||stat.equals("persuasion")){
            queryStat = "charisma";
        }

        String query = "SELECT "+queryStat+" from player_sheets WHERE selected=1";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            int value = cursor.getInt(0);

            Log.v(getClass().toString(), value + " obtained from " + queryStat);

            cursor.close();
            return value;
        }
        return 0;
    }

    public boolean updateSelected(String name){
        Boolean returnVal = false;
        List<CharacterItem> chars = getAllCharacters();
        for(CharacterItem character : chars) {
            ContentValues cv = new ContentValues();
            if(character.name.equals(name)){
                cv.put("selected",1);
                returnVal = true;
            } else {
                cv.put("selected",0);
            }
            db.update("player_sheets",cv, "name=?", new String[]{character.name});
        }
        return returnVal;
    }

}
