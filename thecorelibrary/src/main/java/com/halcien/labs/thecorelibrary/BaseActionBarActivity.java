package com.halcien.labs.thecorelibrary;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by williamalexander on 05/02/15.
 */
public class BaseActionBarActivity extends ActionBarActivity {

    protected void openReviewPage()
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AppGoogleURL())));
        }


    }

    protected void sendSupportEmail()
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{this.getString(R.string.support_email)});
        i.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.support_email_subject));
        i.putExtra(Intent.EXTRA_TEXT   , this.getString(R.string.support_email_body));
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }


    }

    // app url
    protected String AppGoogleURL()
    {

        // get app url app_details_url_prefix
        String appURL = getResources().getString(R.string.app_details_url_prefix);
        String packageName = this.getPackageName();
        appURL += packageName;
        return appURL;

    }
}
