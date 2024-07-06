package com.example.Models.Entities.Client;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EClientType;
import com.example.Models.Enums.EState;

public class SpecialClient extends Client {
    public SpecialClient(String name, EState state, EAddressPlace place) {
        super(name, state, place);
        this.type = EClientType.DefaultAndSpecial;
    }
}
