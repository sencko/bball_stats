/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sencko.basketball.stats.advanced;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author i028512
 */
public class Game {

    Integer period;
    List<Event> pbp;
    String clock;
    Object[][] leaddata;
    Object[][] scoring;
    HashMap<Integer, TeamStats> tm;

    public static class Event {

        Integer s1;
        Integer lead;
        Integer period;
        Integer tno;
        Integer s2;
        String gt;
        String desc;
        String typ;
        Integer scoring;

        public Event() {

        }
        
        public  String getActor(){
           return desc.substring(0, desc.lastIndexOf(','));
        }
    }

    public static class TeamStats {

        Integer score;// "74",
        Integer tot_reb_def;// "31",
        Integer p1_score;// "17",
        Integer ot_score;// "",
        Integer tot_fou_fo;// "18"
        Integer tot_fg_m;// "29",
        Integer full_score;// "74",
        Integer tot_mins;// "",
        Integer tot_ass;// "8",
        Integer tot_ft_pc;// "54",
        Integer p2_score;// "18",
        Integer tot_fbp;// "0",
        Integer tot_ft_m;// "15",
        String coach;// "",
        Integer tot_ft_a;// "28",
        Integer tot_fg3_m;// "1",
        Integer touts;// "1",
        Integer tot_scp;// "16",
        Integer p4_score;// "21",
        Integer fouls;// "6",
        Integer tot_fou_pf;// "17",
        Integer tot_pft;// "20",
        Integer tot_bp;// "2",
        Integer tot_blk_bl;// "4",
        Integer tot_reb_tot;// "52",
        Integer tot_pts;// "74",
        Integer tot_stl;// "14",
        Integer tot_blk_bo;// "2",
        Integer tot_fg_pc;// "38",
        Integer tot_pip;// "44",
        Integer tot_fg3_pc;// "4",
        Integer tot_fg3_a;// "23",
        String asst1;// "",
        Integer tot_tov;// "23",
        Integer tot_fg_a;// "77",
        Integer p3_score;// "18",
        String asst2;// "",
        Integer tot_reb_off;// "21",
        List<Shot> shot;
        Leaders lds;
        HashMap<Integer, PlayerStat> pl;

        public TeamStats() {
        }
    }

    public static class Shot {

        Integer y;
        Integer p;
        Integer pts;
        Integer r;
        Integer per;
        String title;
        Integer x;

        public Shot() {
        }
    }

    public static class Leaders {

        HashMap<Integer, PlayerShort> blocks;
        HashMap<Integer, PlayerShort> assists;
        HashMap<Integer, PlayerShort> steals;
        HashMap<Integer, PlayerShort> rebounds;
        HashMap<Integer, PlayerShort> scoring;

        public Leaders() {
        }
    }

    public static class PlayerShort {

        Integer tot;
        String photo;
        String name;
        Integer no;

        public PlayerShort() {
        }
    }

    public static class PlayerStat {

        Integer fou_fo;// "4",
        String photourl;// "",
        String fname;// "Valentin",
        Integer fg_m;// "2",
        Integer ft_pc;// "38",
        String mins;// "28:46",
        Integer fg3_m;// "0",
        Integer fg3_a;// "3",
        Integer fou_pf;// "0",
        Integer ft_m;// "3",
        Integer active;// "0",
        Integer blk_bo;// "0",
        Integer fg_a;// "7",
        Integer reb_tot;// "2",
        Integer fg_pc;// "29",
        String dob;// "30/12/1899",
        Integer reb_def;// "1",
        Integer captain;// "0",
        Integer blk_bl;// "0",
        Integer reb_off;// "1",
        Integer tov;// "4",
        Integer age;// "0",
        Integer ft_a;// "8",
        String sname;// "Golchev",
        Integer starter;// "1",
        String pos;// "",
        Integer stl;// "2",
        String hgt;// "",
        String profileurl;// "",
        Integer ass;// "2",
        Integer pts;// "7",
        Integer fg3_pc;// "0",
        String nationality;// "",
        Integer eff;// -1

        public PlayerStat() {
        }
    }

}
