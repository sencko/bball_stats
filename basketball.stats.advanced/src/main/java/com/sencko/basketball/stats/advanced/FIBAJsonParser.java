/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced;

import com.sencko.basketball.stats.advanced.objects.Game;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import com.sencko.basketball.stats.advanced.objects.Action;
import com.sencko.basketball.stats.advanced.objects.Event;
import com.sencko.basketball.stats.advanced.objects.EventType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author i028512
 */
public class FIBAJsonParser {

    static final Logger logger = Logger.getLogger(FIBAJsonParser.class.getCanonicalName());

    public static void readLocation(String location) throws IOException {
        InputStream jsonInputStream = null;
        File f = new File(location.replace("/", "_").concat(".json"));
        if (f.exists()) {
            // read from cache
            logger.log(Level.FINEST, "Loading file {0} from cache", f.getCanonicalPath());
            jsonInputStream = new FileInputStream(f);
        } else {
            String prefix = "http://webcast-a.live.sportingpulseinternational.com/matches/";
            String suffix = "//data.json";
            URL url = new URL(prefix + location + suffix);
            logger.log(Level.FINEST, "Downloading file {0} from internet", url.toString());
            URLConnection connection = url.openConnection();
            //  connection.setRequestProperty("Accept-Charset", "UTF-8");
            //    System.out.println(connection.getRequestProperties());
            connection.connect();
            //   System.out.println(connection.getHeaderFields());
            jsonInputStream = connection.getInputStream();
        }
        String readString = readStreamToString(jsonInputStream);

        Game game = builder.create().fromJson(readString, Game.class);
        if (!f.exists()) {
            logger.log(Level.FINEST, "Saving file {0} to cache", f.getCanonicalPath());
            FileOutputStream fileWriter = new FileOutputStream(f);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileWriter, "UTF-8");
            try (JsonWriter jsonWriter = new JsonWriter(outputStreamWriter)) {
           //     jsonWriter.setIndent("  ");
                builder.create().toJson(game, Game.class, jsonWriter);
                jsonWriter.flush();
            }

        }
        analyzeGame(game);
    }
    static GsonBuilder builder;

    public static void main(String[] args) throws Exception {
        builder = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerAdapter()).registerTypeAdapter(String.class, new StringAdapter()).registerTypeAdapter(Action.class, new ActionAdapter()).setPrettyPrinting();

        readLocation("48965/13/13/23/68uR30BQLmzJM");
        readLocation("48965/13/15/86/78CJrCjRx5mh6");
        readLocation("48965/13/13/18/12pOKqzKs5nE");
        readLocation("48965/13/29/11/33upB2PIPn3MI");

        readLocation("48965/13/13/21/38Gqht27dPT1");
        readLocation("48965/13/15/84/999JtqK7Eao");
        readLocation("48965/13/29/07/66ODts76QU17A");
        // Game short_game = builder.create().fromJson(new InputStreamReader(FIBAJsonParser.class.getResourceAsStream("game_short.json")), Game.class);
        //   Game game = builder.create().fromJson(new InputStreamReader(FIBAJsonParser.class.getResourceAsStream("game1.json")), Game.class);
        //  analyzeGame(game);
    }

    static void analyzeGame(Game game) {
        HashSet[] playersInGameTeam = {new HashSet<String>(5), new HashSet<String>(5)};
        HashSet[] playersInRest = {new HashSet<String>(5), new HashSet<String>(5)};
        HashMap[][] plusMinusPlayers = {{new HashMap<String, Integer>(), new HashMap<String, Integer>()}, {new HashMap<String, Integer>(), new HashMap<String, Integer>()}};
        for (int i = game.getPbp().size() - 1; i >= 0; i--) {
            Event event = game.getPbp().get(i);
            if (EventType.sub.equals(event.getTyp())) {
                HashSet<String> teamSet = playersInGameTeam[event.getTno() - 1];
                HashSet<String> restSet = playersInRest[event.getTno() - 1];
                String actor = event.getActor().toString();
                if (teamSet.contains(actor)) {
                    teamSet.remove(actor);
                    restSet.add(actor);
                } else {
                    teamSet.add(actor);
                    if (restSet.contains(actor)) {
                        restSet.remove(actor);
                    } else {
                        //add plus minus on bench
                        HashMap<String, Integer> bench = plusMinusPlayers[event.getTno() - 1][1];
                        int change = (event.getTno() == 1) ? (event.getS1() - event.getS2()) : (event.getS2() - event.getS1());
                        bench.put(actor, change);
                    }
                }

                //   System.out.println(Arrays.toString(playersInGameTeam));
            } else if (i != game.getPbp().size() - 1) {
                Event previousEvent = game.getPbp().get(i + 1);
                int change = 0;
                if (previousEvent.getS1() != event.getS1()) {
                    change += event.getS1() - previousEvent.getS1();
                }
                if (previousEvent.getS2() != event.getS2()) {
                    change -= event.getS2() - previousEvent.getS2();
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

    private static String readStreamToString(InputStream jsonInputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(jsonInputStream);

        String str = new String(bytes, "UTF-8");
        return str;
    }
}
