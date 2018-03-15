package edu.wit.mobileapp.mobile_app_final_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassButton .OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassButton #newInstance} factory method to
 * create an instance of this fragment.
 *
 * created by usingerr on 02/28/2018
 */
public class ClassButton extends Fragment {
    static String TAG = "myApp";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_class_button, container, false);

        Button myBtn = (Button) rootView.findViewById(R.id.classConfirmButton);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classChosen = getArguments().getString("class");


                if (classChosen == "Class") {
                    /*Snackbar.make(rootView, "Please select a class", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                    Snackbar clasSnack = Snackbar.make(rootView, "Please select a class", Snackbar.LENGTH_SHORT);
                    TextView textView = (TextView) (clasSnack.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    clasSnack.show();

                } else {

                    Log.v(TAG, "" + classChosen);
                    Bundle bundle = new Bundle();
                    bundle.putString("class", classChosen);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    Fragment myFragment = new ProficiencyFragment();

                    myFragment.setArguments(bundle);
                    transaction.replace(R.id.container, myFragment);
                    transaction.commit();
                }
            }
        });

        return rootView;
    }
}
