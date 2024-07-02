package com.example.Models.Entities.Client;

import com.example.Models.Entities.Abstract.Client;
import com.example.Models.Enums.EClientType;
import com.example.Models.Enums.EPaymentMethod;
import com.example.Models.ValueObject.Address;

public class PrimeClient extends Client {

    public PrimeClient(String name, Address address) {
        super(name, address);
        this.type = EClientType.Prime;
    }

    public void ManipulateCashback(EPaymentMethod method, Double value) {
        if (method == EPaymentMethod.CreditCard)
            this.addCashback(value * 0.05);
        else
            this.addCashback(value * 0.03);
    }

    @Override
    public Double addCashback(Double value) {
        this.cashBack += value;
        return this.cashBack;
    }

    @Override
    public Double discountCashback(Double value) {
        this.cashBack -= value;
        return this.cashBack;
    }
}
