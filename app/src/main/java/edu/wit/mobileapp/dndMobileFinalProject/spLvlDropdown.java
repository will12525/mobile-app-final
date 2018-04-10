package edu.wit.mobileapp.dndMobileFinalProject;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Dylan on 3/1/2018.
 */

public class spLvlDropdown extends Activity implements AdapterView.OnItemSelectedListener{
    int returnPosition=0;
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        returnPosition=pos;
        Log.v(getClass().toString(),"SpellLevel out: "+returnPosition);
    }
    public void onNothingSelected(AdapterView<?> parent)
    {
        returnPosition=0;
    }
}
