package com.company.util;

import java.util.HashMap;
import java.util.Random;

public class MiddleNameGenerator implements RandomStringGenerator {

    private Random random = new Random();
    private HashMap<Integer, String> alphabet = new HashMap<Integer, String>();

    public MiddleNameGenerator() {
        alphabet.put(0, "a");
        alphabet.put(1, "b");
        alphabet.put(2, "c");
        alphabet.put(2, "d");
    }

    @Override
    public String generate() {
        Integer size = random.nextInt(10) + 5;
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < size; i++) {
            res.append(alphabet.get(random.nextInt(alphabet.size())));
        }

        return res.toString();
    }
}
