package com.cox.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalJson {

    private boolean success;
    private String message;
    private int totalMilliseconds;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalMilliseconds() {
        return totalMilliseconds;
    }

    public void setTotalMilliseconds(int totalMilliseconds) {
        this.totalMilliseconds = totalMilliseconds;
    }

    @Override
    public boolean equals(Object servObj) {
        if (this == servObj) return true;
        if (servObj == null || getClass() != servObj.getClass()) return false;
        FinalJson that = (FinalJson) servObj;
        return success == that.success &&
                totalMilliseconds == that.totalMilliseconds &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(success, message, totalMilliseconds);
    }

    @Override
    public String toString() {
        return "FinalJson{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", totalMilliseconds=" + totalMilliseconds +
                '}';
    }
}
