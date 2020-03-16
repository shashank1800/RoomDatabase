package com.shashankbhat.roomdatabase.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.shashankbhat.roomdatabase.Utils.Constants.DATABASE_NAME;

/**
 * Created by SHASHANK BHAT on 16-Mar-20.
 * <p>
 * shashankbhat1800@gmail.com
 */

@Database(entities = {StudentEntity.class},version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();

    public static StudentDatabase mInstance;


    public static synchronized StudentDatabase getDatabase(Context context){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
