package com.involuntary.revpos.models;

public class Excess {
    private String name;
    private int percentSold;

    public Excess(String name, int percentSold) {
        this.name = name;
        this.percentSold = percentSold;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPercentSold() { return percentSold; }

    public void setPercentSold(int percentSold) {this.percentSold = percentSold; }

    public int calculatePercentSold(int numItemsSold, int inventory) {
        return (numItemsSold/inventory)*100;
    }

}
