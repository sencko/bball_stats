/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author i028512
 */
class IntegerAdapter extends TypeAdapter<Integer> {
    static Pattern pattern = Pattern.compile("(\\d\\d):(\\d\\d)");

    public IntegerAdapter() {
    }

    @Override
    public void write(JsonWriter writer, Integer t) throws IOException {
        if (t == null) {
            writer.nullValue();
        } else {
            writer.value(t);
        }
    }

    @Override
    public Integer read(JsonReader reader) throws IOException {
        switch (reader.peek()) {
            case NULL:
                {
                    reader.nextNull();
                    break;
                }
            case NUMBER:
                {
                    return reader.nextInt();
                }
            case STRING:
                {
                    String stringValue = reader.nextString().trim();
                    if (stringValue.length() == 0) {
                        return null;
                    } else {
                        Matcher matcher = pattern.matcher(stringValue);
                        if (matcher.matches()) {
                            Integer ret = Integer.parseInt(matcher.group(1)) * 60 + Integer.parseInt(matcher.group(2));
                            //  System.out.println(stringValue +" \t" + ret);
                            return ret;
                        }
                    }
                    return Integer.parseInt(stringValue);
                }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
