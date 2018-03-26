package edu.wit.mobileapp.mobile_app_final_project;

/**
 * Created by Dylan on 2/28/2018.
 */

public class invItem {
    public String itemName;
    public String itemWeight;
    public String description;

    public String value;
    //Die to be used for damage
    public int die;
    //How many of die to be used for damage
    public int numDie;
    public String dmgType;
    //AC given on armor as a number+dex
    public int ac;

    //0=anything 1=weapon 2=armor/accessory
    public int type=0;
}


