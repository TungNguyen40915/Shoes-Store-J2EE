package com.store.security;


import java.util.Date;

public class UnauthorizedResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public UnauthorizedResponse(String path) {
        this.path = path;
        this.timestamp = new Date();
        this.error= "Unauthorized";
        this.message = "Error -> Unauthorized";
        this.status = 401;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
