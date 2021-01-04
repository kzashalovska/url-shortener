package com.smartfoxpro.urlshortener.utils;

import org.springframework.stereotype.Component;

@Component
public class BaseAlgorithm {

    private static final String ALLOWED_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(ALLOWED_SYMBOLS.charAt(0));
        }

        while (input > 0) {
            encodedString.append(ALLOWED_SYMBOLS.charAt((int)input % ALLOWED_SYMBOLS.length()));
            input = input / ALLOWED_SYMBOLS.length();
        }

        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;

        int decoded = 0;

        //counter is used to avoid reversing input string
        int counter = 1;
        for (char character : characters) {
            decoded += ALLOWED_SYMBOLS.indexOf(character) * Math.pow(ALLOWED_SYMBOLS.length(), length - counter);
            counter++;
        }

        return decoded;
    }
}
