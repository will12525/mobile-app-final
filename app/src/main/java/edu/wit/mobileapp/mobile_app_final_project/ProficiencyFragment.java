package edu.wit.mobileapp.mobile_app_final_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProficiencyFragment .OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProficiencyFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProficiencyFragment extends Fragment {
    private Bundle bundle = new Bundle();
    private int numberProf;
    private String text;
    private String[] possibleSkills;
    private static int selectedItemCounter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_proficiency, container, false);
        final String pcClass = this.getArguments().getString("class");

        TextView proficiencyTextView = (TextView) rootView.findViewById(R.id.proficiency_text);

        if (pcClass == "Barbarian") {
            numberProf = 2;
            possibleSkills = new String[]{"Animal Handling", "Athletics", "Intimidation", "Nature", "Perception", "Survival"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Bard") {
            numberProf = 3;
            possibleSkills = new String[]{"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand", "Stealth", "Survival"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Cleric") {
            numberProf = 2;
            possibleSkills = new String[]{"History", "Insight", "Medicine", "Persuasion", "Religion"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Druid") {
            numberProf = 2;
            possibleSkills = new String[]{"Arcana", "Animal Handling", "Insight", "Medicine", "Nature", "Perception", "Religion", "Survival"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Fighter") {
            numberProf = 2;
            possibleSkills = new String[]{"Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Monk") {
            numberProf = 2;
            possibleSkills = new String[]{"Acrobatics", "Athletics", "History", "Insight", "Religion", "Stealth"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Paladin") {
            numberProf = 2;
            possibleSkills = new String[]{"Athletics", "Insight", "Intimidation", "Medicine", "Persuasion", "Religion"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Ranger") {
            numberProf = 3;
            possibleSkills = new String[]{"Animal Handling", "Athletics", "Insight", "Investigation", "Nature", "Perception", "Stealth", "Survival"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Rogue") {
            numberProf = 4;
            possibleSkills = new String[]{"Acrobatics", "Athletics", "Deception", "Insight", "Intimidation", "Investigation", "Perception", "Performance", "Persuasion", "Sleight of Hand", "Stealth"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Sorcerer") {
            numberProf = 2;
            possibleSkills = new String[]{"Arcana", "Deception", "Insight", "Intimidation", "Persuasion", "Religion"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Warlock") {
            numberProf = 2;
            possibleSkills = new String[]{"Arcana", "Deception", "History", "Intimidation", "Investigation", "Nature", "Religion"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Wizard") {
            numberProf = 2;
            possibleSkills = new String[]{"Arcana", "History", "Insight", "Investigation", "Medicine", "Religion"};
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        } else if (pcClass == "Blood Hunter") {
            possibleSkills = new String[]{"Athletics", "Acrobatics", "Arcana", "Insight", "Investigation", "Survival"};
            numberProf = 2;
            text = getString(R.string.prof_text, numberProf);
            proficiencyTextView.setText(text);
        }

        List<String> profList = new ArrayList<String>(Arrays.asList(possibleSkills));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, profList);
        final ListView listView = (ListView) rootView.findViewById(R.id.profView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        listView.setAdapter(arrayAdapter);

        listView.getLayoutParams().height = (125 * possibleSkills.length + 40);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listView.isItemChecked(position)) { // increment selectedItemCounter whenever an item is selected
                    selectedItemCounter++;
                    Log.v("myApp", selectedItemCounter + "");
                    Log.v("myApp", listView.getItemAtPosition(position).toString());
                    bundle.putString("prof1", listView.getItemAtPosition(position).toString());

                    if (selectedItemCounter > numberProf) { // if the user attempts to select too many profs, don't let them
                        selectedItemCounter--;
                        listView.setItemChecked(position, false);
                        Snackbar.make(rootView, pcClass + "s can only have " + numberProf + " proficiencies", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                    }
                } else if (!listView.isItemChecked(position)) { // decrement selectedItemCounter on deselection of an item

                    selectedItemCounter--;
                    Log.v("myApp", "selected items: " + selectedItemCounter);
                }


            }
        });

        return rootView;
    }
}