package Models.Client;

import Models.Abstract.Client;
import Models.ValueObject.Address;

public class DefaultClient extends Client {

    public DefaultClient(String name, Address address) {
        super(name, address);
    }
}
