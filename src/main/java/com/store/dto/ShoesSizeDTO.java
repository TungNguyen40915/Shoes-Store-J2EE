package com.store.dto;

public class ShoesSizeDTO {
    private int StockId;
    private String sizeName;

    public ShoesSizeDTO() {
    }

    public ShoesSizeDTO(int stockId, String sizeName) {
        StockId = stockId;
        this.sizeName = sizeName;
    }

    public int getStockId() {
        return StockId;
    }

    public void setStockId(int stockId) {
        StockId = stockId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
