package edu.wit.mobileapp.dndMobileFinalProject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class DiceControl extends AppCompatActivity {

    private int selectedDice = 20;
    private int rollAmount = 1;
    private int currentAbilityValue;
    private String currentAbility = "";
    private boolean advantageChecked = false;
    private boolean disadvantageChecked = false;
    private boolean onDeathSaves = false;
    private boolean extraViewEnabled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_control);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        new DrawerFunctions(this,db);

        Intent intent = getIntent();
        final int statID = intent.getIntExtra("statID",0);
        currentAbilityValue = intent.getIntExtra("value",0);
        if(currentAbilityValue!=0){
            double calculateAbilityScore = currentAbilityValue - 10;
            calculateAbilityScore = calculateAbilityScore/2.0;
            calculateAbilityScore = Math.floor(calculateAbilityScore);
            Log.v(getClass().toString(),calculateAbilityScore +" = "+ currentAbilityValue);
            currentAbilityValue = (int)calculateAbilityScore;
        }
        currentAbility = intent.getStringExtra("statName");
        //Log.v("DiceControl","StatID = "+statID+", Value = "+statValue);

        final TextView diceResultMod = findViewById(R.id.roll_result_with_mod);
        final TextView diceResult = findViewById(R.id.roll_result);

        final RadioButton advantage = findViewById(R.id.advantage_radio);
        final RadioButton disadvantage = findViewById(R.id.disadvantage_radio);

        advantage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(advantageChecked){
                    advantage.setChecked(false);
                    advantageChecked = false;
                } else {
                   advantageChecked = true;
                   disadvantageChecked = false;
                   disadvantage.setChecked(false);
                }
            }
        });

        disadvantage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disadvantageChecked){
                    disadvantage.setChecked(false);
                    disadvantageChecked = false;
                } else {
                    disadvantageChecked = true;
                    advantageChecked = false;
                    advantage.setChecked(false);
                }
            }
        });

        if(statID == 0){
            diceResultMod.setVisibility(View.INVISIBLE);
        }


        Spinner rollCountSpinner = findViewById(R.id.roll_count);
        rollCountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rollAmount = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner diceSelectedSpinner = findViewById(R.id.chosen_dice);
        diceSelectedSpinner.setSelection(5);
        diceSelectedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        selectedDice = 4;
                        break;
                    case 1:
                        selectedDice = 6;
                        break;
                    case 2:
                        selectedDice = 8;
                        break;
                    case 3:
                        selectedDice = 10;
                        break;
                    case 4:
                        selectedDice = 12;
                        break;
                    case 5:
                        selectedDice = 20;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int rolledValue = rollDice(rollAmount,selectedDice);
        diceResult.setText("Roll: "+rolledValue);


        diceResultMod.setText(currentAbility+": "+(rolledValue+currentAbilityValue));

        Button rollButton = findViewById(R.id.re_roll);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rolledValue = rollDice(rollAmount,selectedDice);

                if(advantage.isChecked()){
                    int secondRoll = rollDice(rollAmount, selectedDice);
                    if(secondRoll >= rolledValue){
                        rolledValue = secondRoll;
                    }
                } else if (disadvantage.isChecked()){
                    int secondRoll = rollDice(rollAmount, selectedDice);
                    if(secondRoll <= rolledValue){
                        rolledValue = secondRoll;
                    }
                }


                diceResult.setText("Roll: "+rolledValue);
                System.out.println("-----PRINTING VALUE: "+currentAbilityValue);
                if(statID!=0){
                    diceResultMod.setVisibility(View.VISIBLE);
                    diceResultMod.setText(currentAbility);
                    diceResultMod.setText(currentAbility+": "+(rolledValue+currentAbilityValue));
                } else {
                    diceResultMod.setVisibility(View.INVISIBLE);
                }


            }
        });


        Button failure = findViewById(R.id.failure);
        failure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeathSaves){
                    RadioButton firstButton = findViewById(R.id.one_failure);
                    if(firstButton.isChecked()){
                        RadioButton secondButton = findViewById(R.id.two_failure);
                        if(secondButton.isChecked()){
                            ((RadioButton)findViewById(R.id.three_failure)).setChecked(true);
                        } else {
                            secondButton.setChecked(true);
                        }
                    } else {
                        firstButton.setChecked(true);
                    }
                } else {
                    onDeathSaves = true;
                    getLayoutInflater().inflate(R.layout.death_saves,(ViewGroup)findViewById(R.id.extra_dice_content));
                    extraViewEnabled = true;
                    ((RadioButton)findViewById(R.id.one_failure)).setChecked(true);
                }
            }
        });

        Button success = findViewById(R.id.success);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onDeathSaves){
                    RadioButton firstButton = findViewById(R.id.one_success);
                    if(firstButton.isChecked()){
                        RadioButton secondButton = findViewById(R.id.two_success);
                        if(secondButton.isChecked()){
                            ((RadioButton)findViewById(R.id.three_success)).setChecked(true);
                        } else {
                            secondButton.setChecked(true);
                        }
                    } else {
                        firstButton.setChecked(true);
                    }
                } else {
                    onDeathSaves = true;
                    getLayoutInflater().inflate(R.layout.death_saves,(ViewGroup)findViewById(R.id.extra_dice_content));
                    extraViewEnabled = true;
                    ((RadioButton)findViewById(R.id.one_success)).setChecked(true);
                }

            }
        });

       // ConstraintLayout extraViews = findViewById(R.id.extra_dice_content);
       // ViewGroup parent = (ViewGroup)findViewById(R.id.extra_dice_content);
      // getLayoutInflater().inflate(R.layout.dialog_signin,(ViewGroup)findViewById(R.id.extra_dice_content));


       // new FireMissilesDialogFragment().show(getFragmentManager(),"Testing");
        //new LoginDialogFragment().show(getFragmentManager(),"Testing");

    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


    private int rollDice(int count, int max){
        int returnValue = 0;
        for(int x = 0; x < count; x++){
            returnValue += new Random().nextInt(max)+1;
        }
        return returnValue;//(new Random().nextInt(max)+1)*count;
    }

    @SuppressLint("ValidFragment")
    public static class LoginDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                    // Add action buttons
                    .setPositiveButton("GO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // sign in the user ...
                            Log.v(getClass().toString(),"Go clicked");
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            LoginDialogFragment.this.getDialog().cancel();
                            Log.v(getClass().toString(),"Cancel clicked");
                        }
                    });
            return builder.create();
        }
    }


}
