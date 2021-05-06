package com.example.diario.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Day implements Serializable {
    private int id;
    private String dayMonth;
    private String dayWeek;
    private String annotation;

    public Day(String dayMonth, String dayWeek, String annotation) {
        this.dayMonth = dayMonth;
        this.dayWeek = dayWeek;
        this.annotation = annotation;
    }

    public Day() {

    }

    public void setDayMonth(String dayMonth) {
        this.dayMonth = dayMonth;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public String getAnnotation() {
        return annotation;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return dayMonth +" "+ dayWeek;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
