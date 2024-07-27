package com.example.Models.ValueObject;

import com.example.Models.Enums.EState;

public class TaxesCalculator {

    public void calculateTaxes(Taxes taxes, Address address, Double value) {

        if (address.getState().getRegion() == 0) {
            taxes.setIcms(value * 0.18);

        } else {
            taxes.setIcms(value * 0.12);
            taxes.setMunicipal(value* 0.04);
        }
    }
    
    public Double calculateICMS(EState state, Double value){
        if(state.getRegion() == 0){
            return value * 0.18;
        } else {
            return value * 0.12;
        }
    }

    public Double calculateMunicipal(EState state, Double value){
        if(state.getRegion() == 0){
            return 0.0;
        } else {
            return value * 0.04;
        }
    }
}
