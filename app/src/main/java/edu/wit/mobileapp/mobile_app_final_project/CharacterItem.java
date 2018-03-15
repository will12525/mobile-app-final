package edu.wit.mobileapp.mobile_app_final_project;

/**
 * Created by lawrencew on 3/9/2018.
 */

public class CharacterItem {
    public String name;

    public String data;

    private String charClass;
    private String race;
    private String alignment;

    private int exp;
    private int inventorySlot;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int speed;

    private int health;
    private int proficiencyBonus;

    private String[] proficiencies;

    CharacterItem(){
        name = "test name, do not upovote";
        data = "Use the new character constructor";
    }

    CharacterItem(String name, String data){
        this.name = name;
        this.data = data;
    }

    public CharacterItem(String name, String charClass, String race, String alignment, int exp, int inventorySlot,
                         int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int speed){
        this.name = name;
        this.charClass = charClass;
        this.race=race;
        this.alignment = alignment;
        this.exp = exp;
        this.inventorySlot = inventorySlot;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom=wisdom;
        this.charisma = charisma;
        this.speed = speed;
        data = "Use the new character constructor";
        this.health = 0;
        this.proficiencyBonus = 0;
        this.proficiencies = new String[] {"Setup", "new", "proficiencies"};
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getInventorySlot() {
        return inventorySlot;
    }

    public void setInventorySlot(int inventorySlot) {
        this.inventorySlot = inventorySlot;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public String[] getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(String[] proficiencies) {
        this.proficiencies = proficiencies;
    }


}
