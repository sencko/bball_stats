/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced.objects;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author i028512
 */
public class TeamStats {
    private Integer score; // "74",
    private Integer tot_reb_def; // "31",
    private Integer p1_score; // "17",
    private Integer ot_score; // "",
    private Integer tot_fou_fo; // "18"
    private Integer tot_fg_m; // "29",
    private Integer full_score; // "74",
    private Integer tot_mins; // "",
    private Integer tot_ass; // "8",
    private Integer tot_ft_pc; // "54",
    private Integer p2_score; // "18",
    private Integer tot_fbp; // "0",
    private Integer tot_ft_m; // "15",
    private String coach; // "",
    private Integer tot_ft_a; // "28",
    private Integer tot_fg3_m; // "1",
    private Integer touts; // "1",
    private Integer tot_scp; // "16",
    private Integer p4_score; // "21",
    private Integer fouls; // "6",
    private Integer tot_fou_pf; // "17",
    private Integer tot_pft; // "20",
    private Integer tot_bp; // "2",
    private Integer tot_blk_bl; // "4",
    private Integer tot_reb_tot; // "52",
    private Integer tot_pts; // "74",
    private Integer tot_stl; // "14",
    private Integer tot_blk_bo; // "2",
    private Integer tot_fg_pc; // "38",
    private Integer tot_pip; // "44",
    private Integer tot_fg3_pc; // "4",
    private Integer tot_fg3_a; // "23",
    private String asst1; // "",
    private Integer tot_tov; // "23",
    private Integer tot_fg_a; // "77",
    private Integer p3_score; // "18",
    private String asst2; // "",
    private Integer tot_reb_off; // "21",
    private List<Shot> shot;
    private Leaders lds;
    private HashMap<Integer, PlayerStat> pl;

    public TeamStats() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTot_reb_def() {
        return tot_reb_def;
    }

    public void setTot_reb_def(Integer tot_reb_def) {
        this.tot_reb_def = tot_reb_def;
    }

    public Integer getP1_score() {
        return p1_score;
    }

    public void setP1_score(Integer p1_score) {
        this.p1_score = p1_score;
    }

    public Integer getOt_score() {
        return ot_score;
    }

    public void setOt_score(Integer ot_score) {
        this.ot_score = ot_score;
    }

    public Integer getTot_fou_fo() {
        return tot_fou_fo;
    }

    public void setTot_fou_fo(Integer tot_fou_fo) {
        this.tot_fou_fo = tot_fou_fo;
    }

    public Integer getTot_fg_m() {
        return tot_fg_m;
    }

    public void setTot_fg_m(Integer tot_fg_m) {
        this.tot_fg_m = tot_fg_m;
    }

    public Integer getFull_score() {
        return full_score;
    }

    public void setFull_score(Integer full_score) {
        this.full_score = full_score;
    }

    public Integer getTot_mins() {
        return tot_mins;
    }

    public void setTot_mins(Integer tot_mins) {
        this.tot_mins = tot_mins;
    }

    public Integer getTot_ass() {
        return tot_ass;
    }

    public void setTot_ass(Integer tot_ass) {
        this.tot_ass = tot_ass;
    }

    public Integer getTot_ft_pc() {
        return tot_ft_pc;
    }

    public void setTot_ft_pc(Integer tot_ft_pc) {
        this.tot_ft_pc = tot_ft_pc;
    }

    public Integer getP2_score() {
        return p2_score;
    }

    public void setP2_score(Integer p2_score) {
        this.p2_score = p2_score;
    }

    public Integer getTot_fbp() {
        return tot_fbp;
    }

    public void setTot_fbp(Integer tot_fbp) {
        this.tot_fbp = tot_fbp;
    }

    public Integer getTot_ft_m() {
        return tot_ft_m;
    }

