package Models.ValueObject;

import Models.Enums.EAddressPlace;

public class Address {
    private String state;
    private EAddressPlace place;

    public Address(String state, EAddressPlace place) {
        this.state = state;
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public EAddressPlace getPlace() {
        return place;
    }

    public void setPlace(EAddressPlace place) {
        this.place = place;
    }

}
