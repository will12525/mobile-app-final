package edu.wit.mobileapp.mobile_app_final_project;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lawrencew on 3/9/2018.
 */

public class CharacterItem {
    String name;

    boolean selected = false;

    String charClass;
     String race;
     String alignment;

     //String[] proficiencies;
     List<String> proficiencies = new ArrayList<>();

     int exp;
     int inventorySlot;
     int strength;
     int dexterity;
     int constitution;
     int intelligence;
     int wisdom;
     int charisma;
     int speed;
     int initiative;
     int maxHealth;
     int currentHealth;

     int proficiencyBonus;



  //  public String data;


    public CharacterItem(boolean selected, String name, String charClass, String race, String alignment, String proficiencies,
                         int exp, int inventorySlot, int strength, int dexterity, int constitution, int intelligence,
                         int wisdom, int charisma, int speed, int initiative, int health, int currentHealth){

        this.selected = selected;
        this.name = name;
        this.charClass = charClass;
        this.race=race;
        this.alignment = alignment;
        if(proficiencies != null && !proficiencies.isEmpty()) {
           // String[] tempProficiencies = proficiencies.split(",");
            this.proficiencies.addAll(Arrays.asList(proficiencies.split(",")));
        }
        this.exp = exp;
        this.inventorySlot = inventorySlot;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom=wisdom;
        this.charisma = charisma;
        this.speed = speed;
        this.initiative = initiative;
        this.maxHealth = health;
        this.currentHealth = currentHealth;

        proficiencyBonus = 2;

        //data = "Use the new character constructor";


       // this.proficiencies = new String[] {"Setup", "new", "proficiencies"};
    }

    public int getSkill(String stat){
        int currentAbilityValue = 0;

        if(stat.equals("acrobatics")||stat.equals("sleight_of_hand")||stat.equals("stealth")){
            currentAbilityValue = dexterity;
        } else if(stat.equals("animal_handling")||stat.equals("insight")||stat.equals("medicine")||stat.equals("perception")||stat.equals("survival")){
            currentAbilityValue = wisdom;
        } else if(stat.equals("arcana")||stat.equals("history")||stat.equals("investigation")||stat.equals("nature")||stat.equals("religion")) {
            currentAbilityValue = intelligence;
        } else if(stat.equals("athletics")) {
            currentAbilityValue = strength;
        } else if(stat.equals("deception")||stat.equals("intimidation")||stat.equals("performance")||stat.equals("persuasion")){
            currentAbilityValue = charisma;
        }



        double calculateAbilityScore = currentAbilityValue - 10;
        calculateAbilityScore = calculateAbilityScore/2.0;
        calculateAbilityScore = Math.floor(calculateAbilityScore);


        if(proficiencies!=null){
            if(proficiencies.contains(stat)){
                calculateAbilityScore+= proficiencyBonus;
            }
        }

        return (int)calculateAbilityScore;

    }

    public int getArmorClass() {
        int armorClassBase = 10; //+current armor + shield + other possible items

        return armorClassBase;
    }

    public String getQuickView(){

        return "EXP: "+exp +", Health: "+ currentHealth;
    }

    public int getCurrentHealth() {

        return currentHealth;
    }
    public void setCurrentHealth(int value){
        if(value>maxHealth){
            currentHealth = maxHealth;
        } else {
            currentHealth = value;
        }
    }
}
