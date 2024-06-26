package Models.Client;

import Models.Abstract.Client;
import Models.ValueObject.Address;

public class SpecialClient extends Client {
    public SpecialClient(String name, Address address) {
        super(name, address);
    }
}
