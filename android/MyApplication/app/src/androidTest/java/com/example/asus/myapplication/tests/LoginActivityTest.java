package com.example.asus.myapplication.tests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import com.example.asus.myapplication.LoginActivity;
import com.example.asus.myapplication.R;
import android.util.Log;

/**
 * Created by Steven on 3/30/2015.
 */
public class LoginActivityTest extends ActivityUnitTestCase<LoginActivity>{
    private Intent intent;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);
        intent = new Intent(context, LoginActivity.class);

    }

    @MediumTest
    public void testPreconditions() {
        startActivity(intent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.guestButton);
        final Button launchLoginButton = (Button) getActivity().findViewById(R.id.loginButton);
        Log.d("","Running Pre conditions ---------------------");
        if (getActivity() == null)
            Log.d("LoginActivity", " is null (not good)");
        else
            Log.d("LoginActivity", " has successfully launched.");
        if (launchLoginButton == null)
            Log.d("Launch Login Button"," is null (not good)");
        else
            Log.d("Launch Login Button", " has successfully launched.");
        if (launchNextButton == null)
            Log.d("Launch Next Button"," is null (not good)");
        else
            Log.d("Launch Next Button", "has successfully launched.");
        Log.d("","Pre conditions have completed running.");
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(intent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.guestButton);
        launchNextButton.performClick();

        final Intent ActivityIntent = getStartedActivityIntent();
        Log.d("", "Launching next activity after clicking button ----------------");
        if (ActivityIntent == null)
            Log.d("ActivityIntent", " is null (not good)");
        else
            Log.d("ActivityIntent", " has successfully launched");
        Log.d("testNextActivity", " has been completed.");
    }
}
