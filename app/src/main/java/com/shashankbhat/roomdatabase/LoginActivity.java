package com.shashankbhat.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shashankbhat.roomdatabase.Room.DataRepository;
import com.shashankbhat.roomdatabase.Room.StudentEntity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.shashankbhat.roomdatabase.Utils.Constants.STUDENT_OBJ;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.submit)
    Button submit;

    String url = "https://erp.letseduvate.com/qbox/activity_app/login/";
    private DataRepository dataRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        dataRepository = new DataRepository(getApplication());
        RequestQueue queue = Volley.newRequestQueue(this);

        username.setText("1705510054");
        password.setText("1705510054");

        submit.setOnClickListener(view -> {

            dataRepository.findOneStudent(username.getText().toString());

            JSONObject params = new JSONObject();
            try {
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (url, params,
                            response -> {
                                try {
                                    String erp = response.getString("erp");
                                    String student_photo = response.getString("student_photo");
                                    String first_name = response.getJSONObject("personal_info").getString("first_name");
                                    String second_name = response.getJSONObject("personal_info").getString("second_name");
                                    String father_name = response.getJSONObject("personal_info").getString("father_name");
                                    String role = response.getJSONObject("personal_info").getString("role");
                                    String mother_name = response.getJSONObject("personal_info").getString("mother_name");
                                    String father_mobile = response.getJSONObject("personal_info").getString("father_mobile");

                                    String res = erp + student_photo + first_name + second_name + father_name + role + mother_name + father_mobile;

                                    StudentEntity studentEntity = new StudentEntity(erp, student_photo, first_name, second_name, father_name, role, mother_name, father_mobile);
                                    dataRepository.insert(studentEntity);

                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(STUDENT_OBJ, studentEntity);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            },
                            error -> {
                                Toast.makeText(getApplicationContext(), String.valueOf(error.networkResponse.statusCode), Toast.LENGTH_SHORT).show();
                            }) {
            };
            queue.add(jsonObjectRequest);

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
