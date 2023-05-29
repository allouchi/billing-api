package com.aliateck.util;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Integer max = null;
        String pattern = "50";
        String[] values = pattern.split(",");
        if (values != null) {
            List<String> listOfValues = Arrays.asList(values);
            max = listOfValues.stream().mapToInt(val -> Integer.parseInt(val))
                    .max().getAsInt();
        } else if (!pattern.equals("")) {
            max = Integer.parseInt(pattern);
        }
        System.out.println(max);

    }
}
