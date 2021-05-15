package com.zedlab.mywinningslot.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class Centers {
    @JsonProperty("centers")
    public List<Center> getCenters() {
        return this.centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

    List<Center> centers;
}
