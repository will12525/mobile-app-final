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
    invItemAdapter adapter;
    List<invItem> list;
    ListView lv;
    double wtCounter=0;
    //textview gold/silver/copper
    TextView tvg, tvs, tvc;
    final Context mContext = this;

    int gold,silver,copper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv);
        setTitle("Inventory");
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

        list.add(header);


        itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create Alert Dialog
                final AlertDialog.Builder ADB = new AlertDialog.Builder(inv.this);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                ADB.setTitle("ADD ITEM");
                final View dialogView = inflater.inflate(R.layout.additem_dialog, null);
                ADB.setView(dialogView);
                //Link to edit texts
                final EditText etName =dialogView.findViewById(R.id.itemNameIn);
                final EditText etWt =dialogView.findViewById(R.id.itemWeightIn);

                //Set up OK button
                ADB
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        invItem item = new invItem();
                        //Check if Weight is empty
                        if(etWt.getText().toString().compareTo("")==0)
                            etWt.setText("0");
                        if(etName.getText().toString().compareTo("")==0)
                            etName.setText("DID NOT SET NAME");
                        item.itemName=etName.getText().toString();
                        item.itemWeight=etWt.getText().toString();
                        String wtConvert = etWt.getText().toString();
                        wtCounter+=Double.parseDouble(wtConvert);
                        list.add(item);
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
