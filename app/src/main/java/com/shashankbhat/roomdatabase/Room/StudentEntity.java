package com.shashankbhat.roomdatabase.Room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import static com.shashankbhat.roomdatabase.Utils.Constants.TABLE_NAME;

/**
 * Created by SHASHANK BHAT on 16-Mar-20.
 * <p>
 * shashankbhat1800@gmail.com
 */


@androidx.room.Entity(tableName = TABLE_NAME)
public class StudentEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "erp")
    @NonNull
    private String erp;

    @ColumnInfo(name = "student_photo")
    private String student_photo;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "second_name")
    private String second_name;

    @ColumnInfo(name = "father_name")
    private String father_name;

    @ColumnInfo(name = "role")
    private String role;

    @ColumnInfo(name = "mother_name")
    private String mother_name;

    @ColumnInfo(name = "father_mobile")
    private String father_mobile;

    public StudentEntity(String erp, String student_photo, String first_name, String second_name, String father_name, String role, String mother_name, String father_mobile) {
        this.erp = erp;
        this.student_photo = student_photo;
        this.first_name = first_name;
        this.second_name = second_name;
        this.father_name = father_name;
        this.role = role;
        this.mother_name = mother_name;
        this.father_mobile = father_mobile;
    }

    protected StudentEntity(Parcel in) {
        erp = in.readString();
        student_photo = in.readString();
        first_name = in.readString();
        second_name = in.readString();
        father_name = in.readString();
        role = in.readString();
        mother_name = in.readString();
        father_mobile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(erp);
        dest.writeString(student_photo);
        dest.writeString(first_name);
        dest.writeString(second_name);
        dest.writeString(father_name);
        dest.writeString(role);
        dest.writeString(mother_name);
        dest.writeString(father_mobile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentEntity> CREATOR = new Creator<StudentEntity>() {
        @Override
        public StudentEntity createFromParcel(Parcel in) {
            return new StudentEntity(in);
        }

        @Override
        public StudentEntity[] newArray(int size) {
            return new StudentEntity[size];
        }
    };

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getStudent_photo() {
        return student_photo;
    }

    public void setStudent_photo(String student_photo) {
        this.student_photo = student_photo;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getFather_mobile() {
        return father_mobile;
    }

    public void setFather_mobile(String father_mobile) {
        this.father_mobile = father_mobile;
    }
}
