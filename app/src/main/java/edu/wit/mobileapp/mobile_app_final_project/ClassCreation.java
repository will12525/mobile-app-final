package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by usingerr on 02/28/2018
 */

public class ClassCreation extends AppCompatActivity {

    String classChosen = "Class";
    String raceChosen = "Race";
    String alignmentChosen = "Alignment";
    Bundle bundle = new Bundle();
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_creation);

        db = new DatabaseHandler(getApplicationContext());

        new DrawerFunctions(this, db);

        Resources res = getResources();

        int randomInt1 = xrollXDXx(3, 6);
        int randomInt2 = xrollXDXx(3, 6);
        int randomInt3 = xrollXDXx(3, 6);
        int randomInt4 = xrollXDXx(3, 6);
        int randomInt5 = xrollXDXx(3, 6);
        int randomInt6 = xrollXDXx(3, 6);

        TextView rollOne = findViewById(R.id.rollOneText);
        rollOne.setText("" + randomInt1);
        TextView rollTwo = findViewById(R.id.score_dexterity_text);
        rollTwo.setText("" + randomInt2);
        TextView rollThree = findViewById(R.id.score_constitution_text);
        rollThree.setText("" + randomInt3);
        TextView rollFour = findViewById(R.id.score_intelligence_text);
        rollFour.setText("" + randomInt4);
        TextView rollFive = findViewById(R.id.score_wisdom_text);
        rollFive.setText("" + randomInt5);
        TextView rollSix = findViewById(R.id.score_charisma_text);
        rollSix.setText("" + randomInt6);


        Spinner alignmentSpinner = (Spinner) findViewById(R.id.alignment_spinner);
        Spinner raceSpinner = (Spinner) findViewById(R.id.race_spinner);
        Spinner classSpinner = (Spinner) findViewById(R.id.class_spinner);

        String[] alignment_list = res.getStringArray(R.array.alignments);
        String[] race_list = res.getStringArray(R.array.races);
        String[] class_list = new String[]{
                "Class",
                "Barbarian",
                "Bard",
                "Cleric",
                "Druid",
                "Fighter",
                "Monk",
                "Paladin",
                "Ranger",
                "Rogue",
                "Sorcerer",
                "Warlock",
                "Wizard",
                "Blood Hunter"};

        final List<String> alignmentList = new ArrayList<>(Arrays.asList(alignment_list));
        final List<String> raceList = new ArrayList<>(Arrays.asList(race_list));
        final List<String> classList = new ArrayList<>(Arrays.asList(class_list));

        //initialize alignment ArrayAdapter
        final ArrayAdapter<String> alignmentSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, alignmentList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        //initialize race ArrayAdapter
        final ArrayAdapter<String> raceSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, raceList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        //initialize class ArrayAdapter
        final ArrayAdapter<String> classSpinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, classList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else
                    return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        alignmentSpinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        raceSpinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        classSpinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(alignmentSpinnerArrayAdapter);
        raceSpinner.setAdapter(raceSpinnerArrayAdapter);
        classSpinner.setAdapter(classSpinnerArrayAdapter);

        alignmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alignmentChosen = (String) parent.getItemAtPosition(position);
                bundle.putString("alignment", alignmentChosen);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //just be coo'
            }
        });

        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                raceChosen = (String) parent.getItemAtPosition(position);
                bundle.putString("race", raceChosen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //just be coo'
            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classChosen = ((Spinner) findViewById(R.id.class_spinner)).getItemAtPosition(position).toString();
                // Log.v(TAG, "" + classChosen);
                bundle.putString("class", classChosen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //just be coo'
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = new ClassButton();
        fragment.setArguments(bundle);
        transaction.replace(R.id.container, fragment);
        transaction.commit();

        final Button confirm = findViewById(R.id.charConfirmButton);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("myApp", "character confirmed");

                Intent intent = new Intent();
                //intent.setClass(ClassCreation.this, TestActivity.class);


                EditText pcNameEdit = findViewById(R.id.pcNameEdit);
                String pcName = pcNameEdit.getText().toString();
                EditText pcLevelEdit = findViewById(R.id.pcExpEdit);
                String pcLevelString = pcLevelEdit.getText().toString();
                int pcExperience = 0;

                if (pcName.equals("") || pcLevelString.equals("") || classChosen.equals("Class") || alignmentChosen.equals("Alignment") || raceChosen.equals("Race")) {
                    Snackbar.make(v, "Please input/choose values for each field", Snackbar.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        pcExperience = Integer.parseInt(pcLevelString);
                    } catch (NumberFormatException e) {
                        Snackbar.make(v, "Please input/choose values for each field", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    String strengthString = ((EditText) findViewById(R.id.str_edit)).getText().toString();
                    int strength;
                    String dexterityString = ((EditText) findViewById(R.id.dex_edit)).getText().toString();
                    int dexterity;
                    String constitutionString = ((EditText) findViewById(R.id.con_edit)).getText().toString();
                    int constitution;
                    String intelligenceString = ((EditText) findViewById(R.id.int_edit)).getText().toString();
                    int intelligence;
                    String wisdomString = ((EditText) findViewById(R.id.wis_edit)).getText().toString();
                    int wisdom;
                    String charismaString = ((EditText) findViewById(R.id.cha_edit)).getText().toString();
                    int charisma;

                    try {
                        strength = Integer.parseInt(strengthString);
                        dexterity = Integer.parseInt(dexterityString);
                        constitution = Integer.parseInt(constitutionString);
                        intelligence = Integer.parseInt(intelligenceString);
                        wisdom = Integer.parseInt(wisdomString);
                        charisma = Integer.parseInt(charismaString);
                    } catch (NumberFormatException e) {
                        Snackbar.make(v, "Please input/choose values for each field", Snackbar.LENGTH_LONG).show();
                        return;
                    }

                    int speed = 0;
                    //race ability score mods
                    switch (raceChosen) {
                        case ("Aarakocra"):
                            speed = 25;
                            dexterity = dexterity + 2;
                            wisdom++;
                            break;
                        case ("Aasimar"):
                            speed = 30;
                            charisma = charisma + 2;
                            break;
                        case ("Bugbear"):
                            speed = 30;
                            strength = strength + 2;
                            dexterity++;
                            break;
                        case ("Dragonborn"):
                            speed = 30;
                            strength = strength + 2;
                            charisma++;
                            break;
                        case ("Dwarf"):
                            speed = 25;
                            constitution = constitution + 2;
                            break;
                        case ("Elf"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            break;
                        case ("Feral Tiefling"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            intelligence++;
                            break;
                        case ("Firbolg"):
                            speed = 30;
                            wisdom = wisdom + 2;
                            strength++;
                            break;
                        case ("Genasi"):
                            speed = 30;
                            constitution = constitution + 2;
                            break;
                        case ("Gnome"):
                            speed = 25;
                            intelligence = intelligence + 2;
                            break;
                        case ("Goblin"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            constitution++;
                            break;
                        case ("Goliath"):
                            speed = 30;
                            strength = strength + 2;
                            constitution++;
                            break;
                        case ("Half-Elf"):
                            speed = 30;
                            charisma = charisma + 2;
                            intelligence++;
                            wisdom++;
                            break;
                        case ("Halfling"):
                            speed = 25;
                            dexterity = dexterity + 2;
                            break;
                        case ("Half-Orc"):
                            speed = 30;
                            strength = strength + 2;
                            constitution++;
                            break;
                        case ("Hobgoblin"):
                            speed = 30;
                            constitution = constitution + 2;
                            intelligence++;
                            break;
                        case ("Human"):
                            speed = 30;
                            strength++;
                            dexterity++;
                            constitution++;
                            intelligence++;
                            wisdom++;
                            charisma++;
                            break;
                        case ("Kenku"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            wisdom++;
                            break;
                        case ("Kobold"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            strength = strength - 2;
                            break;
                        case ("Lizardfolk"):
                            speed = 30;
                            constitution = constitution + 2;
                            wisdom++;
                            break;
                        case ("Orc"):
                            speed = 30;
                            strength = strength + 2;
                            constitution++;
                            intelligence = intelligence - 2;
                            break;
                        case ("Tabaxi"):
                            speed = 30;
                            dexterity = dexterity + 2;
                            charisma++;
                            break;
                        case ("Tiefling"):
                            speed = 30;
                            intelligence++;
                            charisma = charisma + 2;
                            break;
                        case ("Tortle"):
                            speed = 30;
                            strength = strength + 2;
                            wisdom++;
                            break;
                        case ("Triton"):
                            speed = 30;
                            strength++;
                            constitution++;
                            charisma++;
                            break;
                        case ("Yuan-ti"):
                            speed = 30;
                            charisma = charisma + 2;
                            intelligence++;
                            break;
                    }
                    int numberProf = bundle.getInt("numberProf");
                    String proficiencies = null;
                    for (int i = 1; i <= 4; i++) {
                        if (bundle.containsKey("prof1") && i == 1) {
                            proficiencies = bundle.getString("prof1") + ",";
                        } else if (bundle.containsKey("prof" + i)) {
                            proficiencies += (i < numberProf) ? bundle.getString("prof" + i) + "," : bundle.getString("prof" + i);
                        }
                    }
                    int initiative = getMod(dexterity) + 10;
                    int hitPoints = getHitPoints(classChosen, pcExperience, getMod(constitution));
                    Log.v("ClassCreation", "initiative: " + initiative + " hit points: " + hitPoints);


                    if (!db.createCharacter(pcName, classChosen, raceChosen, alignmentChosen, proficiencies, pcExperience, strength, dexterity, constitution, intelligence, wisdom, charisma, speed, initiative, hitPoints)) {
                        Snackbar.make(v, "Character " + pcName + " already exists", Snackbar.LENGTH_LONG).show();
                        Log.v("ClassCreation", "character " + pcName + " exists");
                    } else {
                        Log.v("ClassCreation", "character " + pcName + " added");
                        Log.v("ClassCreation", "proficiencies chosen: " + proficiencies);
                        finish();
                    }
                }
            }

        });
    }

    public int xrollXDXx(int numberOfDice, int dieNumber) {
        int result = 0;
        for (int i = 1; i <= numberOfDice; i++) {
            result += ((int) Math.ceil(Math.random() * dieNumber));
        }

        return result;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public int getMod(int score) {
        int mod = (int) Math.floor((score/2)) - 5;
        return mod;
    }

    public int getHitDie(String pcClass) {
        int hitDie = 0;
        switch (pcClass) {
            case ("Barbarian"):
                hitDie = 12;
                break;
            case ("Bard"):
                hitDie = 8;
                break;
            case ("Cleric"):
                hitDie = 8;
                break;
            case ("Druid"):
                hitDie = 8;
                break;
            case ("Fighter"):
                hitDie = 10;
                break;
            case ("Monk"):
                hitDie = 8;
                break;
            case ("Paladin"):
                hitDie = 10;
                break;
            case ("Ranger"):
                hitDie = 10;
                break;
            case ("Rogue"):
                hitDie = 8;
                break;
            case ("Sorcerer"):
                hitDie = 6;
                break;
            case ("Warlock"):
                hitDie = 8;
                break;
            case ("Wizard"):
                hitDie = 6;
                break;
            case ("Blood Hunter"):
                hitDie = 10;
                break;
        }
        return hitDie;
    }

    public static int getLevel (int exp) {
        int level = 1;
        if (exp >= 300 && exp < 900) level = 2;
        if (exp >= 900 && exp < 2700) level = 3;
        if (exp >= 2700 && exp < 6500) level = 4;
        if (exp >= 6500 && exp < 14000) level = 5;
        if (exp >= 14000 && exp < 23000) level = 6;
        if (exp >= 23000 && exp < 34000) level = 7;
        if (exp >= 34000 && exp < 48000) level = 8;
        if (exp >= 48000 && exp < 64000) level = 9;
        if (exp >= 64000 && exp < 85000) level = 10;
        if (exp >= 85000 && exp < 100000) level = 11;
        if (exp >= 100000 && exp < 120000) level = 12;
        if (exp >= 120000 && exp < 140000) level = 13;
        if (exp >= 140000 && exp < 165000) level = 14;
        if (exp >= 165000 && exp < 195000) level = 15;
        if (exp >= 195000 && exp < 225000) level = 16;
        if (exp >= 225000 && exp < 265000) level = 17;
        if (exp >= 265000 && exp < 305000) level = 18;
        if (exp >= 305000 && exp < 355000) level = 19;
        if (exp >= 355000) level = 20; //congrats
        return level;
    }

    public int getHitPoints(String classChosen, int experience, int conMod) {
        int hitPoints = getHitDie(classChosen) + conMod;

        for (int i = 2; i <= getLevel(experience); i++) {
            hitPoints += ((xrollXDXx(1, getHitDie(classChosen))) + conMod);
        }


        return hitPoints;
    }
}
