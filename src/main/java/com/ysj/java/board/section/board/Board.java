package com.ysj.java.board.section.board;

import lombok.Data;

@Data
public class Board
{
  private static int lastID;
  private int id;
  private String name;
  private String type;

  static
  {
    lastID = 0;
  }

  public Board(String name, String type)
  {
    this.id = ++lastID;
    this.name = name;
    this.type = type;
  }
}
