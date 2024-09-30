package com.vietqr.org.dto;

public class PageDTO {
    private int page;
    private int size;
    private int totalPage;
    private int totalElement;

    public PageDTO() {
        this.page = 1;
        this.size = 20;
        this.totalPage = 1;
        this.totalElement = 0;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }
}
