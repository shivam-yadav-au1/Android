package com.example.shivam.recyclerview.dataobject;

import java.util.ArrayList;

public class CustomPojo {

    private String name;
    private String time,content;
    private ArrayList<CustomPojo> customPojos = new ArrayList<CustomPojo>();

    public CustomPojo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
