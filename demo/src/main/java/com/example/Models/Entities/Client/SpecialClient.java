package com.example.Models.Entities.Client;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Enums.EClientType;
import com.example.Models.ValueObject.Address;

public class SpecialClient extends Client {
    public SpecialClient(String name, Address address) {
        super(name, address);
        this.type = EClientType.DefaultAndSpecial;
    }
}
