package com.example.lizi.xmlparseforinternet;

/**
 * Created by Lan on 2016/2/25.
 */
public class Student {

    private String name;
    private String gender;
    private String id;
    private String classes;


    public String getClasses() {
        return classes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Student(String name, String gender, String id, String classes) {
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.classes = classes;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", classes='" + classes + '\'' +
                '}';
    }
}
