package com.meet.kpi;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meet.kpi.db.Clinic;
import com.meet.kpi.db.IServiceEvents;
import com.meet.kpi.db.SingletonDAO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IServiceEvents {
    @Bind(R.id.listView)
    ListView listView;
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
    }
    public void sendRequest(){
        SingletonDAO.getStartAsync(this);
    }
    @Override
    public void starting() {
        Log.d("MainActivity","Request Started");
    }

    @Override
    public void completed(List<Clinic> clinics) {
        Log.d("MainActivity","Request Completed");
        for (Clinic c:clinics){
            locations.add(c.name);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
