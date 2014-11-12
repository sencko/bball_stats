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
import com.sencko.basketball.stats.advanced.objects.Action;
import com.sencko.basketball.stats.advanced.objects.Actor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author i028512
 */
public class ActionAdapter extends TypeAdapter<Action> {

  static Pattern fullAction = Pattern.compile("(\\d+), (.+), (.+)");

  @Override
  public void write(JsonWriter writer, Action t) throws IOException {

    if (t == null) {
      writer.nullValue();
    } else {
      writer.value(t.toString());
    }

  }

  @Override
  public Action read(JsonReader reader) throws IOException {
    switch (reader.peek()) {
      case NULL: {
        reader.nextNull();
        return null;
      }
      case STRING: {
        String stringValue = reader.nextString().trim();

        if (stringValue.length() == 0) {
          return null;
        } else {
          Matcher matcher = fullAction.matcher(stringValue);
          if (matcher.matches()) {
            Actor actor = new Actor();
            actor.setName(matcher.group(2));
            actor.setNumber(Integer.parseInt(matcher.group(1)));
            Action action = new Action();
            action.setActor(actor);
            String desc = matcher.group(3);
            action.setDescription(desc);
            return action;
          } else {
            Action action = new Action();
            action.setDescription(stringValue);
            return action;
          }
        }
      }
    }
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
