package edu.wit.mobileapp.mobile_app_final_project;

/**
 * Created by Dylan on 3/1/2018.
 */

public class spellItem {
    String spellName;
    int spellLevel;
    String spellDescription;
    int spDie=0;
    int spNumDie=0;
    String spDmgType="";
    //Sptype 0=not to combat, 1=to combat
    int spType=0;
    int itemID;

    public String getSpellDescription()
    {
        return spellDescription;
    }
}
