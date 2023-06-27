package com.laba.solvd.parsers;

import javax.xml.bind.annotation.adapters.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(sdf);
    }
}
