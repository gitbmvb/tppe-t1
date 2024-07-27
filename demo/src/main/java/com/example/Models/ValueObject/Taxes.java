package com.example.Models.ValueObject;

import com.example.Models.Enums.EState;

public class Taxes {
    private Double icms = 0.0;
    private Double municipal = 0.0;

    public Taxes() {
        
    } 

    public void calculateTaxes(Address address, Double value) {
        TaxesCalculator calculator = new TaxesCalculator();
        calculator.calculateTaxes(this, address, value);
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
