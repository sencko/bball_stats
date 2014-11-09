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
public class Event {
    private Integer s1;
    private Integer lead;
    private Integer period;
    private Integer tno;
    private Integer s2;
    private Integer gt;
    private Action desc;
    private EventType typ;

    public Integer getS1() {
        return s1;
    }

    public void setS1(Integer s1) {
        this.s1 = s1;
    }

    public Integer getLead() {
        return lead;
    }

    public void setLead(Integer lead) {
        this.lead = lead;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public Integer getS2() {
        return s2;
    }

    public void setS2(Integer s2) {
        this.s2 = s2;
    }

    public Integer getGt() {
        return gt;
    }

    public void setGt(Integer gt) {
        this.gt = gt;
    }

    public Action getDesc() {
        return desc;
    }

    public void setDesc(Action desc) {
            System.out.println(desc);
        this.desc = desc;
    }

    public EventType getTyp() {
        return typ;
    }

    public void setTyp(EventType typ) {
        this.typ = typ;
    }

    public Integer getScoring() {
        return scoring;
    }

    public void setScoring(Integer scoring) {
        this.scoring = scoring;
    }
    private Integer scoring;

    public Event() {
    }

    public Actor getActor() {
    
        return desc.getActor();//getDesc().substring(0, getDesc().lastIndexOf(','));
    }
    
}
