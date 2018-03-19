package edu.wit.mobileapp.mobile_app_final_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lawrencew on 3/8/2018.
 */

public class DrawerFunctions {

    private DatabaseHandler db;

    DrawerFunctions(final Activity context,DatabaseHandler db){
        //this.db = db;
        this.db = db;

        TextView titleStr = context.findViewById(R.id.txt_title);
        CharacterItem characterItem = db.getSelectedCharacter();
        if(characterItem != null) {
            titleStr.setText(characterItem.name);
        }

        final CoordinatorLayout drawerContainer = context.findViewById(R.id.drawer_stuff);

        final ConstraintLayout mainContent = context.findViewById(R.id.content_main);
        final DrawerLayout drawer = context.findViewById(R.id.drawer_layout);
       // Button menuLeft =  context.findViewById(R.id.menuLeft);
        //Button menuRight = context.findViewById(R.id.menuRight);

       // final NavigationView navigationView1 =  context.findViewById(R.id.nav_view);
        //final NavigationView navigationView2 =  context.findViewById(R.id.nav_view2);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);

        context.findViewById(R.id.menuLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawers();
                    drawerContainer.bringToFront();
                    drawer.openDrawer(GravityCompat.START);

                } else {
                    drawer.closeDrawers();
                    mainContent.bringToFront();
                }
            }
        });

        context.findViewById(R.id.menuRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!drawer.isDrawerOpen(GravityCompat.END)){
                    drawer.closeDrawers();
                    drawerContainer.bringToFront();
                    drawer.openDrawer(GravityCompat.END);

                } else {
                    drawer.closeDrawers();
                    mainContent.bringToFront();
                }
            }
        });

        ((NavigationView)context.findViewById(R.id.nav_view)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onDrawerSelection(item,drawer, context);
                return false;
            }
        });

        ((NavigationView)context.findViewById(R.id.nav_view2)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onDrawerSelection(item,drawer, context);
                return false;
            }
        });

        FloatingActionButton fab = context.findViewById(R.id.fab);
        if(fab!=null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, DiceControl.class));
                  //  db.clearDatabase();
                }
            });
        }
    }

    private void onDrawerSelection(MenuItem item, DrawerLayout drawer, Activity context){
        int id = item.getItemId();


        if(id == R.id.character_creation){
            drawer.closeDrawer(GravityCompat.START);
            if(!context.getClass().equals(ClassCreation.class)) {
                context.startActivity(new Intent(context, ClassCreation.class));
            } else {
                Toast.makeText(context.getApplicationContext(), "Already on character creation", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if(id == R.id.invMenu){
            drawer.closeDrawer(GravityCompat.START);
            if(!context.getClass().equals(inv.class)){
                context.startActivity(new Intent(context, inv.class));
            } else{
                Toast.makeText(context.getApplicationContext(), "Already on Inventory", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if(id == R.id.spellsMenu){
            drawer.closeDrawer(GravityCompat.START);
            if(!context.getClass().equals(spells.class)){
                context.startActivity(new Intent(context, spells.class));
            } else{
                Toast.makeText(context.getApplicationContext(), "Already on Spells page", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Intent diceIntent = new Intent(context, DiceControl.class);
            diceIntent.putExtra("statID",id);
            diceIntent.putExtra("statName", item.getTitle().toString());
            Log.v(getClass().toString(),"statID = "+id);
            int statValue = db.getStatValue(item.getTitle().toString().toLowerCase().replace(" ","_"));
            if(statValue==0){
                Toast.makeText(context.getApplicationContext(), "Please create a character to use quick roll", Toast.LENGTH_LONG).show();
                return;
            }
            diceIntent.putExtra("value",statValue);
            context.startActivity(diceIntent);
        }

        if(!context.getClass().equals(MainActivity.class)) {
            context.finish();
        }

    }

}
