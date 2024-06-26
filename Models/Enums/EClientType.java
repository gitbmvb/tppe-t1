package Models.Enums;

public enum EClientType {
    Special(1),
    Default(2),
    DefaultAndSpecial(3),
    Prime(4),
    PrimeAndSpecial(5);

    private final int code;

    EClientType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
