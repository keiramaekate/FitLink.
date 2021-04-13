package com.example.fitlinkv3.Database;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


public interface  Dao {

    @Query( "SELECT * FROM addData")
    public abstract List<AddingShat> getAll();



}
