package com.meet.kpi.db;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by med_high on 30.12.2015.
 */
public class SingletonDAO {
    private static DBAsyncGetter instance = null;
    private static IServiceEvents lastActivity;
    private SingletonDAO() {}

    public static synchronized void getStartAsync(@NonNull IServiceEvents contextActivity) {
        lastActivity = contextActivity;
        if (instance == null) {
            instance = new DBAsyncGetter(contextActivity);
            instance.execute();
        }else {
            instance.setContext(contextActivity);
            instance.onPostExecute(instance.list);
        }
    }
    public static synchronized List<Clinic> getList(){
        if (instance == null) {
            return new ArrayList<Clinic>(1);

        }
        if (instance.list == null) {
            return new ArrayList<Clinic>(1);
        }
        return instance.list;
    }
    public static void reload(){
        getStartAsync(lastActivity);
    }
}
