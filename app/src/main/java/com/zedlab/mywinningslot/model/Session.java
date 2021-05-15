package com.zedlab.mywinningslot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Session {
    @JsonProperty("session_id")
    public String getSession_id() {
        return this.session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    String session_id;

    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    @JsonProperty("available_capacity")
    public int getAvailable_capacity() {
        return this.available_capacity;
    }

    public void setAvailable_capacity(int available_capacity) {
        this.available_capacity = available_capacity;
    }

    int available_capacity;

    @JsonProperty("available_capacity_dose1")
    public int getAvailable_capacity_dose1() {
        return this.available_capacity_dose1;
    }

    public void setAvailable_capacity_dose1(int available_capacity_dose1) {
        this.available_capacity_dose1 = available_capacity_dose1;
    }

    int available_capacity_dose1;

    @JsonProperty("available_capacity_dose2")
    public int getAvailable_capacity_dose2() {
        return this.available_capacity_dose2;
    }

    public void setAvailable_capacity_dose2(int available_capacity_dose2) {
        this.available_capacity_dose2 = available_capacity_dose2;
    }

    int available_capacity_dose2;

    @JsonProperty("min_age_limit")
    public int getMin_age_limit() {
        return this.min_age_limit;
    }

    public void setMin_age_limit(int min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    int min_age_limit;

    @JsonProperty("vaccine")
    public String getVaccine() {
        return this.vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    String vaccine;

    @JsonProperty("slots")
    public List<String> getSlots() {
        return this.slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    List<String> slots;
}
