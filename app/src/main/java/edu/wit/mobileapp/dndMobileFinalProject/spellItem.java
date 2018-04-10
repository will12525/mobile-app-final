package edu.wit.mobileapp.dndMobileFinalProject;

/**
 * Created by Dylan on 3/1/2018.
 */

public class spellItem {
    String spellName;
    int spellLevel=0;
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
    public String toString(){
        String out = "Spell Name: "+spellName+" spLevel: "+spellLevel;
        return out;
    }
}

