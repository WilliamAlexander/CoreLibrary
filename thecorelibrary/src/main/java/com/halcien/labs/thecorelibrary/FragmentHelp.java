package com.halcien.labs.thecorelibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHelp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHelp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHelp extends BaseFragment {


    public FragmentHelp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        // ads - BaseCoreFragment will handle the loading, rem to set loadAdOnCreate false
        this.mAdview = (AdView)view.findViewById(R.id.adView);
        this.requsetAdFromAdService();

        return view;
    }


}