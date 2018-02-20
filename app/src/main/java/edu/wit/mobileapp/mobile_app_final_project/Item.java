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

   int photoId;

    Item(String name, String dice,String property, int photoId)
    {
        this.name = name;
        damageDice = dice;
        this.property = property;
        this.photoId = photoId;
    }

    private List<Item> Items;



}


