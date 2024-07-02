package com.example.Models.ValueObject;

public class Taxes {
    private Double icms = 0.0;
    private Double municipal = 0.0;

    public Taxes() {
    }

    public void calculateTaxes(Address address, Double value) {
        if (address.getState().getRegion() == 0) {
            this.icms = value * 0.18;

        } else {
            this.icms = value * 0.12;
            this.municipal = value * 0.04;
        }
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public Double getMunicipal() {
        return municipal;
    }

    public void setMunicipal(Double municipal) {
        this.municipal = municipal;
    }

}
