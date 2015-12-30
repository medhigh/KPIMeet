package com.meet.kpi.db;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.orman.dbms.Database;
import org.orman.dbms.mysql.MySQL;
import org.orman.dbms.mysql.MySQLSettingsImpl;
import org.orman.exception.OrmanException;
import org.orman.mapper.MappingSession;
import org.orman.mapper.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by med_high on 30.12.2015.
 */
public class DBAsyncGetter extends AsyncTask<Void,Void,List<Clinic>> {

    List<Clinic> list;
    IServiceEvents context;
    public boolean reload;
    public void setContext(IServiceEvents context) {
        this.context = context;
    }

    public static final String TAG = "DBAsyncGetter";

    public DBAsyncGetter(@NonNull IServiceEvents contextActivity) {
        this.context = contextActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context.starting();
    }

    @Override
    protected List<Clinic> doInBackground(Void... params) {
        List<Clinic> list = new ArrayList<>(1);
        try{
            list = getAll(reload);
        } catch (OrmanException e){
            Log.e(TAG,"could not load any structure from base: exeption: "+e.toString());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Clinic> list) {
        context.completed(list);
    }


    public static List<Clinic> getAll(boolean reload){
        if(!reload) {
            Database db = new MySQL(new MySQLSettingsImpl("medhigh", "635589", "onmaplocations", "109.86.145.149", 3306));
            MappingSession.registerDatabase(db);
            MappingSession.registerEntity(Clinic.class);
            MappingSession.start();
        }
        List<Clinic> clinicList = Model.fetchAll(Clinic.class);
        for (Clinic c: clinicList){
            Log.d("db", "HERE OUR CLINIC INFO " + c.name + " " + c.lat);
        }
        return clinicList;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }
}
