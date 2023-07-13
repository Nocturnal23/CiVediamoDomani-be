package com.progettoweb.civediamodomanibe.core.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Utils {

    public static String generateString (Long length) {
        return new Random().ints(97, 123).limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateAlphaNumericString (int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
