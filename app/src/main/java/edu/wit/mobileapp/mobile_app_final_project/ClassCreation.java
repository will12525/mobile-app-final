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

public class ClassCreation extends AppCompatActivity {

    String TAG = "myApp";
    String classChosen = "Class";
    String raceChosen = "Race";
    String alignmentChosen = "Alignment";
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_creation);

        new DrawerFunctions(this);

        Resources res = getResources();

        int randomInt1 = rollThreeDSix();
        int randomInt2 = rollThreeDSix();
        int randomInt3 = rollThreeDSix();
        int randomInt4 = rollThreeDSix();
        int randomInt5 = rollThreeDSix();
        int randomInt6 = rollThreeDSix();

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
                Log.v(TAG, "" + classChosen);
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


        /**
         *
         * Past here is testing passing information from this character creation thing
         * to another activity (not sure if that's the endgame, but I wanted to make sure
         * I could pass stuff through). I commented out the testactivity part but I can push that
         * as well if y'all would like. Just lmk :)
         *
         */


        final Button confirm = findViewById(R.id.charConfirmButton);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("myApp", "character confirmed");

                Intent intent = new Intent();
                //intent.setClass(ClassCreation.this, TestActivity.class);


                EditText pcNameEdit = findViewById(R.id.pcNameEdit);
                String pcName = pcNameEdit.getText().toString();
                EditText pcLevelEdit = findViewById(R.id.pcLevelEdit);
                String pcLevelString = pcLevelEdit.getText().toString();
                int pcLevel = 0;

                Spinner spinner = findViewById(R.id.class_spinner);
                String selectedClass = spinner.getSelectedItem().toString();

                spinner = findViewById(R.id.race_spinner);
                String selectedRace = spinner.getSelectedItem().toString();

                spinner = findViewById(R.id.alignment_spinner);
                String selectedAlignment = spinner.getSelectedItem().toString();



                if (pcName.equals("") || pcLevelString.equals("") || classChosen.equals("Class")) {
                    Snackbar.make(v, "Please input/choose values for each field", Snackbar.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        pcLevel = Integer.parseInt(pcLevelString);
                    } catch (NumberFormatException e) {
                        Snackbar.make(v, "Please input/choose values for each field", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

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


                bundle.putString("name", pcName);
                bundle.putInt("level", pcLevel);

                bundle.putInt("str", strength);
                bundle.putInt("dex", dexterity);
                bundle.putInt("con", constitution);
                bundle.putInt("int", intelligence);
                bundle.putInt("wis", wisdom);
                bundle.putInt("cha", charisma);


                intent.putExtras(bundle);
                //startActivity(intent);

                DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
                handler.createCharacter(pcName,pcLevel,strength,dexterity,constitution,intelligence,wisdom,charisma);


            }

        });


    }


    private int rollThreeDSix() {
        return ((int) Math.ceil(Math.random() * 6)) + ((int) Math.ceil(Math.random() * 6)) + ((int) Math.ceil(Math.random() * 6));
    }
}
