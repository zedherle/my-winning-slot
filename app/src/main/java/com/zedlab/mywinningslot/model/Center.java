package com.zedlab.mywinningslot.model;

import com.fasterxml.jackson.annotation.*;

import java.time.OffsetTime;
import java.util.List;

public class Center {
    @JsonProperty("center_id")
    public int getCenter_id() {
        return this.center_id;
    }

    public void setCenter_id(int center_id) {
        this.center_id = center_id;
    }

    int center_id;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @JsonProperty("name_l")
    public String getName_l() {
        return this.name_l;
    }

    public void setName_l(String name_l) {
        this.name_l = name_l;
    }

    String name_l;

    @JsonProperty("address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    @JsonProperty("address_l")
    public String getAddress_l() {
        return this.address_l;
    }

    public void setAddress_l(String address_l) {
        this.address_l = address_l;
    }

    String address_l;

    @JsonProperty("state_name")
    public String getState_name() {
        return this.state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    String state_name;

    @JsonProperty("state_name_l")
    public String getState_name_l() {
        return this.state_name_l;
    }

    public void setState_name_l(String state_name_l) {
        this.state_name_l = state_name_l;
    }

    String state_name_l;

    @JsonProperty("district_name")
    public String getDistrict_name() {
        return this.district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    String district_name;

    @JsonProperty("district_name_l")
    public String getDistrict_name_l() {
        return this.district_name_l;
    }

    public void setDistrict_name_l(String district_name_l) {
        this.district_name_l = district_name_l;
    }

    String district_name_l;

    @JsonProperty("block_name")
    public String getBlock_name() {
        return this.block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    String block_name;

    @JsonProperty("block_name_l")
    public String getBlock_name_l() {
        return this.block_name_l;
    }

    public void setBlock_name_l(String block_name_l) {
        this.block_name_l = block_name_l;
    }

    String block_name_l;

    @JsonProperty("pincode")
    public String getPincode() {
        return this.pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    String pincode;

    @JsonProperty("lat")
    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    double lat;

    @JsonProperty("long")
    public double getLong() {
        return this.longitude;
    }

    public void setLong(double longitude) {
        this.longitude = longitude;
    }

    double longitude;

    @JsonProperty("from")
    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    String from;

    @JsonProperty("to")
    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    String to;

    @JsonProperty("fee_type")
    public String getFee_type() {
        return this.fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    String fee_type;

    @JsonProperty("vaccine_fees")
    public List<VaccineFee> getVaccine_fees() {
        return this.vaccine_fees;
    }

    public void setVaccine_fees(List<VaccineFee> vaccine_fees) {
        this.vaccine_fees = vaccine_fees;
    }

    List<VaccineFee> vaccine_fees;

    @JsonProperty("sessions")
    public List<Session> getSessions() {
        return this.sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    List<Session> sessions;
}
