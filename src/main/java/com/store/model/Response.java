package com.store.model;

public class Response {
    private String code;
    private String msg;
    private String data;
    private int totalRecords;

    public Response() {
    }

    public Response(String code, String msg, String data, int totalRecords) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.totalRecords = totalRecords;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
