package com.example.Models.Enums;

public enum EState {
    AC("Acre", 1),
    AL("Alagoas", 2),
    AP("Amapá", 1),
    AM("Amazonas", 1),
    BA("Bahia", 2),
    CE("Ceará", 2),
    DF("Distrito Federal", 0),
    ES("Espírito Santo", 3),
    GO("Goiás", 4),
    MA("Maranhão", 1),
    MT("Mato Grosso", 4),
    MS("Mato Grosso do Sul", 4),
    MG("Minas Gerais", 3),
    PA("Pará", 1),
    PB("Paraíba", 2),
    PR("Paraná", 5),
    PE("Pernambuco", 2),
    PI("Piauí", 2),
    RJ("Rio de Janeiro", 3),
    RN("Rio Grande do Norte", 2),
    RS("Rio Grande do Sul", 5),
    RO("Rondônia", 1),
    RR("Roraima", 1),
    SC("Santa Catarina", 5),
    SP("São Paulo", 4),
    SE("Sergipe", 2),
    TO("Tocantins", 1);

    private String name;
    private final int region;

    EState(String name, int region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public int getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return name + " (" + region + ")";
    }
}
