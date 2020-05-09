package com.store.responsebody;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "updatestatusresponsebody")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateStatusResponseBody {
    private boolean isSuccess;
    private String message;

    public UpdateStatusResponseBody() {
    }

    public UpdateStatusResponseBody(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
