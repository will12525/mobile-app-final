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
        db = getWritableDatabase();
       createPlayerTable();
       // clearDatabase();
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

    void createPlayerTable(){
        String characterTable = "CREATE TABLE IF NOT EXISTS player_sheets " +
                "(name TEXT PRIMARY KEY, selected INTEGER, class TEXT, race TEXT, " +
                "alignment TEXT, proficiencies TEXT, exp INTEGER, inventory INTEGER, " +
                "strength INTEGER, dexterity INTEGER, constitution INTEGER, " +
                "intelligence INTEGER, wisdom INTEGER, charisma INTEGER, speed INTEGER, " +
                "initiative INTEGER, maxHitPoints INTEGER, currentHitPoints INTEGER)";


        db.execSQL(characterTable);
        Log.v(getClass().toString()," character table created");
    }

    void clearDatabase(){
        //db.delete("player_sheets",null,null);
        db.execSQL("DROP TABLE IF EXISTS player_sheets");

        Log.v(getClass().toString()," character table deleted");
        createPlayerTable();
    }

    public void deleteCharacter(String name){
        if(getSelectedCharacter().name.equals(name)){
            Cursor cursor = db.rawQuery("SELECT * FROM player_sheets LIMIT 2",null);
            if (cursor.getCount()>0) {
                cursor.moveToFirst();
                do{
                    CharacterItem item = getCharacterInfo(cursor);
                    if(item!=null){
                        if(!item.name.equals(name)){
                            updateSelected(item.name);
                        }
                    }
                }while(cursor.moveToNext());
            }

            cursor.close();

        }
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
        values.put("proficiencies", proficiencies);

        values.put("exp", pcLevel);
        values.put("inventory",0);
        values.put("strength", strength);
        values.put("dexterity", dexterity);
        values.put("constitution", constitution);
        values.put("intelligence", intelligence);
        values.put("wisdom", wisdom);
        values.put("charisma", charisma);
        values.put("speed", speed);
        values.put("initiative", initiative);
        values.put("maxHitPoints", hitPoints);
        values.put("currentHitPoints", hitPoints);

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

                /*boolean selected = false;
                if(cursor.getInt(1)==1){
                    selected = true;
                }

                list.add(new CharacterItem(selected, cursor.getString(0), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getInt(6),cursor.getInt(7),cursor.getInt(8),cursor.getInt(9), cursor.getInt(10),cursor.getInt(11),cursor.getInt(12),cursor.getInt(13),cursor.getInt(14),cursor.getInt(15),cursor.getInt(16),cursor.getInt(17)));
*/
                CharacterItem item = getCharacterInfo(cursor);
                if(item!=null){
                    list.add(item);
                }
            }while(cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    public CharacterItem getSelectedCharacter(){
        CharacterItem characterItem = null;
        String query = "SELECT * FROM player_sheets WHERE selected=1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();

            characterItem = getCharacterInfo(cursor);
/*            boolean selected = false;
            if(cursor.getInt(1)==1){
                selected = true;
            }




            try {
                characterItem = new CharacterItem(selected, cursor.getString(0), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17));
            } catch (RuntimeException e){
                clearDatabase();
            }*/
        }
        cursor.close();
        return characterItem;
    }

    private CharacterItem getCharacterInfo(Cursor cursor){
        boolean selected = false;
        String name = "";
        String charClass = "";
        String race = "";
        String alignment = "";
        String proficiencies = "";
        int exp = 0;
        int inventorySlot = 0;
        int strength = 0;
        int dexterity = 0;
        int constitution = 0;
        int intelligence = 0;
        int wisdom = 0;
        int charisma = 0;
        int speed = 0;
        int initiative = 0;
        int maxHitPoints = 0;
        int currentHitPoints = 0;

        try {
            if(cursor.getInt(1)==1){
                selected = true;
            }

            name = cursor.getString(0);
            charClass = cursor.getString(2);
            race = cursor.getString(3);
            alignment = cursor.getString(4);
            proficiencies = cursor.getString(5);
            exp = cursor.getInt(6);
            inventorySlot = cursor.getInt(7);
            strength = cursor.getInt(8);
            dexterity = cursor.getInt(9);
            constitution = cursor.getInt(10);
            intelligence = cursor.getInt(11);
            wisdom = cursor.getInt(12);
            charisma = cursor.getInt(13);
            speed = cursor.getInt(14);
            initiative = cursor.getInt(15);
            maxHitPoints = cursor.getInt(16);
            currentHitPoints = cursor.getInt(17);


        }catch (RuntimeException e){
            clearDatabase();
            return null;
        }


        return new CharacterItem(selected,name,charClass,race,alignment,proficiencies,exp,inventorySlot,strength,dexterity,constitution,intelligence,wisdom,charisma,speed,initiative,maxHitPoints,currentHitPoints);
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
