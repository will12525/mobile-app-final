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

    private void intitalize()
    {
        Items = new ArrayList<>();

        Items.add(new Item("Xtreme Teen Bible","1d8", "holy", R.drawable.ic_dual_blades));

        Items.add(new Item("Umbra Staff","1d12", "LUP", R.drawable.ic_dual_blades));

        Items.add(new Item("Railsplitter","1d20", "Trees", R.drawable.ic_dual_blades));

        Items.add(new Item("Flaming Raging Poisoning Sword of Doom","2d12", "Taako", R.drawable.ic_dual_blades));
    }


}


