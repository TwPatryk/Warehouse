package com.tworek.patryk.warehouse.model;

public class Jewellery extends Product {
    private String type;

    public Jewellery(String name, int size, String color, int pieces, String type) {
        super(name, size, color, pieces);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Jewellery {" +
                super.toString() +
                ", type='" + type + '\'' +
                '}';
    }
}
