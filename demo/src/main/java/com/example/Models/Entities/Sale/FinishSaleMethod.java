package com.example.Models.Entities.Sale;

import com.example.Models.Enums.EPaymentMethod;

public class FinishSaleMethod {
    private Sale mainSale;
    private Double totalValue = 0.0;
    private Double freight = 0.0;

    public FinishSaleMethod(Sale mainSale) {
        this.mainSale = mainSale;
    }

    public Double finish() {
        this.totalValue = this.mainSale.sumProductsPriceAndTaxes();
        this.freight = this.mainSale.calculateFreigth();
        totalValue = this.mainSale.getClient().getType().applyDiscount(this.totalValue, this.freight);
        if (this.mainSale.getPaymentMethod() == EPaymentMethod.CreditCard)
            this.totalValue *= 0.9;
        this.mainSale.cashBackManipulation(this.totalValue);
        return totalValue;
    }
}
