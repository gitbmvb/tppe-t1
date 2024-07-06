package com.example.Models.Enums;

import com.example.Models.Interfaces.Discontable;

public enum EClientType implements Discontable {
    Default(1) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return value + freigth;
        }
    },
    DefaultAndSpecial(2) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return (value * 0.9) + (freigth * 0.7);
        }
    },
    Prime(3) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return value + freigth;
        }
    },
    PrimeAndSpecial(4) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return (value * 0.9) + (freigth * 0.7);
        }
    };

    private final int code;

    EClientType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
