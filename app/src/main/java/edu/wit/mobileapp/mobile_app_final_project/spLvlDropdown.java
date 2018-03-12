package edu.wit.mobileapp.mobile_app_final_project;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Dylan on 3/1/2018.
 */

public class spLvlDropdown extends Activity implements AdapterView.OnItemSelectedListener{
    int returnPosition;
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        returnPosition=pos;
    }
    public void onNothingSelected(AdapterView<?> parent)
    {
        returnPosition=0;
    }
}
