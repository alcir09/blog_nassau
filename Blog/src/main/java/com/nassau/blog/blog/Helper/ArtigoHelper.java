package com.nassau.blog.blog.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ArtigoHelper {

    public static Date converterStringParamDate(String data){

        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

        try {
            if (Objects.isNull(data)) {
                return new Date();
            }

            return formato.parse(data);

        } catch(ParseException e) {
            return new Date();
        }

    }
    
}
