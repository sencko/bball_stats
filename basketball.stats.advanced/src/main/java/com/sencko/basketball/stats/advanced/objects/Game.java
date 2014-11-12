/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced.objects;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author i028512
 */
public class Game {

  private static transient Game currentGame;

  /**
   * @return the currentGame
   */
  public static Game getCurrentGame() {
    return currentGame;
  }

  private Integer period;
  private List<Event> pbp;
  private String clock;
  private Object[][] leaddata;
  private Object[][] scoring;
  private HashMap<Integer, TeamStats> tm;
  private Date date;
  private String id;

  public Game() {
    currentGame = this;
  }

  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }

  public List<Event> getPbp() {
    return pbp;
  }

  public void setPbp(List<Event> pbp) {
    this.pbp = pbp;
  }

  public String getClock() {
    return clock;
  }

  public void setClock(String clock) {
    this.clock = clock;
  }

  public Object[][] getLeaddata() {
    return leaddata;
  }

  public Object[][] getScoring() {
    return scoring;
  }

  public HashMap<Integer, TeamStats> getTm() {
    return tm;
  }

  public void setTm(HashMap<Integer, TeamStats> tm) {
    this.tm = tm;
  }

  /**
   * @param leaddata the leaddata to set
   */
  public void setLeaddata(Object[][] leaddata) {
    this.leaddata = leaddata;
  }

  /**
   * @param scoring the scoring to set
   */
  public void setScoring(Object[][] scoring) {
    this.scoring = scoring;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }
}
