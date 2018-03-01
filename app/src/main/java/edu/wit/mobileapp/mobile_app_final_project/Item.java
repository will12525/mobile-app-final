package edu.wit.mobileapp.mobile_app_final_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camasok on 2/16/2018.
 */

public class Item {

    String name;
    String damageDice;
    String property;
    boolean magical;

   int photoId;

    Item(String name, String dice,String property, boolean magical)
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


