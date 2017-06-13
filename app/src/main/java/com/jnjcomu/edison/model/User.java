package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi <ruto1924@gmail.com>
 * @since 2017-05-06
 */

public class User {
    private int id;
    private String name;
    private int grade;
    private int clazz;
    private int number;

    public User() {
    }

    public User(int id, String name, int grade, int clazz, int number) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.clazz = clazz;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
