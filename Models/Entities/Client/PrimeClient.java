package Models.Entities.Client;

import Models.Entities.Abstract.Client;
import Models.Enums.EPaymentMethod;
import Models.ValueObject.Address;

public class PrimeClient extends Client {

    public PrimeClient(String name, Address address) {
        super(name, address);
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
