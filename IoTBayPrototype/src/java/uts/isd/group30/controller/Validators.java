package uts.isd.group30.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators implements Serializable {

    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private String passwordPattern = "[a-z0-9]{4,}";
    private String creditCardNumberPattern = "([0-9]{16})";
    private String creditCardExpiryPattern = "([0-9]{2})(/)([0-9]{2})";
    private String creditCardCVCPattern = "([0-9]{3})";
    private String phonePattern = "([0-9]{10})";

    public Validators() {
    }

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();

    }

    public boolean checkEmpty(String email, String password) {

        return email.isEmpty() || password.isEmpty();

    }

    public boolean validateEmail(String email) {

        return validate(emailPattern, email);

    }

    public boolean validateName(String name) {

        return validate(namePattern, name);

    }

    public boolean validatePassword(String password) {

        return validate(passwordPattern, password);
    }

    public boolean validatePhoneNumber(String phoneNumber) {

        return validate(phonePattern, phoneNumber);
    }

    public boolean validateCreditCardNumber(String creditCardNumber) {
        return validate(creditCardNumberPattern, creditCardNumber);
    }

    public boolean validateCreditCardExpiry(String creditCardExpiry) {
        return validate(creditCardExpiryPattern, creditCardExpiry);
    }

    public boolean validateCreditCardCVC(String creditCardCVC) {
        return validate(creditCardCVCPattern, creditCardCVC);
    }
}
