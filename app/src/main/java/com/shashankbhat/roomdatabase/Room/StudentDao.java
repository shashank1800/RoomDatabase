package com.shashankbhat.roomdatabase.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import static com.shashankbhat.roomdatabase.Utils.Constants.TABLE_NAME;

/**
 * Created by SHASHANK BHAT on 16-Mar-20.
 * <p>
 * shashankbhat1800@gmail.com
 */

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StudentEntity studentEntity);

    @Query(value = "SELECT * FROM " + TABLE_NAME + " WHERE erp = :erp LIMIT 1")
    StudentEntity findOneStudent(String erp);

}
