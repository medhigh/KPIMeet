package com.meet.kpi.db;

import java.util.List;

/**
 * Created by med_high on 30.12.2015.
 */
public interface IServiceEvents
{
    void starting();

    void completed(List<Clinic> Clinics);
}

