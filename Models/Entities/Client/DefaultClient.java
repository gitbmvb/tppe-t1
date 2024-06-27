package Models.Entities.Client;

import Models.Entities.Abstract.Client;
import Models.ValueObject.Address;

public class DefaultClient extends Client {

    public DefaultClient(String name, Address address) {
        super(name, address);
    }
}
