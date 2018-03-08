package edu.wit.mobileapp.mobile_app_final_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout mDrawerLayout;
    ListView mDrawerListView;
    RelativeLayout mDrawerRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Button menuLeft =  findViewById(R.id.menuLeft);
        Button menuRight =  findViewById(R.id.menuRight);

        menuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer(drawer,GravityCompat.START);

                if(drawer.isDrawerOpen(GravityCompat.END)){
                    drawer.closeDrawer(GravityCompat.END);
                }
            }
        });

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer(drawer, GravityCompat.END);

                if(drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView1 =  findViewById(R.id.nav_view);
        NavigationView navigationView2 =  findViewById(R.id.nav_view2);
        navigationView1.setNavigationItemSelectedListener(this);
        navigationView2.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void toggleDrawer(DrawerLayout drawer, int gravity){
        if (drawer.isDrawerOpen(gravity)) {
            drawer.closeDrawer(gravity);
        } else {
            drawer.openDrawer(gravity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       // makeToast("Item selected: "+id);
        //noinspection SimplifiableIfStatement
        if(id == R.string.quick_acrobatics){
            makeToast("Acro selected");
        }
        /*if (id == R.id.action_settings) {
            makeToast("settings picked");
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    public void makeToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
      //  toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String itemValue = item.toString();
        


        String str = getString(R.string.quick_acrobatics);
        makeToast("HERE");
      //  makeToast(item.toString());
        return false;
    }
}
