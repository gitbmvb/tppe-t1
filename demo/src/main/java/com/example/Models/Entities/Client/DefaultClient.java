package com.example.Models.Entities.Client;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Enums.EAddressPlace;
import com.example.Models.Enums.EState;

public class DefaultClient extends Client {

    public DefaultClient(String name, EState state, EAddressPlace place) {
        super(name, state, place);
    }
}
