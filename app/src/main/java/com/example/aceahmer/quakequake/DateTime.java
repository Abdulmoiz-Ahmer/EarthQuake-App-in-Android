package com.example.aceahmer.quakequake;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public String date(Long millisec, String format) {
        Date date = new Date(millisec);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date).toString();

    }
}
