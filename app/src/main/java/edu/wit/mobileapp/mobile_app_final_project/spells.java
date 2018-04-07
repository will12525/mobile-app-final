package edu.wit.mobileapp.mobile_app_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class spells extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<spellItem>> listDataChild;
    List<spellItem> lv0, lv1, lv2, lv3, lv4, lv5, lv6, lv7, lv8, lv9;
    Context mContext = this;
    private DatabaseHandler db;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);
        //create db
        db = new DatabaseHandler(this);
        new DrawerFunctions(this, db);

        expListView=(ExpandableListView)findViewById(R.id.lvExp);
        prepareHeaders();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



        expListView=findViewById(R.id.lvExp);
        listAdapter=new ExpandableListAdapter(mContext, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        repopulate();

        btn = (Button)findViewById(R.id.AddSpell);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                final AlertDialog.Builder ADB = new AlertDialog.Builder(spells.this);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                ADB.setTitle("Add Spell");
                final View dView = inflater.inflate(R.layout.addspell_dialog, null);
                ADB.setView(dView);
                //Prepare to receive data
                final EditText etSpName=dView.findViewById(R.id.spellNameIn);
                final EditText etSpDescription=dView.findViewById(R.id.spellDescIn);
                final EditText etSpDmgDienumIn=dView.findViewById(R.id.spellDmgDieNumIn);
                final EditText etSpDmgDieTypeIn=dView.findViewById(R.id.spellDmgDieTypeIn);
                final RadioButton isCombat =dView.findViewById(R.id.toCombatRadioSp);
                final EditText etSpDmgType = dView.findViewById(R.id.spellDmgTypeIn);
                //Initialize Spinner
                final Spinner lvlSelect = (Spinner)dView.findViewById(R.id.splvlDropdown);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, R.array.spellLvl, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lvlSelect.setAdapter(adapter);

                ADB
                        .setCancelable(true)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                spellItem spellIn = new spellItem();
                                spellIn.spellName=etSpName.getText().toString();
                                spellIn.spellDescription=etSpDescription.getText().toString();
                                lvlSelect.setOnItemSelectedListener(null);
                                int spLvl = (int)lvlSelect.getSelectedItemId();
                                Log.v(getClass().toString(),"spLvl: " + spLvl);
                                spellIn.spellLevel=spLvl;
                                spellIn.spDie=Integer.parseInt(etSpDmgDieTypeIn.getText().toString());
                                spellIn.spNumDie=Integer.parseInt(etSpDmgDienumIn.getText().toString());
                                spellIn.spDmgType=etSpDmgType.getText().toString();
                                if(isCombat.isChecked())
                                    spellIn.spType=1;
                                else
                                    spellIn.spType=0;
                                List<spellItem> list = listDataChild.get(listDataHeader.get(spLvl));
                                list.add(spellIn);
                                db.addSpell(spellIn);
                                listDataChild.put(listDataHeader.get(spLvl), list);
                                listAdapter.notifyDataSetChanged();
                                listAdapter.notifyDataSetInvalidated();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog AD = ADB.create();
                AD.show();
            }

        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPos, int childPos, long Id) {
                final AlertDialog.Builder AlertDB = new AlertDialog.Builder(spells.this);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                final View descView = inflater.inflate(R.layout.spell_display_dialog, null);
                final TextView displayDesc = (TextView)descView.findViewById(R.id.spDescDisplay);
                final List<spellItem> spellToDisp = listDataChild.get(listDataHeader.get(groupPos));


                AlertDB.setTitle("Spell Description");
                AlertDB.setView(descView);
                displayDesc.setText("Spell Name: "+spellToDisp.get(childPos).spellName+"\nSpell Level: "+spellToDisp.get(childPos).spellLevel+"\nSpell Damage: "+spellToDisp.get(childPos).spNumDie+"d"+spellToDisp.get(childPos).spDie+"\nDamage Type: "+spellToDisp.get(childPos).spDmgType+"\nSpell Description: "+spellToDisp.get(childPos).spellDescription);

                AlertDB
                        .setCancelable(true)
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog descDialog = AlertDB.create();
                descDialog.show();
                return false;

            }
        });
    }

    private void prepareHeaders(){
        //Child placeholders required otherwise App crashes when you tap an empty group.
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<spellItem>>();

        listDataHeader.add("Level 0 Spells");
        listDataHeader.add("Level 1 Spells");
        listDataHeader.add("Level 2 Spells");
        listDataHeader.add("Level 3 Spells");
        listDataHeader.add("Level 4 Spells");
        listDataHeader.add("Level 5 Spells");
        listDataHeader.add("Level 6 Spells");
        listDataHeader.add("Level 7 Spells");
        listDataHeader.add("Level 8 Spells");
        listDataHeader.add("Level 9 Spells");
        spellItem spPlaceholder = new spellItem();
        spPlaceholder.spellName="PLACEHOLDER";


        List<spellItem> lv0 = new ArrayList<spellItem>();
       /* lv0.add(spPlaceholder);*/

        List<spellItem> lv1 = new ArrayList<spellItem>();
        /*lv1.add(spPlaceholder);*/

        List<spellItem> lv2 = new ArrayList<spellItem>();
   /*     lv2.add(spPlaceholder);*/

        List<spellItem> lv3 = new ArrayList<spellItem>();
       /* lv3.add(spPlaceholder);*/

        List<spellItem> lv4 = new ArrayList<spellItem>();
     /*   lv4.add(spPlaceholder);*/

        List<spellItem> lv5 = new ArrayList<spellItem>();
       /* lv5.add(spPlaceholder);*/

        List<spellItem> lv6 = new ArrayList<spellItem>();
       /* lv6.add(spPlaceholder);*/

        List<spellItem> lv7 = new ArrayList<spellItem>();
        /*lv7.add(spPlaceholder);*/

        List<spellItem> lv8 = new ArrayList<spellItem>();
        /*lv8.add(spPlaceholder);*/

        List<spellItem> lv9 = new ArrayList<spellItem>();
/*        lv9.add(spPlaceholder);*/

        listDataChild.put(listDataHeader.get(0), lv0);
        listDataChild.put(listDataHeader.get(1), lv1);
        listDataChild.put(listDataHeader.get(2), lv2);
        listDataChild.put(listDataHeader.get(3), lv3);
        listDataChild.put(listDataHeader.get(4), lv4);
        listDataChild.put(listDataHeader.get(5), lv5);
        listDataChild.put(listDataHeader.get(6), lv6);
        listDataChild.put(listDataHeader.get(7), lv7);
        listDataChild.put(listDataHeader.get(8), lv8);
        listDataChild.put(listDataHeader.get(9), lv9);
    }
    public void addSpell(int spLvl, List<spellItem> Name)
    {
        listDataChild.put(listDataHeader.get(spLvl), Name);
    }

    private void repopulate(){
        List<spellItem> fromDb = db.getCharacterSpells();
        for(int i=0; i<fromDb.size(); i++){
            Log.v(getClass().toString(),fromDb.get(i).toString());
            List<spellItem>spLvlRow = listDataChild.get(listDataHeader.get(fromDb.get(i).spellLevel));
            spLvlRow.add(fromDb.get(i));
            listDataChild.put(listDataHeader.get(fromDb.get(i).spellLevel), spLvlRow);
            listAdapter.notifyDataSetChanged();
            listAdapter.notifyDataSetInvalidated();
            Log.v(getClass().toString(),"repopulted Spells");
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        repopulate();
        Log.v(getClass().toString(), "Repopulated spells");
    }


}
