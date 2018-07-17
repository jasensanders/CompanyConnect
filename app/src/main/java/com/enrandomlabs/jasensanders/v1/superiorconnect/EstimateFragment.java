package com.enrandomlabs.jasensanders.v1.superiorconnect;

import android.os.Bundle;
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
 * OnFragmentInteractionListener interface
 * to handle interaction events.
 * Use the {@link EstimateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstimateFragment extends Fragment {
    // General parameter arguments
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String[] REQ_FORM_LABELS = new String[]{"name", "office name", "email", "phone", "message"};
    private static final String SUPERIOR_PLAN = "Regular Service Plan: ";

    // Default names and types of parameters
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

    public EstimateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstimateFragment.
     */
    // General parameters that might be used in the future.
    public static EstimateFragment newInstance(String param1, String param2) {
        EstimateFragment fragment = new EstimateFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_estimate, container, false);

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

                    // Prepend Service Plan Type
                    form_data[4] = SUPERIOR_PLAN + form_data[4];

                    //Send form_data.
                    SendMailService.startActionRequestEstimate(getActivity(), form_data[0], form_data);

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

//    // Method for updating UI based on button event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
