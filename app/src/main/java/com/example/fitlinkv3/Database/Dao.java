package com.example.fitlinkv3.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// this is to set the methods in the database class
public interface  Dao {

    @Query( "SELECT * FROM addData")
    public abstract List<AddingShat> getAddingShatList();
@Insert
    void insertAddingShat ( AddingShat addData);

@Update
    void updateAddingShat (AddingShat addData );
@Delete
    void deleteAddingShat (AddingShat addData);


}
