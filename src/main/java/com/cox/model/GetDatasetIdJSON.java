package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDatasetIdJSON {
    private String datasetId;

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    @Override
    public String toString() {
        return "GetDatasetIdJSON{" +
                "datasetId='" + datasetId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object servObj) {
        if (this == servObj) return true;
        if (servObj == null || getClass() != servObj.getClass()) return false;
        GetDatasetIdJSON dataSetId = (GetDatasetIdJSON) servObj;
        return Objects.equals(datasetId, dataSetId.datasetId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datasetId);
    }
}
