package com.cox.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSet {
    private String datasetId;
    private OutputToPost op;

    @Override
    public String toString() {
        return "DataSet{" +
                "datasetId='" + datasetId + '\'' +
                ", op=" + op +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return Objects.equals(datasetId, dataSet.datasetId) &&
                Objects.equals(op, dataSet.op);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datasetId, op);
    }

    public String getDatasetId() {

        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    
    
    public OutputToPost getOp() {
		return op;
	}

	public void setOp(OutputToPost op) {
		this.op = op;
	}
}
