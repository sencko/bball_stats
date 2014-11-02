/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author i028512
 */
public class FIBAJsonParser {

    public static void readLocation(String location) throws IOException {
        String prefix = "http://webcast-a.live.sportingpulseinternational.com/matches/";
        String suffix = "//data.json";
        URL url = new URL(prefix + location + suffix);
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream jsonInputStream = connection.getInputStream();
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerAdapter());
        Game game = builder.create().fromJson(new InputStreamReader(jsonInputStream), Game.class);
        analyzeGame(game);
    }

    public static void main(String[] args) throws Exception {
        readLocation("48965/13/13/23/68uR30BQLmzJM");
        readLocation("48965/13/15/86/78CJrCjRx5mh6");
        //  readLocation("48965/13/13/18/12pOKqzKs5nE");
        readLocation("48965/13/29/11/33upB2PIPn3MI");

        readLocation("48965/13/13/21/38Gqht27dPT1");
        readLocation("48965/13/15/84/999JtqK7Eao");
        readLocation("48965/13/29/07/66ODts76QU17A");
        // Game short_game = builder.create().fromJson(new InputStreamReader(FIBAJsonParser.class.getResourceAsStream("game_short.json")), Game.class);
        //   Game game = builder.create().fromJson(new InputStreamReader(FIBAJsonParser.class.getResourceAsStream("game1.json")), Game.class);
        //  analyzeGame(game);
    }

    private static class IntegerAdapter extends TypeAdapter<Integer> {

        static Pattern pattern = Pattern.compile("(\\d\\d):(\\d\\d)");

        public IntegerAdapter() {
        }

        @Override
        public void write(JsonWriter writer, Integer t) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Integer read(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case NULL: {
                    reader.nextNull();
                    break;
                }
                case NUMBER: {
                    return reader.nextInt();
                }
                case STRING: {
                    String stringValue = reader.nextString().trim();
                    if (stringValue.length() == 0) {
                        return null;
                    } else {
                        Matcher matcher = pattern.matcher(stringValue);
                        if (matcher.matches()) {
                           Integer ret =  Integer.parseInt(matcher.group(1)) * 60 + Integer.parseInt(matcher.group(2));
                         //  System.out.println(stringValue +" \t" + ret);
                             return(ret);
                        }
                    }
                    return Integer.parseInt(stringValue);
                }
            }

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    static void analyzeGame(Game game) {
        HashSet[] playersInGameTeam = {new HashSet<String>(5), new HashSet<String>(5)};
        HashSet[] playersInRest = {new HashSet<String>(5), new HashSet<String>(5)};
        HashMap[][] plusMinusPlayers = {{new HashMap<String, Integer>(), new HashMap<String, Integer>()}, {new HashMap<String, Integer>(), new HashMap<String, Integer>()}};
        for (int i = game.pbp.size() - 1; i >= 0; i--) {
            Game.Event event = game.pbp.get(i);
            if ("sub".equals(event.typ)) {
                HashSet<String> teamSet = playersInGameTeam[event.tno - 1];
                HashSet<String> restSet = playersInRest[event.tno - 1];
                String actor = event.getActor();
                if (teamSet.contains(actor)) {
                    teamSet.remove(actor);
                    restSet.add(actor);
                } else {
                    teamSet.add(actor);
                    if (restSet.contains(actor)) {
                        restSet.remove(actor);
                    } else {
                        //add plus minus on bench
                        HashMap<String, Integer> bench = plusMinusPlayers[event.tno - 1][1];
                        int change = (event.tno == 1) ? (event.s1 - event.s2) : (event.s2 - event.s1);
                        bench.put(actor, change);
                    }
                }

                //   System.out.println(Arrays.toString(playersInGameTeam));
            } else if (i != game.pbp.size() - 1) {
                Game.Event previousEvent = game.pbp.get(i + 1);
                int change = 0;
                if (previousEvent.s1 != event.s1) {
                    change += event.s1 - previousEvent.s1;
                }
                if (previousEvent.s2 != event.s2) {
                    change -= event.s2 - previousEvent.s2;
                }
                for (int j = 0; j < 2; j++) {
                    HashSet<String> teamSet = playersInGameTeam[j];
                    for (String player : teamSet) {
                        HashMap<String, Integer> prev = plusMinusPlayers[j][0];
                        Integer previous = prev.get(player);
                        if (previous == null) {
                            previous = 0;
                        }
                        previous = previous + change;
                        prev.put(player, previous);
                    }

                    HashSet<String> restSet = playersInRest[j];
                    for (String player : restSet) {
                        HashMap<String, Integer> prev = plusMinusPlayers[j][1];
                        Integer previous = prev.get(player);
                        if (previous == null) {
                            previous = 0;
                        }
                        previous = previous + change;
                        prev.put(player, previous);
                    }

                    change = -change;
                }
            }
        }
        //     System.out.println(Arrays.deepToString(plusMinusPlayers));

        for (int i = 0; i < 2; i++) {
            HashMap<String, Integer> board = plusMinusPlayers[i][0];
            HashMap<String, Integer> bench = plusMinusPlayers[i][1];
            int checkSum = 0;
            for (String name : board.keySet()) {
                int plusS = board.get(name);
                int plusB = bench.get(name);
                int total = plusS - plusB;
                System.out.printf("%20s\t%4d\t%4d\t%4d\n", name, plusS, plusB, total);
                checkSum += total;
            }

            System.out.println(checkSum + "----------------------------------------");
        }
    }
}
