package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import java.util.Set;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GetVechileIDJSON {

    Set<Integer> vehicleIds;

    public Set<Integer> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(Set<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
    
    @Override
    public String toString() {
        return "GetVechileIDJSON{" +
                "vehicleIds=" + vehicleIds +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(vehicleIds);
    }
    
    @Override
    public boolean equals(Object servObj) {
        if (this == servObj) return true;
        if (servObj == null || getClass() != servObj.getClass()) return false;
        GetVechileIDJSON that = (GetVechileIDJSON) servObj;
        return Objects.equals(vehicleIds, that.vehicleIds);
    }
}
