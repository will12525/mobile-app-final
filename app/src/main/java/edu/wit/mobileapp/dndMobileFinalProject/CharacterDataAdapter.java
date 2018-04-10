package edu.wit.mobileapp.dndMobileFinalProject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lawrencew on 3/9/2018.
 */

public class CharacterDataAdapter extends ArrayAdapter<CharacterItem> {


    private LayoutInflater mInflater;
    private List<CharacterItem> gridItems;
    private Activity mContext;
    private DatabaseHandler db;


    CharacterDataAdapter(@NonNull Activity context, int resource, List<CharacterItem> gridItems) {
        super(context, resource);
        this.mContext = context;
        this.gridItems = gridItems;

        mInflater = LayoutInflater.from(context);
        db = new DatabaseHandler(context);
    }

    @Override
    public int getCount() {
        return gridItems.size();
    }

    @Override
    public CharacterItem getItem(int position) {
        return gridItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void refresh(){

        CharacterItem characterItem = db.getSelectedCharacter();

        if(characterItem!=null){

            ((TextView)mContext.findViewById(R.id.txt_title)).setText(characterItem.name);
            ((TextView)mContext.findViewById(R.id.main_ac_display)).setText(characterItem.getArmorClass()+"");
            ((TextView)mContext.findViewById(R.id.health_display)).setText(characterItem.getCurrentHealth()+"");
            ((TextView)mContext.findViewById(R.id.class_display)).setText(characterItem.charClass);
            ((TextView)mContext.findViewById(R.id.race_display)).setText(characterItem.race);
        } else {
            ((TextView)mContext.findViewById(R.id.txt_title)).setText("No players");
            ((TextView)mContext.findViewById(R.id.main_ac_display)).setText("0");
            ((TextView)mContext.findViewById(R.id.health_display)).setText("0");
            ((TextView)mContext.findViewById(R.id.class_display)).setText("");
            ((TextView)mContext.findViewById(R.id.race_display)).setText("");
        }
        gridItems = db.getAllCharacters();
        notifyDataSetChanged();
    }


    @NonNull
    public View getView(int position, View convertView, @NonNull final ViewGroup parent){

        final ViewHolder viewHolder;
        final CharacterItem item = gridItems.get(position);

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.character_list_item, null);

            viewHolder.name = convertView.findViewById(R.id.char_name);
            viewHolder.data = convertView.findViewById(R.id.char_data);
            viewHolder.deleteChar = convertView.findViewById(R.id.delete_character_button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(item.name);
        viewHolder.data.setText(item.getQuickView());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String characterName = viewHolder.name.getText().toString();
                db.updateSelected(characterName);
                //db.deleteChar(viewHolder.name.getText().toString());
                refresh();
            }
        });

        viewHolder.deleteChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireMissilesDialogFragment fireMissilesDialogFragment = new FireMissilesDialogFragment();

                Bundle args = new Bundle();
                args.putString("name", viewHolder.name.getText().toString());
                fireMissilesDialogFragment.setArguments(args);
                fireMissilesDialogFragment.show(mContext.getFragmentManager(),"Testing");
                refresh();


            }
        });

        return convertView;
    }

    public static class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final String name = getArguments().getString("name");
            builder.setMessage("Delete " + name + "?")
                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            DatabaseHandler db = new DatabaseHandler(getContext());
                            db.deleteCharacter(name);
                            Intent intent = new Intent("CHARACTER_REMOVED");
                            getContext().sendBroadcast(intent);

                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private static class ViewHolder{
        TextView name;
        TextView data;
        ImageButton deleteChar;
    }
}
