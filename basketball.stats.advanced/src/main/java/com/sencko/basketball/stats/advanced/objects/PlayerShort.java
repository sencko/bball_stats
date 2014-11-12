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
public class PlayerShort {

  private Integer tot;
  private String photo;
  private String name;
  private Integer no;

  public PlayerShort() {
  }

  public Integer getTot() {
    return tot;
  }

  public void setTot(Integer tot) {
    this.tot = tot;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNo() {
    return no;
  }

  public void setNo(Integer no) {
    this.no = no;
  }

}
