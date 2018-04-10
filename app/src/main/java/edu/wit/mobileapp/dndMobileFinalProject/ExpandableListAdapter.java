package edu.wit.mobileapp.dndMobileFinalProject;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dylan on 3/1/2018.
 * Adapted from https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private List<spellItem> list;
    private Context _context;
    private List<String> _listDataHeader;//Header Titles
    //Child data in format of header title, child title
    private HashMap<String, List<spellItem>> _listDataChild;
    private DatabaseHandler db;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<spellItem>> listDataChild) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final spellItem childText = (spellItem) getChild(groupPosition, childPosition);
        db = new DatabaseHandler(_context);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.spell_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.spellName);
        txtListChild.setText(childText.spellName);

        Button deleteBtn = (Button) convertView.findViewById(R.id.spDeleteBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = _listDataChild.get(_listDataHeader.get(groupPosition));
                db.deleteSpell(list.get(childPosition));
                list.remove(childPosition);
                _listDataChild.put(_listDataHeader.get(groupPosition), list);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this._listDataChild.get(this._listDataHeader.get(groupPosition)) != null)
            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        else
            return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.spell_group, null);
        }

        TextView grpHeader = (TextView) convertView.findViewById(R.id.groupHeader);
        grpHeader.setTypeface(null, Typeface.BOLD);
        grpHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

