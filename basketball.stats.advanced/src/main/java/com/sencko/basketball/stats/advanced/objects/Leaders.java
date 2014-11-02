/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced.objects;

import java.util.HashMap;

/**
 *
 * @author i028512
 */
public class Leaders {
    private HashMap<Integer, PlayerShort> blocks;
    private HashMap<Integer, PlayerShort> assists;
    private HashMap<Integer, PlayerShort> steals;
    private HashMap<Integer, PlayerShort> rebounds;
    private HashMap<Integer, PlayerShort> scoring;

    public Leaders() {
    }

    public HashMap<Integer, PlayerShort> getBlocks() {
        return blocks;
    }

    public void setBlocks(HashMap<Integer, PlayerShort> blocks) {
        this.blocks = blocks;
    }

    public HashMap<Integer, PlayerShort> getAssists() {
        return assists;
    }

    public void setAssists(HashMap<Integer, PlayerShort> assists) {
        this.assists = assists;
    }

    public HashMap<Integer, PlayerShort> getSteals() {
        return steals;
    }

    public void setSteals(HashMap<Integer, PlayerShort> steals) {
        this.steals = steals;
    }

    public HashMap<Integer, PlayerShort> getRebounds() {
        return rebounds;
    }

    public void setRebounds(HashMap<Integer, PlayerShort> rebounds) {
        this.rebounds = rebounds;
    }

    public HashMap<Integer, PlayerShort> getScoring() {
        return scoring;
    }

    public void setScoring(HashMap<Integer, PlayerShort> scoring) {
        this.scoring = scoring;
    }
    
}
