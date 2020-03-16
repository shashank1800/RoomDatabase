package com.shashankbhat.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shashankbhat.roomdatabase.Room.StudentEntity;
import com.shashankbhat.roomdatabase.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private StudentEntity studentEntity;

    @BindView(R.id.details)
    TextView details;

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            studentEntity = bundle.getParcelable(Constants.STUDENT_OBJ);

            details.setText("ERP : "+ studentEntity.getErp());
            details.append("\nFirst Name : "+ studentEntity.getFirst_name());
            details.append("\nSecond Name : "+ studentEntity.getSecond_name());
            details.append("\nRole : "+studentEntity.getRole());
            details.append("\nMother Name : "+studentEntity.getMother_name());
            details.append("\nFather Mobile : "+studentEntity.getFather_mobile());

            Glide.with(this).load(studentEntity.getStudent_photo()).into(imageView);
        }

    }
}
