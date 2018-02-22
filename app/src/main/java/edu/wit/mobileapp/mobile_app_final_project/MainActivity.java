package edu.wit.mobileapp.mobile_app_final_project;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.getInteger;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combat_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.attackList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        List<Item> itemListing = new ArrayList<Item>();

        itemListing.add(new Item("Xtreme Teen Bible","1d8", "holy", true));
        itemListing.add(new Item("Umbra Staff","1d12", "LUP", true));
        itemListing.add(new Item("Railsplitter","1d20", "Trees", false));
        itemListing.add(new Item("Flaming Raging Poisoning Sword of Doom","2d12", "Taako", false));

        itemListing.add(new Item("Magic Missle","3d4","Abraka fuck you!",true));


        ItemAdapter adapter = new ItemAdapter(itemListing);
        rv.setAdapter(adapter);

        final EditText hpNumber = (EditText) findViewById(R.id.hpNumber);
        final ProgressBar hpBar = (ProgressBar) findViewById(R.id.hpBar);
        hpBar.setMax(100);
        hpBar.setProgress(50,true);

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

                   int hp = Integer.parseInt(s.toString());



                   if(hp >= 100)
                   {
                       hpBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
                   }
                   if( hp <= 99 && hp > 50)
                   {
                       hpBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
                   }
                   if( hp < 50 && hp >= 26)
                   {
                       hpBar.setProgressTintList(ColorStateList.valueOf(Color.argb(255,255,140,0))); // Orange
                   }
                   if(( hp <= 25))
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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
