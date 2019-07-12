package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerOutput {

    private int dealerId;

    private String name;

    private List<GetVehicleInfo> vehicles = new LinkedList<>();



    public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<GetVehicleInfo> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<GetVehicleInfo> vehicles) {
		this.vehicles = vehicles;
	}

	public  boolean  addVech(GetVehicleInfo getVehicleInfo) {

        return getVehicles().add(getVehicleInfo);
    }

    @Override
    public boolean equals(Object serevObj) {
        if (this == serevObj) return true;
        if (serevObj == null || getClass() != serevObj.getClass()) return false;
        DealerOutput dopt = (DealerOutput) serevObj;
        return dealerId == dopt.dealerId &&
                Objects.equals(name, dopt.name) &&
                Objects.equals(vehicles, dopt.vehicles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dealerId, name, vehicles);
    }

    @Override
    public String toString() {
        return "DealerAnswer{" +
                "dealerId=" + dealerId +
                ", name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
