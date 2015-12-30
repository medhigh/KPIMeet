package com.meet.kpi;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meet.kpi.db.Clinic;
import com.meet.kpi.db.IServiceEvents;
import com.meet.kpi.db.SingletonDAO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IServiceEvents {
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.activity_main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    List<String> locations = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,locations);
        listView.setAdapter(adapter);
        sendRequest();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendRequest();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.purple, R.color.green, R.color.orange);
    }
    public void sendRequest(){
        SingletonDAO.getStartAsync(this);
    }
    @Override
    public void starting() {
        Log.d("MainActivity","Request Started");
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void completed(List<Clinic> Clinics) {
        Log.d("MainActivity","Request Completed");
        if(Clinics !=null)
        for (Clinic c: Clinics){
            locations.add(c.name);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    @OnClick(R.id.showVer)
    public void onClickVer(View view){
        startActivity(new Intent(this,VersionInformationActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
