package com.shashankbhat.roomdatabase.Room;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;

import com.shashankbhat.roomdatabase.MainActivity;

import static com.shashankbhat.roomdatabase.Utils.Constants.STUDENT_OBJ;

/**
 * Created by SHASHANK BHAT on 16-Mar-20.
 * <p>
 * shashankbhat1800@gmail.com
 */
public class DataRepository extends AndroidViewModel {

    private StudentDao studentDao;
    private StudentEntity studentEntity;
    private Application application;

    public DataRepository(Application application){
        super(application);
        this.application = application;
        StudentDatabase database = StudentDatabase.getDatabase(application);
        this.studentDao = database.studentDao();
    }

    public void insert(StudentEntity studentEntity){ new InsertAsyncTask(studentDao).execute(studentEntity);}

    public void findOneStudent(String erp){
        new SelectAsyncTask(studentDao).execute(erp);
    }

    @SuppressLint("StaticFieldLeak")
    private class InsertAsyncTask extends AsyncTask<StudentEntity,Void,Void> {
        StudentDao studentDao;
        private InsertAsyncTask(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            studentDao.insert(studentEntities[0]);
            System.out.println("Inserted");
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

        @Override
        protected void onPostExecute(StudentEntity studentEntity) {
            super.onPostExecute(studentEntity);
            if(studentEntity != null) {
                Toast.makeText(application, "Database Hit", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putParcelable(STUDENT_OBJ, studentEntity);
                intent.putExtras(bundle);

                application.startActivity(intent);
            }
        }
    }
}
