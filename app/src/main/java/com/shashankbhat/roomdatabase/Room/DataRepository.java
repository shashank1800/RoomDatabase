package com.shashankbhat.roomdatabase.Room;
import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;

/**
 * Created by SHASHANK BHAT on 16-Mar-20.
 * <p>
 * shashankbhat1800@gmail.com
 */
public class DataRepository extends AndroidViewModel {

    private StudentDao studentDao;

    public StudentEntity studentData;

    public DataRepository(Application application){
        super(application);
        StudentDatabase database = StudentDatabase.getDatabase(application);
        this.studentDao = database.studentDao();
    }

    public void insert(StudentEntity studentEntity){ new InsertAsyncTask(studentDao).execute(studentEntity);}

    public void findOneStudent(String erp){new SelectAsyncTask(studentDao).execute(erp);}

    @SuppressLint("StaticFieldLeak")
    private class InsertAsyncTask extends AsyncTask<StudentEntity,Void,Void> {
        StudentDao studentDao;
        private InsertAsyncTask(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            studentDao.insert(studentEntities[0]);
            return null;
        }
    }

    private class SelectAsyncTask extends AsyncTask<String,Void,StudentEntity>{

        StudentDao studentDao;
        private SelectAsyncTask(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected StudentEntity doInBackground(String... strings) {
            return studentDao.findOneStudent(strings[0]);
        }
    }
}
