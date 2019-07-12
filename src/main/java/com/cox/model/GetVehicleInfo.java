package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GetVehicleInfo {

    private int vehicleId;

    private int year;

    private String make;

    private String model;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int dealerId;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    @Override
    public boolean equals(Object servObj) {
        if (this == servObj) return true;
        if (servObj == null || getClass() != servObj.getClass()) return false;
        GetVehicleInfo that = (GetVehicleInfo) servObj;
        return vehicleId == that.vehicleId &&
                year == that.year &&
                dealerId == that.dealerId &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model);
    }
    
    @Override
    public String toString() {
        return "GetVehicleInfo{" +
                "vehicleId=" + vehicleId +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", dealerId=" + dealerId +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(vehicleId, year, make, model, dealerId);
    }
}
