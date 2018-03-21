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

        RecyclerView rv = (RecyclerView) findViewById(R.id.attackList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        List<BattleItem> battleItemListing = new ArrayList<BattleItem>();

        battleItemListing.add(new BattleItem("Xtreme Teen Bible","1d8", "holy", true));
        battleItemListing.add(new BattleItem("Umbra Staff","1d12", "LUP", true));
        battleItemListing.add(new BattleItem("Railsplitter","1d20", "Trees", false));
        battleItemListing.add(new BattleItem("Flaming Raging Poisoning Sword of Doom","2d12", "Taako", false));

        battleItemListing.add(new BattleItem("Magic Missle","3d4","Abraka fuck you!",true));


        BattleItemAdapter adapter = new BattleItemAdapter(battleItemListing);
        rv.setAdapter(adapter);

        if(battleItemListing.size() == 0)
        {
            // Do a thing
        }

        DatabaseHandler db = new DatabaseHandler(this);
        final CharacterItem character = db.getSelectedCharacter();


        final EditText hpNumber = (EditText) findViewById(R.id.hpNumber);
        final ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpBar);
        hpBar.setMax( character.getHealth() );
        hpBar.setProgress(character.getHealth(),true);

        final EditText initiative = (EditText) findViewById(R.id.initiativeEdit);
        final EditText speed = (EditText) findViewById(R.id.speedEdit);
        final EditText armorClass = (EditText) findViewById(R.id.armorEdit);

        initiative.setText( character.getInitiative());
        speed.setText(character.getSpeed());
        armorClass.setText(character.getArmorClass());

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

                    double hp = ((Integer.parseInt(s.toString())) / character.getHealth()) * 100;



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
