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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lawrencew on 3/8/2018.
 */

public class DrawerFunctions {

    DrawerFunctions(final Activity context){

        final CoordinatorLayout testlayout = context.findViewById(R.id.drawer_stuff);

        final ConstraintLayout mainContent = context.findViewById(R.id.content_main);
        final DrawerLayout drawer = context.findViewById(R.id.drawer_layout);
        Button menuLeft =  context.findViewById(R.id.menuLeft);
        Button menuRight = context.findViewById(R.id.menuRight);

        final NavigationView navigationView1 =  context.findViewById(R.id.nav_view);
        final NavigationView navigationView2 =  context.findViewById(R.id.nav_view2);

        menuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawers();
                    testlayout.bringToFront();
                    drawer.openDrawer(GravityCompat.START);

                } else {
                    drawer.closeDrawers();
                    mainContent.bringToFront();
                }

            }
        });

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!drawer.isDrawerOpen(GravityCompat.END)){
                    drawer.closeDrawers();
                    testlayout.bringToFront();
                    drawer.openDrawer(GravityCompat.END);

                } else {
                    drawer.closeDrawers();
                    mainContent.bringToFront();
                }
            }
        });


        navigationView1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onDrawerSelection(item.getItemId(),drawer, context);
                return false;
            }
        });


        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onDrawerSelection(item.getItemId(),drawer, context);
                return false;
            }
        });

        FloatingActionButton fab = context.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DiceControl.class));
            }
        });

    }
    private void toggleDrawer(DrawerLayout drawer, int gravity){
        if (drawer.isDrawerOpen(gravity)) {
            drawer.closeDrawer(gravity);
        } else {
            drawer.openDrawer(gravity);
            drawer.bringToFront();
            drawer.requestFocus();

        }
    }

    private void onDrawerSelection(int id, DrawerLayout drawer, Activity context){
        Intent diceIntent = new Intent(context, DiceControl.class);

        switch (id){
            case R.id.character_creation:
                drawer.closeDrawer(GravityCompat.START);
                if(!context.getClass().equals(ClassCreation.class)) {
                    context.startActivity(new Intent(context, ClassCreation.class));
                } else {
                    Toast.makeText(context.getApplicationContext(), "Already on character creation", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.quick_acrobatics:
                diceIntent.putExtra("id",R.id.quick_acrobatics);
                context.startActivity(diceIntent);
                context.finish();
                break;
            case R.id.quick_animal_handling:
                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_arcana:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_athletics:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_deception:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_history:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_insight:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_intimidation:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_investigation:
                context.finish();
                context.startActivity(new Intent(context, DiceControl.class));
                break;
            case R.id.quick_medicine:
                context.finish();
                context.startActivity(new Intent(context, DiceControl.class));
                break;
            case R.id.quick_nature:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_perception:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_performance:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_persuasion:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_religion:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_slight_of_hand:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_stealth:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
            case R.id.quick_survival:

                context.startActivity(new Intent(context, DiceControl.class));
                context.finish();
                break;
        }

    }

}
