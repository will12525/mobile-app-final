package edu.wit.mobileapp.mobile_app_final_project;

/**
 * Created by camasok on 2/16/2018.
 */

public class BattleItem {

    String name;
    String damageDice;
    String property;
    boolean magical;

   int photoId;

    BattleItem(String name, String dice, String property, boolean magical)
    {
        this.name = name;
        damageDice = dice;
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


