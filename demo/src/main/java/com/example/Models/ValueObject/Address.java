package com.example.Models.ValueObject;

import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;

public class Address {
    private EState state;
    private EAddressPlace place;

    public Address(EState state, EAddressPlace place) {
        this.state = state;
        this.place = place;
    }

    public EState getState() {
        return state;
    }

    public void setState(EState state) {
        this.state = state;
    }

    public EAddressPlace getPlace() {
        return place;
    }

    public void setPlace(EAddressPlace place) {
        this.place = place;
    }

}
