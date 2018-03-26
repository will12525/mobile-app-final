package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 2/28/2018.
 */

public class invItemAdapter extends ArrayAdapter<invItem> {
    private LayoutInflater mInflater;
    private List<invItem> list;
    private DatabaseHandler db;


    public invItemAdapter(Context context, int rid, List<invItem> list){
        super(context, rid, list);
        this.list=list;
        mInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        invItem item = (invItem)getItem(position);
        db = new DatabaseHandler(getContext());

        View view = mInflater.inflate(R.layout.inv_item, null);

        TextView name;
        name = (TextView)view.findViewById(R.id.listName);
        name.setText(item.itemName);

        TextView weight;
        weight = (TextView)view.findViewById(R.id.listWeight);
        weight.setText(item.itemWeight);

        TextView value;
        value=(TextView)view.findViewById(R.id.listValue);
        value.setText(item.value);

        Button btn =(Button)view.findViewById(R.id.deleteInvBtn);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db.deleteItem(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;

    }
}
