package edu.wit.mobileapp.mobile_app_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class inv extends AppCompatActivity {
    Button itemBtn;
    Button moneyBtn;
    //Buttons for dialog
    Button wepBtn;
    Button armBtn;
    Button miscBtn;
    View dialogView;
    invItemAdapter adapter;
    List<invItem> list;
    ListView lv;
    double wtCounter=0;
    //textview gold/silver/copper
    TextView tvg, tvs, tvc;
    final Context mContext = this;
    private DatabaseHandler db;

    int gold,silver,copper;

//TODO Edit itemAddDialog for description, damage, damage type, item type(?), return to database somehow
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv);
        setTitle("Inventory");

        //create db for hamburgers
        db = new DatabaseHandler(this);
        new DrawerFunctions(this, db);
        //Money Setup
        moneyBtn=findViewById(R.id.btnMoney);
        tvg = findViewById(R.id.goldView);
        tvs = findViewById(R.id.silverView);
        tvc = findViewById(R.id.copperView);
        tvg.setText("Gold: "+gold);
        tvs.setText("Silver: "+silver);
        tvc.setText("Copper: "+copper);
        //Add Item Setup
        list = new ArrayList<>();
        itemBtn =findViewById(R.id.btnItem);
        adapter = new invItemAdapter(this, 0,list);
        lv=findViewById(R.id.invList);
        lv.setAdapter(adapter);
        //First List Element contains Column Titles
        invItem header = new invItem();
        header.itemName="Item Name";
        header.itemWeight="Weight";
        //Nested Dialog prep
        LayoutInflater inflater = LayoutInflater.from(mContext);
        dialogView = inflater.inflate(R.layout.dialog_itemtype, null);
        wepBtn = (Button)dialogView.findViewById(R.id.weaponBtn);
        armBtn=(Button)dialogView.findViewById(R.id.armorBtn);
        miscBtn=(Button)dialogView.findViewById(R.id.genericBtn);

        list.add(header);

        //Handles button from Inventory activity screen
        itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create Alert Dialog
                final AlertDialog.Builder ADB = new AlertDialog.Builder(inv.this);
                ADB.setTitle("Choose Item Type");
                ADB.setView(dialogView);
                ADB.setCancelable(true);
                ADB.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                //show dialog
                AlertDialog alertDialog = ADB.create();
                alertDialog.show();
            }
        });
        //Adding Misc item
        miscBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create Alert Dialog
                final AlertDialog.Builder ADB = new AlertDialog.Builder(inv.this);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                ADB.setTitle("ADD ITEM");
                final View miscView = inflater.inflate(R.layout.additem_dialog, null);
                ADB.setView(miscView);
                //Link to edit texts
                final EditText etName =miscView.findViewById(R.id.itemNameIn);
                final EditText etWt =miscView.findViewById(R.id.itemWeightIn);
                final EditText etDesc=miscView.findViewById(R.id.itemDescIn);
                final EditText etVal=miscView.findViewById(R.id.itemValueIn);


                ADB
                        .setCancelable(true)
                        //Set up OK button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                invItem item = new invItem();
                                //Check if Weight is empty
                                if(etWt.getText().toString().compareTo("")==0)
                                    etWt.setText("0");
                                if(etName.getText().toString().compareTo("")==0)
                                    etName.setText("DID NOT SET NAME");
                                item.description=etDesc.getText().toString();
                                item.itemName=etName.getText().toString();
                                item.itemWeight=etWt.getText().toString();
                                item.value=etVal.getText().toString();
                                String wtConvert = etWt.getText().toString();
                                wtCounter+=Double.parseDouble(wtConvert);
                                list.add(item);
                                db.addItem(item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        //Set Cancel Button
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                //show dialog
                AlertDialog alertDialog = ADB.create();
                alertDialog.show();
            }
        });
        //Adding Weapon item
        wepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                final AlertDialog.Builder ADB = new AlertDialog.Builder(inv.this);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                ADB.setTitle("ADD WEAPON");
                final View wepView = inflater.inflate(R.layout.addweapon_dialog, null);
                ADB.setView(wepView);
                //link to all EditTexts
                final EditText etName = wepView.findViewById(R.id.addWepName);
                final EditText etWt = wepView.findViewById(R.id.addWepWeight);
                final EditText etDieCount=wepView.findViewById(R.id.addWepDieCount);
                final EditText etDieType=wepView.findViewById(R.id.addWepDieType);
                final EditText etDamageType=wepView.findViewById(R.id.addWepDamageType);
                final EditText etDesc=wepView.findViewById(R.id.addWepDesc);
                final EditText etVal=wepView.findViewById(R.id.addWepValue);
                ADB
                        .setCancelable(true)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                invItem toAdd = new invItem();
                                toAdd.type=1;
                                toAdd.itemName=etName.getText().toString();
                                toAdd.itemWeight=etWt.getText().toString();
                                toAdd.numDie=Integer.parseInt(etDieCount.getText().toString());
                                toAdd.die=Integer.parseInt(etDieType.getText().toString());
                                toAdd.dmgType=etDamageType.getText().toString();
                                toAdd.description=etDesc.getText().toString();
                                toAdd.value=etVal.getText().toString();
                                wtCounter+=Double.parseDouble(toAdd.itemWeight);
                                list.add(toAdd);
                                db.addItem(toAdd);
                                adapter.notifyDataSetChanged();

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
        moneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder ADB2 = new AlertDialog.Builder(inv.this);
                LayoutInflater inflater2 = LayoutInflater.from(mContext);
                final View moneyView = inflater2.inflate(R.layout.money_dialog, null);
                ADB2.setView(moneyView);
                final EditText eGold = (EditText)moneyView.findViewById(R.id.goldEdit);
                final EditText eSilver = (EditText)moneyView.findViewById(R.id.silverEdit);
                final EditText eCopper = (EditText)moneyView.findViewById(R.id.copperEdit);

                ADB2
                        .setCancelable(true)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(eGold.getText().toString().compareTo("")!=0){
                                    gold=gold+Integer.parseInt(eGold.getText().toString());
                                    tvg.setText("Gold: "+gold);
                                }
                                if(eSilver.getText().toString().compareTo("")!=0){
                                    silver=silver+Integer.parseInt(eSilver.getText().toString());
                                    tvs.setText("Silver: "+silver);
                                }
                                if(eCopper.getText().toString().compareTo("")!=0){
                                    copper=copper+Integer.parseInt(eCopper.getText().toString());
                                    tvc.setText("Copper: "+copper);
                                }

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog moneyAlert = ADB2.create();
                moneyAlert.show();
            }
        });

    }
    public void removeItem(View v){
        invItem itemToRemove = (invItem)v.getTag();
        adapter.remove(itemToRemove);
    }
}
