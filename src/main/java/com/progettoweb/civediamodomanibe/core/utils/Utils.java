package com.progettoweb.civediamodomanibe.core.utils;

import java.util.Random;

public class Utils {

    public static String generateString (Long length) {
        return new Random().ints(97, 123).limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
