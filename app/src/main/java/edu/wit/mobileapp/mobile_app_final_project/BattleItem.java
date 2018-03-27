package edu.wit.mobileapp.mobile_app_final_project;

/**
 * Created by camasok on 2/16/2018.
 */

public class BattleItem {

    String name;
    String damageDice;
    int multDice;
    int typeDice;
    String property;
    boolean magical;

   int photoId;

    BattleItem(String name, int multDice, int typeDice, String property, boolean magical)
    {
        this.multDice = multDice;
        this.typeDice = typeDice;
        this.name = name;
        damageDice = multDice + "d" + typeDice;
        this.property = property;

        if(magical)
        {
            this.photoId = R.drawable.ic_magic;
        }
        else
        {
            this.photoId = R.drawable.ic_physical;
        }

    }





}


