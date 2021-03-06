package edu.wit.mobileapp.dndMobileFinalProject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    private DatabaseHandler db;
    private CharacterDataAdapter mCharacterDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHandler(this);
        new DrawerFunctions(this,db);

        BroadcastReceiver receiver = new MyBroadcastReceiver();


        IntentFilter filter = new IntentFilter();
        filter.addAction("CHARACTER_REMOVED");
        this.registerReceiver(receiver, filter);

        List<CharacterItem> list = db.getAllCharacters();
        CharacterItem selectedCharacter = db.getSelectedCharacter();
        if(selectedCharacter!=null){
            TextView textView = findViewById(R.id.main_ac_display);
            textView.setText(selectedCharacter.getArmorClass()+"");
            textView = findViewById(R.id.health_display);
            textView.setText(selectedCharacter.getCurrentHealth()+"");
            textView = findViewById(R.id.class_display);
            textView.setText(selectedCharacter.charClass);
            textView = findViewById(R.id.race_display);
            textView.setText(selectedCharacter.race);
        }




        ListView listView = findViewById(R.id.my_list_view);
        mCharacterDataAdapter = new CharacterDataAdapter(this,0, list);
        listView.setAdapter(mCharacterDataAdapter);



    }




    public void makeToast(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
      //  toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        mCharacterDataAdapter.refresh();
        mCharacterDataAdapter.notifyDataSetChanged();
        findViewById(R.id.content_main).bringToFront();

    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private static final String TAG = "MyBroadcastReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action!=null) {
                if (action.equals("CHARACTER_REMOVED")) {
                    mCharacterDataAdapter.refresh();
                }
            }

        }
    }

}
