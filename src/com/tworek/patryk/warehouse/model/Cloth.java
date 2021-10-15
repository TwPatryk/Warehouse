package com.tworek.patryk.warehouse.model;

public class Cloth extends Product {
    private String length;


    public Cloth(String name, int size, String color, int pieces, String length) {
        super(name, size, color, pieces);
        this.length = length;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Cloth{ " +
                super.toString() +
                ", length='" + length + '\'' +
                '}';
    }
}
