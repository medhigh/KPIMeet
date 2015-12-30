package com.meet.kpi;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

public class VersoinTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    public VersoinTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    public void testRun() {
        solo.sleep(1000);
        solo.clickOnButton(R.id.showVer);
        solo.waitForActivity(VersionInformationActivity.class);
        assertTrue(((TextView)solo.getView(R.id.ver)).getText().toString().contains("SNAPSHOT"));
        assertTrue(((TextView)solo.getView(R.id.ver)).getText().toString().length()>=10);
        solo.goBack();
        assertTrue(solo.getCurrentActivity().getClass().equals(MainActivity.class));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}

