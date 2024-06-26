package Models.Enums;

import Models.Interfaces.Discontable;

public enum EClientType implements Discontable {
    Special(1) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return value + freigth;
        }
    },
    Default(2) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return value + freigth;
        }
    },
    DefaultAndSpecial(3) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return null;
        }
    },
    Prime(4) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return null;
        }
    },
    PrimeAndSpecial(5) {
        @Override
        public Double applyDiscount(Double value, Double freigth) {
            return null;
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
