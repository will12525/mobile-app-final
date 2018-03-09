package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DiceControl extends AppCompatActivity {

    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_control);

        new DrawerFunctions(this);

    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
