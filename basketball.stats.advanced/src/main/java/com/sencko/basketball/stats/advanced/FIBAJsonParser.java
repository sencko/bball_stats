/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced;

import com.sencko.basketball.stats.advanced.adapters.StringAdapter;
import com.sencko.basketball.stats.advanced.adapters.IntegerAdapter;
import com.sencko.basketball.stats.advanced.adapters.ActionAdapter;
import com.sencko.basketball.stats.advanced.objects.Game;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import com.sencko.basketball.stats.advanced.objects.Action;
import com.sencko.basketball.stats.advanced.objects.Event;
import com.sencko.basketball.stats.advanced.objects.EventType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author i028512
 */
public class FIBAJsonParser {

  static final Logger logger = Logger.getLogger(FIBAJsonParser.class.getCanonicalName());

  public static void readLocation(String location) throws IOException {
    String cacheName = location.replace("/", "_").concat(".json");
    Game game = getFromCache(cacheName);
    if (game == null) {
      game = readGameFromNet(location);
      addToCache(cacheName, game);
    }
    analyzeGame(game);
  }

  private static Game readGameFromNet(String location) throws IOException, MalformedURLException {
    InputStream jsonInputStream;
    String prefix = "http://webcast-a.live.sportingpulseinternational.com/matches/";
    String suffix = "//data.json";
    URL url = new URL(prefix + location + suffix);
    logger.log(Level.FINEST, "Downloading file {0} from internet", url.toString());
    URLConnection connection = url.openConnection();
    connection.connect();
    jsonInputStream = connection.getInputStream();
    try {
      return readGameFromStream(jsonInputStream);
    } finally {
      jsonInputStream.close();
    }
  }

  static GsonBuilder builder;

  public static void main(String[] args) throws Exception {
    //http://nalb.bg/%D1%81%D0%BB%D0%B5%D0%B4%D0%B5%D1%82%D0%B5-%D0%BC%D0%B0%D1%87%D0%BE%D0%B2%D0%B5%D1%82%D0%B5-%D0%BE%D1%82-%D0%BD%D0%B0%D0%BB%D0%B1-%D0%BD%D0%B0-%D0%B6%D0%B8%D0%B2%D0%BE/
    //http://nalb.bg/%D1%81%D0%BB%D0%B5%D0%B4%D0%B5%D1%82%D0%B5-%D0%B4%D0%B2%D1%83%D0%B1%D0%BE%D0%B8%D1%82%D0%B5-%D0%BE%D1%82-%D0%BD%D0%B0%D0%BB%D0%B1-%D0%BD%D0%B0-%D0%B6%D0%B8%D0%B2%D0%BE-%D0%B8-%D0%B2-%D0%BF%D0%B5%D1%82/
    //http://nalb.bg/%D1%81%D0%BB%D0%B5%D0%B4%D0%B5%D1%82%D0%B5-%D0%B4%D0%B2%D1%83%D0%B1%D0%BE%D0%B8%D1%82%D0%B5-%D0%BE%D1%82-%D0%BD%D0%B0%D0%BB%D0%B1-%D0%B8-%D0%B2-%D1%87%D0%B5%D1%82%D0%B2%D1%8A%D1%80%D1%82%D0%B8%D1%8F/

    builder = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerAdapter()).registerTypeAdapter(String.class, new StringAdapter()).registerTypeAdapter(Action.class, new ActionAdapter()).setPrettyPrinting();
    BufferedReader reader = new BufferedReader(new InputStreamReader(FIBAJsonParser.class.getResourceAsStream("/games.txt")));
    String location = null;
    while ((location = reader.readLine()) != null) {
      location = location.trim();
      try {
        readLocation(location);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    /*
     readLocation("48965/13/13/23/68uR30BQLmzJM");
     readLocation("48965/13/15/86/78CJrCjRx5mh6");
     readLocation("48965/13/13/18/12pOKqzKs5nE");
     readLocation("48965/13/29/11/33upB2PIPn3MI");

     readLocation("48965/13/13/21/38Gqht27dPT1");
     readLocation("48965/13/15/84/999JtqK7Eao");
     readLocation("48965/13/29/07/66ODts76QU17A");
     */
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
  /*
   private static String readStreamToString(InputStream jsonInputStream) throws IOException {
   byte[] bytes = IOUtils.toByteArray(jsonInputStream);

   String str = new String(bytes, "UTF-8");
   return str;
   }
   */

  private static Game getFromCache(String cacheName) throws FileNotFoundException, IOException {
    File f = new File("archive.zip");
    logger.log(Level.FINEST, "Loading file {0} from cache", cacheName);
    if (f.exists()) {
      try (ZipFile file = new ZipFile(f)) {
        ZipEntry entry = file.getEntry(cacheName);
        if (entry != null) {
          try (InputStream stream = file.getInputStream(entry)) {
            return readGameFromStream(stream);
          }
        }
      }
    }
    return null;
  }

  private static void addToCache(String cacheName, Game game) throws FileNotFoundException, UnsupportedEncodingException, IOException {
    logger.log(Level.FINEST, "Saving file {0} to cache", cacheName);
    File file = new File("archive.zip");
    File file1 = null;
    if (file.exists()) {
      //copy to archive1, return
      file1 = new File("archive1.zip");
      if (file1.exists()) {
        file1.delete();
      }
      file.renameTo(file1);
    }

    try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file))) {
      out.setLevel(9);
      // name the file inside the zip  file 
      out.putNextEntry(new ZipEntry(cacheName));
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "UTF-8");
      JsonWriter jsonWriter = new JsonWriter(outputStreamWriter);
      jsonWriter.setIndent("  ");
      builder.create().toJson(game, Game.class, jsonWriter);
      jsonWriter.flush();

      if (file1 != null) {
        try (ZipFile zipFile = new ZipFile(file1)) {
          Enumeration<? extends ZipEntry> files = zipFile.entries();
          while (files.hasMoreElements()) {
            ZipEntry entry = files.nextElement();
            try (InputStream in = zipFile.getInputStream(entry)) {
              out.putNextEntry(new ZipEntry(entry.getName()));

              IOUtils.copy(in, out);
            }
          }
        }
        file1.delete();

      }
    }
  }

  private static Game readGameFromStream(InputStream jsonInputStream) throws UnsupportedEncodingException {
    return builder.create().fromJson(new InputStreamReader(jsonInputStream, "UTF-8"), Game.class);
  }
}
