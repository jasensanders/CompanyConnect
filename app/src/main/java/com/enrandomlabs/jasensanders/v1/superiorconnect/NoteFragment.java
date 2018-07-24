package com.enrandomlabs.jasensanders.v1.superiorconnect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enrandomlabs.jasensanders.v1.superiorconnect.sendservice.SendMailService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * OnFragmentInteraction listener.
 * to handle interaction events.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends Fragment {
    // General names for parameter arguments
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String[] REQ_FORM_LABELS = new String[]{"name", "office name", "email", "phone", "message"};


    private String mParam1;
    private String mParam2;


    private CheckBox contact;
    private String[] form_data = new String[7];
    TextView[] FORM;
    EditText name;
    EditText officeName;
    EditText email;
    EditText phone;
    EditText fax;
    EditText message;

    //private OnFragmentInteractionListener mListener;

    public NoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteFragment.
     */
    // General parameters that might be used in the future.
    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        // Get references
        TextView policyLink = root.findViewById(R.id.policy_link);
        policyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.superiorjanitorialsystems.com/superior_janitorial_systems_policy.htm"));
                startActivity(browserIntent);
            }
        });
        contact = root.findViewById(R.id.contact_checkbox);
        name = root.findViewById(R.id.name);
        officeName = root.findViewById(R.id.office_name);
        email = root.findViewById(R.id.email);
        phone = root.findViewById(R.id.phone);
        fax = root.findViewById(R.id.fax);
        message = root.findViewById(R.id.message);
        FORM = new TextView[]{name, officeName, email, phone, message};

        Button submit = root.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If the Form is filled out:
                if(validate(FORM, REQ_FORM_LABELS)){

                    // Clear Error states from Views (if any)
                    clearErrorFlags(FORM);

                    //Send form_data.
                    SendMailService.startActionSendNote(getActivity(), form_data[0], form_data);

                }

            }
        });


        return root;
    }

    private boolean validate(TextView[] data, String[] labels){
        // Check fields filled in else Toast Error message, turn empty field red.

        for(int i =0; i<data.length; i++){
            if(data[i]== null || isEmpty(data[i])){

                apologise(data[i], labels[i]);
                form_data = new String[7];
                return false;
            }else{
                form_data[i] = data[i].getText().toString();
            }
        }
        form_data[5] = String.valueOf(contact.isChecked());
        form_data[6] = fax.getText().toString();
        return true;
    }

    private boolean isEmpty(TextView view){
        return view.getText().toString().matches("");

    }

    private void apologise(TextView view, String label){
        view.setBackgroundColor(getResources().getColor(R.color.colorTextViewError));
        Toast.makeText(getActivity(), "You did not enter a "+ label, Toast.LENGTH_LONG).show();
    }
    private void clearErrorFlags(TextView[] views){
        for(TextView view: views){
            view.setBackgroundColor(getResources().getColor(R.color.primaryWhite));
        }
    }

}
