package com.meet.kpi.db;

import org.orman.mapper.Model;
import org.orman.mapper.annotation.PrimaryKey;

/**
 * Created by med_high on 30.12.2015.
 */
public class Clinic extends Model<Clinic> {
    @PrimaryKey(autoIncrement = true)
    public int id;
    public String name;
    public double lat;
    public double lng;
}