    public void setTot_ft_m(Integer tot_ft_m) {
        this.tot_ft_m = tot_ft_m;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getTot_ft_a() {
        return tot_ft_a;
    }

    public void setTot_ft_a(Integer tot_ft_a) {
        this.tot_ft_a = tot_ft_a;
    }

    public Integer getTot_fg3_m() {
        return tot_fg3_m;
    }

    public void setTot_fg3_m(Integer tot_fg3_m) {
        this.tot_fg3_m = tot_fg3_m;
    }

    public Integer getTouts() {
        return touts;
    }

    public void setTouts(Integer touts) {
        this.touts = touts;
    }

    public Integer getTot_scp() {
        return tot_scp;
    }

    public void setTot_scp(Integer tot_scp) {
        this.tot_scp = tot_scp;
    }

    public Integer getP4_score() {
        return p4_score;
    }

    public void setP4_score(Integer p4_score) {
        this.p4_score = p4_score;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }

    public Integer getTot_fou_pf() {
        return tot_fou_pf;
    }

    public void setTot_fou_pf(Integer tot_fou_pf) {
        this.tot_fou_pf = tot_fou_pf;
    }

    public Integer getTot_pft() {
        return tot_pft;
    }

    public void setTot_pft(Integer tot_pft) {
        this.tot_pft = tot_pft;
    }

    public Integer getTot_bp() {
        return tot_bp;
    }

    public void setTot_bp(Integer tot_bp) {
        this.tot_bp = tot_bp;
    }

    public Integer getTot_blk_bl() {
        return tot_blk_bl;
    }

    public void setTot_blk_bl(Integer tot_blk_bl) {
        this.tot_blk_bl = tot_blk_bl;
    }

    public Integer getTot_reb_tot() {
        return tot_reb_tot;
    }

    public void setTot_reb_tot(Integer tot_reb_tot) {
        this.tot_reb_tot = tot_reb_tot;
    }

    public Integer getTot_pts() {
        return tot_pts;
    }

    public void setTot_pts(Integer tot_pts) {
        this.tot_pts = tot_pts;
    }

    public Integer getTot_stl() {
        return tot_stl;
    }

    public void setTot_stl(Integer tot_stl) {
        this.tot_stl = tot_stl;
    }

    public Integer getTot_blk_bo() {
        return tot_blk_bo;
    }

    public void setTot_blk_bo(Integer tot_blk_bo) {
        this.tot_blk_bo = tot_blk_bo;
    }

    public Integer getTot_fg_pc() {
        return tot_fg_pc;
    }

    public void setTot_fg_pc(Integer tot_fg_pc) {
        this.tot_fg_pc = tot_fg_pc;
    }

    public Integer getTot_pip() {
        return tot_pip;
    }

    public void setTot_pip(Integer tot_pip) {
        this.tot_pip = tot_pip;
    }

    public Integer getTot_fg3_pc() {
        return tot_fg3_pc;
    }

    public void setTot_fg3_pc(Integer tot_fg3_pc) {
        this.tot_fg3_pc = tot_fg3_pc;
    }

    public Integer getTot_fg3_a() {
        return tot_fg3_a;
    }

    public void setTot_fg3_a(Integer tot_fg3_a) {
        this.tot_fg3_a = tot_fg3_a;
    }

    public String getAsst1() {
        return asst1;
    }

    public void setAsst1(String asst1) {
        this.asst1 = asst1;
    }

    public Integer getTot_tov() {
        return tot_tov;
    }

    public void setTot_tov(Integer tot_tov) {
        this.tot_tov = tot_tov;
    }

    public Integer getTot_fg_a() {
        return tot_fg_a;
    }

    public void setTot_fg_a(Integer tot_fg_a) {
        this.tot_fg_a = tot_fg_a;
    }

    public Integer getP3_score() {
        return p3_score;
    }

    public void setP3_score(Integer p3_score) {
        this.p3_score = p3_score;
    }

    public String getAsst2() {
        return asst2;
    }

    public void setAsst2(String asst2) {
        this.asst2 = asst2;
    }

    public Integer getTot_reb_off() {
        return tot_reb_off;
    }

    public void setTot_reb_off(Integer tot_reb_off) {
        this.tot_reb_off = tot_reb_off;
    }

    public List<Shot> getShot() {
        return shot;
    }

    public void setShot(List<Shot> shot) {
        this.shot = shot;
    }

    public Leaders getLds() {
        return lds;
    }

    public void setLds(Leaders lds) {
        this.lds = lds;
    }

    public HashMap<Integer, PlayerStat> getPl() {
        return pl;
    }

    public void setPl(HashMap<Integer, PlayerStat> pl) {
        this.pl = pl;
    }
    
}
