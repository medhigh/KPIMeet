package com.meet.kpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VersionInformationActivity extends AppCompatActivity {
    @Bind(R.id.ver)
    TextView ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_information);
        ButterKnife.bind(this);
        ver.setText(R.string.version);
    }
}
