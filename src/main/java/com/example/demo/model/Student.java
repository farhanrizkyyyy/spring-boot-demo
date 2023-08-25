package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {
    private String id;
    private String name;
    private String phone;
    private boolean isActive;

    public Student(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isActive = true;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public boolean getActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Student trimWhiteSpaces() {
        this.id = this.id.trim();
        this.name = this.name.trim();
        this.phone = this.phone.trim();

        return this;
    }
}
