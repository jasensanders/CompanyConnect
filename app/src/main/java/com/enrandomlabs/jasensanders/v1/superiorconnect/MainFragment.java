package com.enrandomlabs.jasensanders.v1.superiorconnect;

import android.content.Context;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // General parameter arguments
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Default names and types of parameters
    private String mParam1;
    private String mParam2;

    // CardViews
    private CardView mWelcomeCard;
    private CardView mHowtoCard;
    private CardView mAboutUsCard;

    // Expansions for CardViews
    private TextView mWelcomeDetails;
    private TextView mHowToDetails;
    private TextView mAboutUsDetails;

    // Container references for CardViews
    private LinearLayout mWelcomeContainer;
    private LinearLayout mHowToContainer;
    private LinearLayout mAboutContainer;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // General parameters that might be used in the future.
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Get references
        mWelcomeCard = root.findViewById(R.id.cardviewWelcome);
        mHowtoCard = root.findViewById(R.id.cardviewHowto);
        mAboutUsCard = root.findViewById(R.id.cardviewAbout);

        mWelcomeDetails = root.findViewById(R.id.cardviewWelcomeDetails);
        mHowToDetails = root.findViewById(R.id.cardviewHowtoDetails);
        mAboutUsDetails = root.findViewById(R.id.cardviewAboutDetails);

        mWelcomeContainer = root.findViewById(R.id.welcomeContainer);
        mHowToContainer = root.findViewById(R.id.howToContainer);
        mAboutContainer = root.findViewById(R.id.aboutContainer);

        // Set OnClick Expand Listeners
        mWelcomeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v);
            }
        });

        mHowtoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v);
            }
        });

        mAboutUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(v);
            }
        });

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void expand(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.cardviewWelcome:
                if (mWelcomeDetails.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(mWelcomeContainer);
                    mWelcomeDetails.setVisibility(View.VISIBLE);
                } else {
                    TransitionManager.beginDelayedTransition(mWelcomeContainer);
                    mWelcomeDetails.setVisibility(View.GONE);
                }

                break;

            case R.id.cardviewHowto:
                if (mHowToDetails.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(mHowToContainer);
                    mHowToDetails.setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.beginDelayedTransition(mHowToContainer);
                    mHowToDetails.setVisibility(View.GONE);
                }

                break;

            case R.id.cardviewAbout:
                if(mAboutUsDetails.getVisibility()== View.GONE) {

                    TransitionManager.beginDelayedTransition(mAboutContainer);
                    mAboutUsDetails.setVisibility(View.VISIBLE);
                }
                else {
                    TransitionManager.beginDelayedTransition(mAboutContainer);
                    mAboutUsDetails.setVisibility(View.GONE);
                }

                break;

            default: break;

        }
    }
}


