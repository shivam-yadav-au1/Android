package com.example.shivam.activity.com.example.shivam.dataobject;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String name;
    private String address;
    private String proffession;


    public Person(String name,String address,String proffession){
        this.name = name;
        this.address = address;
        this.proffession = proffession;
    }

    protected Person(Parcel in) {
        name = in.readString();
        address = in.readString();
        proffession = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }



    public String getAddress() {
        return address;
    }



    public String getProffession() {
        return proffession;
    }

    @Override
    public String toString() {
        return "Name =>"+this.name+" Address =>"+this.address+" Proffession =>"+this.proffession;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(proffession);
    }
}
