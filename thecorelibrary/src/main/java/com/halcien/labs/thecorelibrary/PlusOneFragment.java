package com.halcien.labs.thecorelibrary;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.android.gms.ads.AdView;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link PlusOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlusOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlusOneFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // The URL to +1.  Must be a valid URL.

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;

    private PlusOneButton mPlusOneButton;

    private ImageButton ibReviewApp;
    private TextView appVersionTV;
    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlusOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlusOneFragment newInstance(String param1, String param2) {
        PlusOneFragment fragment = new PlusOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PlusOneFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

        ibReviewApp = (ImageButton)view.findViewById(R.id.ibReviewApp);
        ibReviewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //performAnimation(ibReviewApp, 250l, 1.3f, 0.5f);

                openReviewPage();
            }
        });

        //Find the +1 button
        mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        // ads - BaseCoreFragment will handle the loading, rem to set loadAdOnCreate false
        this.mAdview = (AdView)view.findViewById(R.id.adView);
        this.requsetAdFromAdService();

        // app ver
        appVersionTV = (TextView)view.findViewById(R.id.appversion);


        return view;
    }

    private String getAppVersion()
    {
        try {
            String appname = getResources().getString(R.string.app_name);
            String versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            return appname + " v" + versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "?";
    }


    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize(this.AppGoogleURL(), PLUS_ONE_REQUEST_CODE);

        appVersionTV.setText(this.getAppVersion());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        //if (mListener != null) {
        //    mListener.onFragmentInteraction(uri);
        //}
    }



    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }



}
