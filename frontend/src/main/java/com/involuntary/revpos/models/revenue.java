package com.involuntary.revpos.models;

public class revenue {
    private String date;
    private Double revenue;

    public revenue(String date, double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public String getId() {
        return date;
    }

    /**
     * @param id id to set (integer)
     */
    public void setId(String date) {
        this.date = date;
    }


    public Double getRevenue() { return revenue; }

    public void setRevenue(Double revenue) { this.revenue = revenue; }

}
