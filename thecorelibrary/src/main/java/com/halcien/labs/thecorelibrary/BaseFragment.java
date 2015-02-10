package com.halcien.labs.thecorelibrary;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import com.google.android.gms.ads.*;

import android.view.View;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    protected AdView mAdview;


    public BaseFragment() {
        // Required empty public constructor
    }



    public void test()
    {

    }
    // request ad on fragment load
    protected void requsetAdFromAdService()
    {
        // not bothered if ads do not load
        if(mAdview != null)
        {
            try {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // Initiate a generic request.
                        mAdview.loadAd(new AdRequest.Builder().build());

                        mAdview.setAdListener(new AdListener() {

                            public void onAdLoaded() {


                                mAdview.setVisibility(View.VISIBLE);


                            }
                        });
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }
    }

    protected void openReviewPage()
    {
        try {
            getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AppGoogleURL())));
        }
    }


    @Override
    public void onResume() {

        super.onResume();



        if(mAdview != null)
        {
            mAdview.resume();
        }

    }

    // app url
    protected String AppGoogleURL()
    {
        if(isAdded()){
            // get app url app_details_url_prefix
            String appURL = getResources().getString(R.string.app_details_url_prefix);
            String packageName = this.getActivity().getPackageName();
            appURL += packageName;
            return appURL;
        }

        return "";

    }


    @Override
    public void onDestroy() {
        if(mAdview != null)
        {
            mAdview.destroy();
        }
        super.onDestroy();
    }

    public void onPause() {

        if(mAdview != null)
        {
            mAdview.pause();
        }

        super.onPause();
    }

}
