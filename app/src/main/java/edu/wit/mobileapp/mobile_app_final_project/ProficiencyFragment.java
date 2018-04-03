package edu.wit.mobileapp.mobile_app_final_project;

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
 *
 * created by usingerr on 02/28/2018
 */
public class ProficiencyFragment extends Fragment {
    private Bundle bundle;
    private int numberProf;
    private String text;
    private String[] possibleSkills;
    private static int selectedItemCounter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_proficiency, container, false);
        final String pcClass = getArguments().getString("class");

        bundle = ((ClassCreation)getActivity()).getBundle();

        TextView proficiencyTextView = (TextView) rootView.findViewById(R.id.proficiency_text);

        switch (pcClass) {
            case "Barbarian":
                numberProf = 2;
                possibleSkills = new String[]{"Animal Handling", "Athletics", "Intimidation", "Nature", "Perception", "Survival"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Bard":
                numberProf = 3;
                possibleSkills = new String[]{"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand", "Stealth", "Survival"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Cleric":
                numberProf = 2;
                possibleSkills = new String[]{"History", "Insight", "Medicine", "Persuasion", "Religion"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Druid":
                numberProf = 2;
                possibleSkills = new String[]{"Arcana", "Animal Handling", "Insight", "Medicine", "Nature", "Perception", "Religion", "Survival"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Fighter":
                numberProf = 2;
                possibleSkills = new String[]{"Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Monk":
                numberProf = 2;
                possibleSkills = new String[]{"Acrobatics", "Athletics", "History", "Insight", "Religion", "Stealth"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Paladin":
                numberProf = 2;
                possibleSkills = new String[]{"Athletics", "Insight", "Intimidation", "Medicine", "Persuasion", "Religion"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Ranger":
                numberProf = 3;
                possibleSkills = new String[]{"Animal Handling", "Athletics", "Insight", "Investigation", "Nature", "Perception", "Stealth", "Survival"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Rogue":
                numberProf = 4;
                possibleSkills = new String[]{"Acrobatics", "Athletics", "Deception", "Insight", "Intimidation", "Investigation", "Perception", "Performance", "Persuasion", "Sleight of Hand", "Stealth"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Sorcerer":
                numberProf = 2;
                possibleSkills = new String[]{"Arcana", "Deception", "Insight", "Intimidation", "Persuasion", "Religion"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Warlock":
                numberProf = 2;
                possibleSkills = new String[]{"Arcana", "Deception", "History", "Intimidation", "Investigation", "Nature", "Religion"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Wizard":
                numberProf = 2;
                possibleSkills = new String[]{"Arcana", "History", "Insight", "Investigation", "Medicine", "Religion"};
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
            case "Blood Hunter":
                possibleSkills = new String[]{"Athletics", "Acrobatics", "Arcana", "Insight", "Investigation", "Survival"};
                numberProf = 2;
                text = getString(R.string.prof_text, numberProf);
                proficiencyTextView.setText(text);
                break;
        }

        final List<String> profList = new ArrayList<String>(Arrays.asList(possibleSkills));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, profList);
        final ListView listView = (ListView) rootView.findViewById(R.id.profView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        listView.setAdapter(arrayAdapter);

        listView.getLayoutParams().height = (125 * possibleSkills.length + 40);
        bundle.putInt("numberProf", numberProf);

        selectedItemCounter = 0;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listView.isItemChecked(position)) { // increment selectedItemCounter whenever an item is selected
                    selectedItemCounter++;
                    Log.v("myApp", selectedItemCounter + "");
                    Log.v("myApp", listView.getItemAtPosition(position).toString());

                    if (selectedItemCounter > numberProf) { // if the user attempts to select too many profs, don't let them
                        selectedItemCounter--;
                        listView.setItemChecked(position, false);
                        Snackbar.make(rootView, pcClass + "s can only have " + numberProf + " proficiencies", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                    }

                    bundle.putString("prof" + selectedItemCounter, listView.getItemAtPosition(position).toString());
                } else if (!listView.isItemChecked(position)) { // decrement selectedItemCounter on deselection of an item
                    bundle.remove("prof" + selectedItemCounter);
                    selectedItemCounter--;
                    Log.v("myApp", "selected items: " + selectedItemCounter);
                }

            }
        });

        return rootView;
    }
}