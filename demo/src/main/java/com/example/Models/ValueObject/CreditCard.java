package com.example.Models.ValueObject;

import java.util.regex.Pattern;

import com.example.Models.Exceptions.InvalidFormatException;

public class CreditCard {
    private String number;
    String regex = "^4296 13\\d{2} \\d{4} \\d{4}$";
    Pattern pattern = Pattern.compile(regex);

    public CreditCard(String number) {
        try {
            setNumber(number);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws InvalidFormatException {
        if (pattern.matcher(number).matches())
            this.number = number;
        else
            throw new InvalidFormatException("Invalid Format: " + number);
    }

}
