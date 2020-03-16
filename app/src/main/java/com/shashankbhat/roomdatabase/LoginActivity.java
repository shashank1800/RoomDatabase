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

import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.submit)
    Button submit;

    String url = "https://erp.letseduvate.com/qbox/activity_app/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        RequestQueue queue = Volley.newRequestQueue(this);

        username.setText("1705510054");
        password.setText("1705510054");

        submit.setOnClickListener(view -> {
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
                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            },
                            error -> {
                                Toast.makeText(getApplicationContext(), String.valueOf(error.networkResponse.statusCode), Toast.LENGTH_SHORT).show();
                            }) {
            };
            queue.add(jsonObjectRequest);
        });
    }
}
