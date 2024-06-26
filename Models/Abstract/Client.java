package Models.Abstract;

import Models.ValueObject.Address;

public abstract class Client {
    private String name;
    private Address address;

    public Client(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Double Discount(Double PartialValue) {
        return PartialValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
