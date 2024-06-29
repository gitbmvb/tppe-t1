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

    public Double calculateFreigth() {
        if (place == EAddressPlace.Capital) {
            if (state.getRegion() == 0)
                return 5.0;
            if (state.getRegion() == 1)
                return 20.0;
            if (state.getRegion() == 2)
                return 15.0;
            if (state.getRegion() == 3)
                return 7.0;
            if (state.getRegion() == 4 || state.getRegion() == 5)
                return 10.0;
        }
        if (place == EAddressPlace.Inside) {
            if (state.getRegion() == 1)
                return 25.0;
            if (state.getRegion() == 2)
                return 18.0;
            if (state.getRegion() == 3)
                return 10.0;
            if (state.getRegion() == 4 || state.getRegion() == 5)
                return 13.0;
        }
        return 0.0;
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
