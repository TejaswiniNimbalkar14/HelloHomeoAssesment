package com.tejaswininimbalkar.hellohomeoassesment.LocalDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"name"}, unique = true)})
        //(indices = @Index(value = {"name"}, unique = true))
public class CrewMembersEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "agency")
    public String agency;
    @ColumnInfo(name = "image")
    public String image;
    @ColumnInfo(name = "wikipedia")
    public String wikipedia;
    @ColumnInfo(name = "status")
    public String status;

}
