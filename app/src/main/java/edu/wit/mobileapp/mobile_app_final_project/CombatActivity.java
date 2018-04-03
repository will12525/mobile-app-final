package edu.wit.mobileapp.mobile_app_final_project;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camasok on 3/12/2018.
 */

public class CombatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.combat_activity);

        DatabaseHandler db = new DatabaseHandler(this);
        new DrawerFunctions(this,db);

        RecyclerView rv = (RecyclerView) findViewById(R.id.attackList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        List<BattleItem> battleItemListing = new ArrayList<BattleItem>();


        final CharacterItem character = db.getSelectedCharacter();

        List<invItem> processList = db.getCharacterInventory();

        for(int i = 1; i < processList.size(); i++)
        {
            if(processList.get(i).type == 1) {
                String tempName = processList.get(i).itemName;
                int tempMulti = processList.get(i).numDie;
                int tempNum = processList.get(i).die;
                String tempDmg = processList.get(i).dmgType;
                String tempDescription = processList.get(i).description;
                battleItemListing.add(new BattleItem(tempName, tempMulti, tempNum, tempDmg, false));
            }
        }

        /* battleItemListing.add(new BattleItem("Xtreme Teen Bible",1, 12, "holy", true));
        battleItemListing.add(new BattleItem("Umbra Staff",1, 12, "LUP", true));
        battleItemListing.add(new BattleItem("Railsplitter",1, 12,"Trees", false));
        battleItemListing.add(new BattleItem("Flaming Raging Poisoning Sword of Doom",1, 12, "Taako", false));

        battleItemListing.add(new BattleItem("Magic Missle",1, 12,"Abraka fuck you!",true)); */


        BattleItemAdapter adapter = new BattleItemAdapter(battleItemListing);
        rv.setAdapter(adapter);
        if(battleItemListing.size() == 0)
        {
            // Do a thing
        }




        final EditText hpNumber = (EditText) findViewById(R.id.hpNumber);
        final ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpBar);
        hpBar.setMax( character.maxHealth);
        hpBar.setProgress(character.getCurrentHealth(),true);

        final EditText initiative = (EditText) findViewById(R.id.initiativeEdit);
        final EditText speed = (EditText) findViewById(R.id.speedEdit);
        final EditText armorClass = (EditText) findViewById(R.id.armorEdit);

       initiative.setText( character.initiative + "");
        speed.setText(character.speed + "");
        armorClass.setText(character.getArmorClass() + "");

        hpNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("") || s.toString().equals("-"))
                {
                    // hpBar.setProgress(0,true);
                    // hpNumber.setText("0");
                    // hpNumber.setSelection(1);
                }else
                {

                    hpBar.setProgress(Integer.parseInt(s.toString()),true);

                    double hp = ((Integer.parseInt(s.toString())) / character.getCurrentHealth()) * 100;



                    if(hp >= 100.0)
                    {
                        hpBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
                    }
                    if( hp <= 99.0 && hp > 50.0)
                    {
                        hpBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    if( hp < 50.0 && hp >= 26.0)
                    {
                        hpBar.setProgressTintList(ColorStateList.valueOf(Color.argb(255,255,140,0))); // Orange
                    }
                    if(( hp <= 25.0))
                    {
                        hpBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
                    }



                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //intentionally blank
            }
        });
    }

}
