/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced.objects;

/**
 *
 * @author i028512
 */
public class Action {

    private Actor actor;
    private String description;

    /**
     * @return the actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param actor the actor to set
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        if (actor == null) {
            return description;
        } else {
            return actor + ", " + description;
        }
    }

}
