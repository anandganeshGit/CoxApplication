package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputToPost {

    private Collection<DealerOutput> dealers;

    public Collection<DealerOutput>  getDealers() {
        return dealers;
    }

    public void setDealers(Collection<DealerOutput>  dealers) {
        this.dealers = dealers;
    }
    
    @Override
    public int hashCode() {

        return Objects.hash(dealers);
    }

    @Override
    public boolean equals(Object servObj) {
        if (this == servObj) return true;
        if (servObj == null || getClass() != servObj.getClass()) return false;
        OutputToPost answer = (OutputToPost) servObj;
        return Objects.equals(dealers, answer.dealers);
    }

    @Override
    public String toString() {
        return "OutputToPost{" +
                "dealers=" + dealers +
                '}';
    }
}
