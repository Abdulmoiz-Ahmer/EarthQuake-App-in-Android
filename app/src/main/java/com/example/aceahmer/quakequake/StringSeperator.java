package com.example.aceahmer.quakequake;

public class StringSeperator {
    public String[] seperate(String place) {
        String array[] = new String[2];
        if (place.contains("of")) {
            array = place.split("of");
            array[0] += "Of";
            return array;
        } else {
            array[0] = "Near Of";
            array[1] = place;
            return array;
        }
    }
}
