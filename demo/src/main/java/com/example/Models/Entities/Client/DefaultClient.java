package com.example.Models.Entities.Client;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Entities.Cart.Cart;
import com.example.Models.ValueObject.Address;

public class DefaultClient extends Client {

    public DefaultClient(String name, Address address, Cart cart) {
        super(name, address, cart);
    }
}
