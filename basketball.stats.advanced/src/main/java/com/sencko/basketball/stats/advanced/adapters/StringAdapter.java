/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 *
 * @author i028512
 */
public class StringAdapter extends TypeAdapter<String> {

  @Override
  public void write(JsonWriter writer, String t) throws IOException {
    if (t == null) {
      writer.nullValue();
    } else {
      writer.value(t);
    }
  }

  @Override
  public String read(JsonReader reader) throws IOException {
    switch (reader.peek()) {
      case NULL: {
        reader.nextNull();
        return null;
      }
      case NUMBER: {
        return String.valueOf(reader.nextInt());
      }
      case STRING: {
        String stringValue = reader.nextString().trim();
        if (stringValue.length() == 0) {
          return null;
        } else {
          //    System.out.println(stringValue);
          return stringValue;
        }
      }
    }
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
