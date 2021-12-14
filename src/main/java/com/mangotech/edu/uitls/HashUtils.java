package com.mangotech.edu.uitls;


public class HashUtils {
    public HashUtils() {
    }

    public static String generateSalt() {
        return RandomStringUtils.getInstance().nextString();
    }

    public static String generateSalt(int number) {
        return (new RandomStringUtils(number)).nextString();
    }
}
