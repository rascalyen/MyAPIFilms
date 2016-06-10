package com.example.yen.imdb.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonEntity implements Parcelable{

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    public PersonEntity() {
    }

    protected PersonEntity(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Creator<PersonEntity> CREATOR = new Creator<PersonEntity>() {
        @Override
        public PersonEntity createFromParcel(Parcel source) {
            return new PersonEntity(source);
        }

        @Override
        public PersonEntity[] newArray(int size) {
            return new PersonEntity[size];
        }
    };

}