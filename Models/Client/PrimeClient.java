package Models.Client;

import Models.Abstract.Client;
import Models.ValueObject.Address;

public class PrimeClient extends Client {
    public PrimeClient(String name, Address address) {
        super(name, address);
    }
}
