package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private Context mContext;


    CharacterDataAdapter(@NonNull Context context, int resource, List<CharacterItem> gridItems) {
        super(context, resource);
        this.mContext = context;
        this.gridItems = gridItems;

        mInflater = LayoutInflater.from(context);
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

    private void refresh(){
        DatabaseHandler db = new DatabaseHandler(mContext);
        gridItems = db.getAllCharacters();
        notifyDataSetChanged();
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent){

        final ViewHolder viewHolder;
        final CharacterItem item = gridItems.get(position);

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.character_list_item, null);

            viewHolder.name = convertView.findViewById(R.id.char_name);
            viewHolder.data = convertView.findViewById(R.id.char_data);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(item.name);
        viewHolder.data.setText( item.data);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(mContext);
                db.deleteChar(viewHolder.name.getText().toString());
                refresh();
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView data;
    }
}
